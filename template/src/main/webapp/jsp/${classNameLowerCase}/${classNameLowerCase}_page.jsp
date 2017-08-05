<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLowerCase = className?lower_case>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="basePath" value="<@jspEl 'pageContext.request.scheme'/>://<@jspEl 'pageContext.request.serverName'/>:<@jspEl 'pageContext.request.serverPort'/><@jspEl 'pageContext.request.contextPath'/>/"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>${tableDesc}列表</title>

	<!-- Bootstrap -->
	<link href="<@jspEl 'basePath'/>css/bootstrap.min.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>css/font-awesome.min.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>iconfont/iconfont.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>css/bootstrap-select.min.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>css/select2.min.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>css/style.css" rel="stylesheet"/>
	<link href="<@jspEl 'basePath'/>css/style_new.css" rel="stylesheet"/>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<jsp:include page="../page/header.jsp"></jsp:include><%-- 引用头 --%>
<div class="page_body container-fluid">
	<div class="row">
		<jsp:include page="../page/left.jsp"></jsp:include>
		<div class="col-lg-20 col-md-19 col-sm-18 main">
			<jsp:include page="../page/left2.jsp"></jsp:include>
			<div class="main_cont">
				<jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
				<input type="hidden" value="<@jspEl 'token'/>" id="hi_token"/>
				<h2 class="main_title"><@jspEl 'pageNameChild'/> <small>(共<@jspEl 'pageBean.rows'/>条)</small></h2>
				<div class="common_data p20">
					<div class="filter_area">
						<input type="hidden" value="searchForm" id="formId">
						<input type="hidden" value="goto${className}Page.htm" id="formName">
						<form role="form" class="form-inline"  action="goto${className}Page.htm" id="searchForm" method="post">
							<#list table.notPkColumns?chunk(4) as row>
								<#list row as column>
									<#if !column.htmlHidden>
										<#if column.isDateTimeColumn>
							<div class="form-group">
								<div class="input-group date form_datetime w300">
									<span class="input-group-addon">${column.columnAlias}开始</span>
									<input class="form-control" type="text" name="${column.columnNameLower}BeginStr" value="<@jspEl 'pageBean.objectBean.${column.columnNameLower}BeginStr'/>" readonly maxlength="19">
									<span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group date form_datetime w300">
									<span class="input-group-addon">${column.columnAlias}截止</span>
									<input class="form-control" type="text" name="${column.columnNameLower}EndStr" value="<@jspEl 'pageBean.objectBean.${column.columnNameLower}EndStr'/>" readonly maxlength="19">
									<span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
											<#else>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon">${column.columnAlias}</span>
									<input type="text" class="form-control" name="${column.columnNameLower}" maxlength="${column.size}" value="<@jspEl 'pageBean.objectBean.${column.columnNameLower}'/>">
								</div>
							</div>
										</#if>
									</#if>
								</#list>
							</#list>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">搜索</button>
							</div>
						</form>
					</div>

					<div class="data_ctrl_area mb20">
						<div class="data_ctrl_search pull-right"></div>
						<div class="data_ctrl_brns pull-left">
							<button type="button" class="btn btn-info" onclick="addInfo();">
								<i class="glyphicon glyphicon-plus"></i>添加
							</button>
							<button type="button" class="btn btn-info" onclick="delInfo();">
								<i class="glyphicon glyphicon-trash"></i>删除
							</button>
							<div class="btn-group">
								<button type="button" class="btn btn-info" onclick="exportList();">
									<i class="glyphicon glyphicon-export"></i>导出所有
								</button>
								<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#" onclick="exportCheck()">导出选中</a></li>
								</ul>
							</div>
						</div>
						<div class="clr"></div>
					</div>

					<div class="table_tabs" id="info_tabs">
						<ul class="flagli">
							<li class="active">
								<a href="javascript:void(0);" data-type="0">标签一</a>
							</li>
							<li>
								<a href="javascript:void(0);" data-type="1">标签二</a>
							</li>
						</ul>
					</div>

					<form id="exportForm" method="post">
						<table class="table table-striped table-hover">
							<thead>
							<tr>
								<th width="10"><input type="checkbox" onclick="allunchecked(this,'${table.idColumn.columnNameFirstLower}Arr');"></th>
								<#list table.columns as column>
									<#if !column.htmlHidden>
								<th>${column.columnAlias}</th>
									</#if>
								</#list>
								<th width="150">操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="<@jspEl 'pageBean.list'/>" var="myColumn" varStatus="i">
								<tr>
									<td><input type="checkbox" name="${table.idColumn.columnNameFirstLower}Arr"  value="<@jspEl 'myColumn.${table.idColumn.columnNameFirstLower}'/>"></td>
									<#list table.columns as column>
										<#if !column.htmlHidden>
											<#if column.isDateTimeColumn>
									<td><@jspEl 'myColumn.${column.columnNameFirstLower}Str'/></td>
												<#else>
									<td><@jspEl 'myColumn.${column.columnNameFirstLower}'/></td>
											</#if>
										</#if>
									</#list>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default" onclick="editInfo(<@jspEl 'myColumn.${table.idColumn.columnNameFirstLower}'/>);"><i class="glyphicon glyphicon-cog"></i>编辑</button>
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
												<span class="caret"></span>
												<span class="sr-only">Toggle Dropdown</span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a href="javascript:void(0);" onclick="delInfo(<@jspEl 'myColumn.${table.idColumn.columnNameFirstLower}'/>);">删除</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</form>

					<div class="table_foot">
						<c:import url="../page/searchPag.jsp">
							<c:param name="pageBean" value="<@jspEl 'pageBean'/>"/>
							<c:param name="path" value="../"></c:param>
						</c:import>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

