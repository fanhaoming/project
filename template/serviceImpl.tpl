package ${configuration.packageName}.service.impl;


import org.springframework.stereotype.Service;
import ${configuration.packageName}.dao.${table.className}Mapper;
import ${configuration.packageName}.domain.${table.className};
import ${configuration.packageName}.service.I${table.className}Service;
import com.smart.java.utils.project.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service("${table.instanceName}Service")
public class ${table.className}Service extends BaseServiceImpl<${table.className}, ${table.className}Mapper> implements I${table.className}Service {
	/**CreateByCodeGeneratorStart*/
	/**CreateByCodeGeneratorEnd*/
	@Autowired
	${table.className}Mapper ${table.instanceName}Mapper;
}
