<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${classNameLowerCase}.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.PageBean;
import ${basepackage}.${classNameLowerCase}.dao.${className}Mapper;
import ${basepackage}.${classNameLowerCase}.vo.${className};

/**
 * ${tableDesc}DAO
 * @author ${rapidAuthor}
 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
 */
@Repository("${className}Mapper")
public class ${className}MapperImpl extends BasicSqlSupport implements ${className}Mapper{
	
	/** 
	 * 插入${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public int insert${className}(${className} entity) throws Exception {
		return this.insert("${basepackage}.${classNameLowerCase}.dao.${className}Mapper.insert${className}",entity);
	}
	
	/** 
	 * 根据ID修改${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public int update${className}(${className} entity) throws Exception {
		return this.update("${basepackage}.${classNameLowerCase}.dao.${className}Mapper.update${className}",entity);
	}
	
	/** 
	 * 根据ID删除${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public int delete${className}(List<${table.idColumn.javaType}> params) throws Exception {
		return this.delete("${basepackage}.${classNameLowerCase}.dao.${className}Mapper.delete${className}",params);
	}
	
	/** 
	 * 根据ID查询${tableDesc}单条信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public ${className} select${className}ForOne(${className} entity){
		return this.selectOne("${basepackage}.${classNameLowerCase}.dao.${className}Mapper.select${className}ForOne",entity);
	}
	
	/** 
	 * 根据参数查询${tableDesc}分页信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public PageBean select${className}ForPage(${className} entity){
		return this.selectPage("${basepackage}.${classNameLowerCase}.dao.${className}Mapper.select${className}ForPage",entity);
	}
	
	/** 
	 * 根据参数查询${tableDesc}列表信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public List<${className}> select${className}ForList(${className} entity){
		return this.selectList("${basepackage}.${classNameLowerCase}.dao.${className}Mapper.select${className}ForList",entity);
	}
	
}