<!-- 编辑信息Modal -->
<div class="modal fade" id="myInfoModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">编辑${tableDesc}</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="" method="post" id="myInfoForm">
					<input type="hidden" name="CSRFToken" value="<@jspEl 'token'/>">
					<input type="hidden" id="${table.idColumn.columnNameFirstLower}" name="${table.idColumn.columnNameFirstLower}" value="">
					<#assign oneFlag=true>
					<#list table.columns as column>
						<#if !column.htmlHidden>
					<div class="form-group">
						<label class="control-label col-sm-5"><span class="text-danger">*</span>${column.columnAlias}：</label>
						<div class="col-sm-1"></div>
						<div class="col-sm-8">
							<#if column.isDateTimeColumn>
						<div class="input-group date form_datetime">
							<input id="${column.columnNameLower}Str" name="${column.columnNameLower}Str" type="text" class="form-control w200 required" value="" readonly maxlength="19" />
							<span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
								<#else>
						<input id="${column.columnNameLower}" name="${column.columnNameLower}" type="text" class="form-control w200 required" value="" maxlength="${column.size}" />
							</#if>
						</div>
						<#if oneFlag>
						<div class="col-sm-3">
							<a href="javascript:;" id="${column.columnNameLower}Pop" class="help_tips">
								<i class="icon iconfont">&#xe611;</i>
							</a>
						</div>
						<#assign oneFlag=false>
						</#if>
					</div>
						</#if>
					</#list>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="subForm();">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<%-- 按需添加当前页面所需的插件 --%>
