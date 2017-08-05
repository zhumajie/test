<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${classNameLowerCase}.vo;

import com.ningpai.base.vo.BaseVo;
import java.io.Serializable;

/**
 * ${tableDesc}实体类
 * @author ${rapidAuthor}
 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
public class ${className} extends BaseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**标识数组_用于勾选导出*/
	private ${table.idColumn.javaType}[] ${table.idColumn.columnNameFirstLower}Arr;
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	/**搜索条件:${column.columnAlias}开始*/
	private ${column.javaType} ${column.columnNameLower}Begin;
	/**搜索条件:${column.columnAlias}截止*/
	private ${column.javaType} ${column.columnNameLower}End;
	</#if>
	/**${column.columnAlias}*/
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	
<@generateConstructor className/>

	public ${table.idColumn.javaType}[] get${table.idColumn.columnName}Arr() {
		return ${table.idColumn.columnNameFirstLower}Arr;
	}

	public void set${table.idColumn.columnName}Arr(${table.idColumn.javaType}[] ${table.idColumn.columnNameFirstLower}Arr) {
		this.${table.idColumn.columnNameFirstLower}Arr = ${table.idColumn.columnNameFirstLower}Arr;
	}

	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	public String get${column.columnName}Str() {
		try {
			return new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.${column.columnNameLower});//yyyy-MM-dd HH:mm:ss
		}catch(Exception e){
			return null;
		}
	}

	public void set${column.columnName}Str(String value) {
		try {
			this.${column.columnNameLower} = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (Exception e) {
		}
	}

	public ${column.javaType} get${column.columnName}Begin() {
		return this.${column.columnNameLower}Begin;
	}

	public void set${column.columnName}Begin(${column.javaType} value) {
		this.${column.columnNameLower}Begin = value;
	}

	public String get${column.columnName}BeginStr() {
		try {
			return new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.${column.columnNameLower}Begin);
		}catch(Exception e){
			return null;
		}
	}

	public void set${column.columnName}BeginStr(String value) {
		try {
			this.${column.columnNameLower}Begin = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (Exception e) {
		}
	}

	public ${column.javaType} get${column.columnName}End() {
		return this.${column.columnNameLower}End;
	}

	public void set${column.columnName}End(${column.javaType} value) {
		this.${column.columnNameLower}End = value;
	}

	public String get${column.columnName}EndStr() {
		try {
			return new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.${column.columnNameLower}End);
		}catch(Exception e){
			return null;
		}
	}

	public void set${column.columnName}EndStr(String value) {
		try {
			this.${column.columnNameLower}End = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (Exception e) {
		}
	}

		</#if>
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

	</#list>
}