<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${classNameLowerCase}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${basepackage}.${classNameLowerCase}.service.${className}Service;
import ${basepackage}.${classNameLowerCase}.vo.${className};
import com.ningpai.base.form.ResultMsg;
import com.ningpai.util.PageBean;
import com.ningpai.logger.util.OperaLogUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * ${tableDesc}Controller_请求接收器
 * @author ${rapidAuthor}
 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
 */
@Controller("${className}Controller")
public class ${className}Controller{
	@Resource(name = "${className}Service")
	private ${className}Service ${classNameFirstLower}Service;

	/**
	 * 跳转${tableDesc}分页列表页面
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@RequestMapping(value="/goto${className}Page",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView goto${className}Page(HttpServletRequest request, HttpServletResponse response, ${className} entity) {
		PageBean pageBean = ${classNameFirstLower}Service.select${className}ForPage(entity);
		pageBean.setUrl("goto${className}Page.htm");
		ModelAndView mv = new ModelAndView("jsp/${classNameLowerCase}/${classNameLowerCase}_page", "pageBean", pageBean);
		return mv;
	}

	/**
	 * 查询单个${tableDesc}详情,填充修改弹框显示
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@RequestMapping(value="/select${className}ForOne",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ${className} select${className}ForOne(HttpServletRequest request, HttpServletResponse response, ${className} entity) {
		return ${classNameFirstLower}Service.select${className}ForOne(entity);
	}

	/**
	 * 添加${tableDesc}
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@RequestMapping(value="/insert${className}",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg insert${className}(HttpServletRequest request,HttpServletResponse response,${className} entity) {
		ResultMsg rm = new ResultMsg();
		try {
			${classNameFirstLower}Service.insert${className}(entity);
			OperaLogUtil.addOperaLog(request, "添加${tableDesc}", "${tableDesc}标识:" + entity.get<#list table.columns as column><#if column.pk>${column.columnName}</#if></#list>());
			rm.setCode(ResultMsg.SUCCESS);
			rm.setNextUrl("goto${className}Page.htm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rm;
	}
	
	/** 
	 * 修改${tableDesc}
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@RequestMapping(value="/update${className}",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg update${className}(HttpServletRequest request,HttpServletResponse response,${className} entity) {
		ResultMsg rm = new ResultMsg();
		try {
			${classNameFirstLower}Service.update${className}(entity);
			OperaLogUtil.addOperaLog(request, "修改${tableDesc}", "${tableDesc}标识:" + entity.get<#list table.columns as column><#if column.pk>${column.columnName}</#if></#list>());
			rm.setCode(ResultMsg.SUCCESS);
			rm.setNextUrl("goto${className}Page.htm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rm;
	}
	
	/** 
	 * 根据参数删除${tableDesc}
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@RequestMapping(value="/delete${className}",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg delete${className}(HttpServletRequest request,HttpServletResponse response,@RequestBody List<${table.idColumn.javaType}> ids) {
		ResultMsg rm = new ResultMsg();
		try {
			${classNameFirstLower}Service.delete${className}(ids);
			OperaLogUtil.addOperaLog(request, "删除${tableDesc}", "${tableDesc}标识:" + ids.toString());
			rm.setCode(ResultMsg.SUCCESS);
			rm.setNextUrl("goto${className}Page.htm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rm;
	}

	/**
	 * 导出${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@RequestMapping(value="/export${className}Excel",method={RequestMethod.GET,RequestMethod.POST})
	public void export${className}Excel(HttpServletRequest request, HttpServletResponse response, ${className} entity) {
		List<${className}> list = ${classNameFirstLower}Service.select${className}ForList(entity);
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("${tableDesc}信息");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 设置宽度
		<#list table.columns as column>
		<#if !column.htmlHidden>
		sheet.setColumnWidth(${column_index-1}, 5000);
		</#if>
		</#list>

		// 设置列头信息
		HSSFCell cell = null;
		<#list table.columns as column>
		<#if !column.htmlHidden>
		cell = row.createCell(${column_index-1});
		cell.setCellValue("${column.columnAlias}");
		cell.setCellStyle(style);
		</#if>
		</#list>

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i+1);
			${className} cus = list.get(i);

			//创建单元格，并设置值
			<#list table.columns as column>
			<#if !column.htmlHidden>
			<#if column.isDateTimeColumn>
			if (cus.get${column.columnName}Str() != null) {
				row.createCell(${column_index-1}).setCellValue(cus.get${column.columnName}Str());
			}
			<#else>
			if (cus.get${column.columnName}() != null) {
				row.createCell(${column_index-1}).setCellValue(cus.get${column.columnName}());
			}
			</#if>
			</#if>
			</#list>
		}
		// 第六步，将文件存到指定位置
		String filename = String.valueOf(System.currentTimeMillis()).concat(".xls");
		// 设置下载时客户端Excel的名称
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + filename);
		OutputStream ouputStream;
		try {
		ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

}