<script src="<@jspEl 'basePath'/>js/bootstrap.min.js"></script>
<script src="<@jspEl 'basePath'/>js/bootstrap-select.min.js"></script>
<script src="<@jspEl 'basePath'/>js/bootstrap-datetimepicker.min.js"></script>
<script src="<@jspEl 'basePath'/>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<@jspEl 'basePath'/>js/common.js"></script>
<script src="<@jspEl 'basePath'/>js/common/common_alert.js"></script>
<script src="<@jspEl 'basePath'/>js/common/common_checked.js"></script>
<script src="<@jspEl 'basePath'/>js/select2.min.js"></script>
<script>
	<%--打开添加弹出框,并清空输入框--%>
	function addInfo(){
		myInfoFormValidator.resetForm();
		$('#myInfoForm').attr('action', 'insert${className}.htm');
		$("#${table.idColumn.columnNameFirstLower}").val('');
	<#list table.columns as column>
		<#if !column.htmlHidden><#if column.isDateTimeColumn>
		$("#${column.columnNameLower}Str").val('');
	<#else>
		$("#${column.columnNameLower}").val('');
	</#if></#if>
	</#list>
		$("#myInfoModal").find(".modal-title").text("添加${tableDesc}");
		$('#myInfoModal').modal('show');
	}

	<%--打开编辑弹出框,并请求后台为输入框赋值--%>
	function editInfo(${table.idColumn.columnNameFirstLower}){
		myInfoFormValidator.resetForm();
		$('#myInfoForm').attr('action', 'update${className}.htm');
		$("#${table.idColumn.columnNameFirstLower}").val(${table.idColumn.columnNameFirstLower});
		$.ajax({
			url:'select${className}ForOne.htm',
			type:'POST',
			data:{'CSRFToken':'<@jspEl 'token'/>','${table.idColumn.columnNameFirstLower}':${table.idColumn.columnNameFirstLower}},
			success:function(entity){
				<#list table.columns as column>
				<#if !column.htmlHidden><#if column.isDateTimeColumn>
				$("#${column.columnNameLower}Str").val(entity.${column.columnNameLower}Str);
				<#else>
				$("#${column.columnNameLower}").val(entity.${column.columnNameLower});
				</#if></#if>
				</#list>
			}
		});
		$("#myInfoModal").find(".modal-title").text("编辑${tableDesc}");
		$('#myInfoModal').modal('show');
	}

	<%--提交添加/编辑信息表单--%>
	function subForm() {
		if($('#myInfoForm').valid()){
			$.ajax({
				url:$('#myInfoForm').attr('action'),
				type:'POST',
				data:$("#myInfoForm").serialize(),
				success:function(resMsg){
					if(resMsg.code == 1){
						if(resMsg.nextUrl){
							location.href=resMsg.nextUrl;
						}
					}else{
						showTipAlert(resMsg.errorMsg);
					}
				}
			});
		}
	}

	<%--单个删除信息--%>
	function delInfo(rowId){
		var idArr;
		if(rowId){
			idArr = new Array();
			idArr.push(rowId);//单个删除方式
		}else{
			var chkObjs = $("input[name='${table.idColumn.columnNameFirstLower}Arr']:checked");
			if(chkObjs.length == 0){
				showTipAlert("请至少选择一条记录");
				return;
			}
            idArr = new Array();
			$.each(chkObjs,function(){
				idArr.push($(this).val());//批量删除方式
			});
		}
		<%-- 弹出框提示用户是否确认删除 --%>
		simpleConfirm('您确定要删除所选记录吗？',function(){
			$.ajax({
				url:'delete${className}.htm?CSRFToken=<@jspEl 'token'/>',
				type:'POST',
				data:JSON.stringify(idArr),//考虑浏览器兼容性问题,需要引入json插件
				contentType:'application/json;charset=utf-8',
				success: function (resMsg) {
					if(resMsg.code == 1){
						if(resMsg.nextUrl){
							location.href=resMsg.nextUrl;
						}
					}else{
						showTipAlert(resMsg.errorMsg);
					}
				}
			});
		});
	}

    <%--导出所有的记录--%>
    function exportList(){
        window.location.href="export${className}Excel.htm?CSRFToken=<@jspEl 'token'/>";
    }

    <%--导出选中的记录--%>
    function exportCheck(){
        if($("[name='${table.idColumn.columnNameFirstLower}Arr']:checked").length){
            $("#exportForm").attr("action","export${className}Excel.htm?CSRFToken=<@jspEl 'token'/>");
            $("#exportForm").submit();
        }else {
            showTipAlert("请至少选择一条记录");
        }
    }

	<%--页面加载完毕执行的方法[默认放于最下方]--%>
	$(function(){
		/*添加编辑弹出框form表单验证*/
		myInfoFormValidator = $('#myInfoForm').validate();

		/*为选定的select下拉菜单开启搜索提示*/
		$('select[data-live-search="true"]').select2();

		/*日期选择事件*/
		$('.form_datetime').datetimepicker({
			format: 'yyyy-mm-dd',// hh:ii:ss
			weekStart: 1,
			autoclose: true,
			language: 'zh-CN',
			pickerPosition: 'bottom-left',
			todayBtn: true,
            minView:'month'//选到日期  'hour':默认选到时分
		});

		/*切换tab页效果*/
		$('#info_tabs a').click(function () {
			$that = $(this);
			$that.parent().addClass('active');
			$that.parent().siblings().removeClass('active');
			//var _cla = $that.attr("data-type");
			//$("#xxx").val(_cla);
			$("#searchForm").submit();
		});
        <#assign oneFlagTmp=true>
		/*下面是表单里面的填写项提示相关的*/
		$('#<#list table.columns as column><#if !column.htmlHidden><#if oneFlagTmp>${column.columnNameLower}Pop<#assign oneFlagTmp=false></#if></#if></#list>').popover({
			content : '请填写xxx,范围:xxx',
			trigger : 'hover'
		});
	});
</script>
</body>
</html>
