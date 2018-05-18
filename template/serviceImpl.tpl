package ${configuration.packageName}.${table.moduleName}.service.impl;


import org.springframework.stereotype.Service;
import ${configuration.packageName}.${table.moduleName}.dao.${table.className}Mapper;
import ${configuration.packageName}.${table.moduleName}.domain.${table.className};
import ${configuration.packageName}.${table.moduleName}.service.${table.className}Service;
import com.project.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service("${table.instanceName}ServiceImpl")
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}, ${table.className}Mapper> implements ${table.className}Service {
	/**CreateByCodeGeneratorStart*/
	/**CreateByCodeGeneratorEnd*/
	@Autowired
	${table.className}Mapper ${table.instanceName}Mapper;
}
