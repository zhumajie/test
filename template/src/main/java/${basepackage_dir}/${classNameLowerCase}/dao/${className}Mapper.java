<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${classNameLowerCase}.dao;

import java.util.List;
import com.ningpai.util.PageBean;
import ${basepackage}.${classNameLowerCase}.vo.${className};

/**
 * ${tableDesc}DAO接口
 * @author ${rapidAuthor}
 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
 */
public interface ${className}Mapper{
	
	/** 
	 * 插入${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	public int insert${className}(${className} entity) throws Exception ;
	
	/** 
	 * 根据ID修改${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	public int update${className}(${className} entity) throws Exception ;
	
	/** 
	 * 根据ID删除${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	public int delete${className}(List<${table.idColumn.javaType}> ids) throws Exception ;
	
	/** 
	 * 根据ID查询${tableDesc}单条信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	public ${className} select${className}ForOne(${className} entity);
	
	/** 
	 * 根据参数查询${tableDesc}分页信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	public PageBean select${className}ForPage(${className} entity);
	
	/** 
	 * 根据参数查询${tableDesc}列表信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	public List<${className}> select${className}ForList(${className} entity);
	
}
