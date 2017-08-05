<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${classNameLowerCase}.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ningpai.util.PageBean;
import ${basepackage}.${classNameLowerCase}.service.${className}Service;
import ${basepackage}.${classNameLowerCase}.dao.${className}Mapper;
import ${basepackage}.${classNameLowerCase}.vo.${className};
import org.springframework.transaction.annotation.Transactional;

/**
 * ${tableDesc}Service
 * @author ${rapidAuthor}
 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
 */
@Service("${className}Service")
public class ${className}ServiceImpl implements ${className}Service{
	@Resource(name = "${className}Mapper")
	private ${className}Mapper ${classNameLower}Mapper;
	
	/** 
	 * 插入${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Transactional
	@Override
	public int insert${className}(${className} entity) throws Exception {
		return ${classNameLower}Mapper.insert${className}(entity);
	}
	
	/** 
	 * 根据ID修改${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Transactional
	@Override
	public int update${className}(${className} entity) throws Exception {
		return ${classNameLower}Mapper.update${className}(entity);
	}
	
	/** 
	 * 根据ID删除${tableDesc}信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Transactional
	@Override
	public int delete${className}(List<${table.idColumn.javaType}> ids) throws Exception {
		return ${classNameLower}Mapper.delete${className}(ids);
	}
	
	/** 
	 * 根据ID查询${tableDesc}单条信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public ${className} select${className}ForOne(${className} entity){
		return ${classNameLower}Mapper.select${className}ForOne(entity);
	}
	
	/** 
	 * 根据参数查询${tableDesc}分页信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public PageBean select${className}ForPage(${className} entity){
		return ${classNameLower}Mapper.select${className}ForPage(entity);
	}
	
	/** 
	 * 根据参数查询${tableDesc}列表信息
	 * @author ${rapidAuthor}
	 * @version 1.0 <#if now??>${now?string('yyyy-MM-dd')}</#if>
	 */
	@Override
	public List<${className}> select${className}ForList(${className} entity){
		return ${classNameLower}Mapper.select${className}ForList(entity);
	}
	
}
