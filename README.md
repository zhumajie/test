# **欢迎使用kstore代码生成器**
## 配合使用生成器,标品需要如下改动

## OperaLogUtil类增加方法
	/**
     * 添加操作日志记录_业务Controller中使用
     * @author bail 2017-07-31
     *
     * @param request
     * @param operaCode 操作主题 如: 添加会员
     * @param operaContent 操作内容主体 如: 会员标识:1,2
     */
    public static void addOperaLog(HttpServletRequest request, String operaCode, String operaContent) {
        addOperaLog(request, (String) request.getSession().getAttribute("name"), operaCode, request.getSession().getAttribute("operaPath") + "," + operaContent);
    }
	
## ResultMsg类替换成如下
	public class ResultMsg<T> {
		public static final int SUCCESS = 1;
		public static final int ERROR = 0;
		private int code = ERROR;
		private T context;
		private String errorMsg = "操作失败,请稍后重试";
		private String msg = "操作成功";
		private String nextUrl;
	}
	
## PageBean增加如下2个构造器以及1个计算页码方法
	public PageBean(BaseVo vo, int totalCountTmp){
        //对总条数,当前页码,分页大小进行赋值
        this.rows = totalCountTmp;
        this.pageNo = vo.getPageNo();
        this.pageSize = vo.getPageSize();
        this.objectBean = vo;
        //重新计算出正确的page与rows参数
        calculatePage();
        //计算出开始记录数,mysql的分页需要
        vo.setPageNo(this.pageNo);
        vo.setPageSize(this.pageSize);
        vo.setStartRowNum((this.pageNo-1)*this.pageSize);
        vo.setEndRowNum(this.pageSize);
    }

    public PageBean(Map<String,Object> params, int totalCountTmp){
        //对总条数,当前页码,分页大小进行赋值
        this.rows = totalCountTmp;
        Object pageNoTmp = params.get("pageNo");
        Object pageSizeTmp = params.get("pageSize");
        if(pageNoTmp==null){
            this.pageNo = 1;
        }else{
            try {
                String pageNoStr = pageNoTmp.toString();
                if(pageNoStr.indexOf(".")>-1){//防止传入的是小数
                    this.pageNo = (int)Double.parseDouble(pageNoStr);
                }else{
                    this.pageNo = Integer.parseInt(pageNoStr);
                }
            } catch (NumberFormatException e) {
                this.pageNo = 1;
            }
        }
        if(pageSizeTmp==null){
            this.pageSize = FIFTEEN;
        }else{
            try {
                String pageSizeStr = pageSizeTmp.toString();
                if(pageSizeStr.indexOf(".")>-1){//防止传入的是小数
                    this.pageSize = (int)Double.parseDouble(pageSizeStr);
                }else{
                    this.pageSize = Integer.parseInt(pageSizeStr);
                }
            } catch (NumberFormatException e) {
                this.pageSize = FIFTEEN;
            }
        }
        this.objectBean = params;
        //重新计算出正确的page与rows参数
        calculatePage();
        params.put("pageNo",this.pageNo);
        params.put("pageSize",this.pageSize);
        params.put("startRowNum",(this.pageNo-1)*this.pageSize);
        params.put("endRowNum",this.pageSize);
    }

    /**
     * 计算出可以真正执行SQL中的page,rows
     */
    private void calculatePage(){
        if(this.pageNo<1){
            this.pageNo = 1;
        }
        if(this.pageSize<1){
            this.pageSize = FIFTEEN;
        }
        //计算出总页码
        int totalPagesTmp = getTotalPages();
		totalPagesTmp = totalPagesTmp==0?1:totalPagesTmp;
        // 若页码超过最大页码 则显示最后一个
        if (this.pageNo > totalPagesTmp){
            this.pageNo = totalPagesTmp;
        }
    }
	
## BasicSqlSupport增加2个分页方法
	/**
     * 查询分页信息-重载(BaseVo类型)
     * @author bail 2017-07-31
     * @param statement 分页SQL ID
     * @param baseVo 查询参数
     * @return PageBean 分页封装实例
     */
    public PageBean selectPage(String statement, BaseVo baseVo) {
        /*1.查询符合条件的记录总行数*/
        int totalRows = this.selectOne(statement+"Count",baseVo);

        /*2.根据参数以及总行数构建分页对象*/
        PageBean pageBean = new PageBean(baseVo,totalRows);

        /*3.根据总行数判断是否查询具体记录信息*/
        if(totalRows > 0){
            pageBean.setList(this.selectList(statement,baseVo));
        }else{
            pageBean.setList(Collections.emptyList());
        }
        return pageBean;
    }

    /**
     * 查询分页信息-重载(Map类型)
     * @author bail 2017-07-31
     * @param statement 分页SQL ID
     * @param params 查询参数
     * @return PageBean 分页封装实例
     */
    public PageBean selectPage(String statement, Map<String,Object> params) {
        /*1.查询符合条件的记录总行数*/
        int totalRows = this.selectOne(statement+"Count",params);

        /*2.根据参数以及总行数构建分页对象*/
        PageBean pageBean = new PageBean(params,totalRows);

        /*3.根据总行数判断是否查询具体记录信息*/
        if(totalRows > 0){
            pageBean.setList(this.selectList(statement,params));
        }else{
            pageBean.setList(Collections.emptyList());
        }
        return pageBean;
    }
	
## 在kstore_core模块 com.ningpai.base下创建vo包,然后创建BaseVo分页基类
	/**
	 * 用于分页的基类
	 * Author: bail
	 * Time: 2017/8/3.9:50
	 */
	public class BaseVo implements Serializable {
		private static final long serialVersionUID = 1L;
		/** 当前页数*/
		private int pageNo = 1;//默认第一页
		/** 每页显示数*/
		private int pageSize = 15;//默认15条一页
		/** 分页开始的条数*/
		private int startRowNum;
		/** 分页结束的条数*/
		private int endRowNum;

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getStartRowNum() {
			return startRowNum;
		}

		public void setStartRowNum(int startRowNum) {
			this.startRowNum = startRowNum;
		}

		public int getEndRowNum() {
			return endRowNum;
		}

		public void setEndRowNum(int endRowNum) {
			this.endRowNum = endRowNum;
		}
	}

## searchPag.jsp bug修改:查不到时,却可以点击下一页按钮,因为totalPages可能为0
	增加:
	<c:if test="${pageBean.totalPages==0}">
		<span class="current">1 </span>
	</c:if>
	修改:
	<c:if test="${pageBean.pageNo<pageBean.totalPages}">
		<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo+1 })"> 下一页 </a>
	</c:if>
	<c:if test="${pageBean.pageNo>=pageBean.totalPages }">
		<span class="disabled"> 下一页 </span>
	</c:if>