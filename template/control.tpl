package ${configuration.packageName}.${table.moduleName}.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.core.base.BaseControl;
import ${configuration.packageName}.${table.moduleName}.condition.${table.className}Condition;
import ${configuration.packageName}.${table.moduleName}.domain.${table.className};
import ${configuration.packageName}.${table.moduleName}.service.${table.className}Service;
import ${configuration.packageName}.${table.moduleName}.viewobject.${table.className}ViewObject;


@Controller
@Api(tags = "${table.comment}")
public class ${table.className}Control extends BaseControl {
	/**CreateByCodeGeneratorStart*/

	@Autowired
	${table.className}Service ${table.instanceName}ServiceImpl;

	@RequestMapping(value = "/api/${table.instanceName}/listByIds", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "批量获取${table.comment}", response = ${table.className}.class, produces = "application/json")
	public void listByIds(@RequestBody List<String> ids) {
		List<${table.className}> ${table.instanceName}s = ${table.instanceName}ServiceImpl.listByIds(ids);

	}

@RequestMapping(value = "/${configuration.api}/${table.instanceName}/save", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "保存${table.comment}", response = ${table.className}.class, produces = "application/json")
    public void save(@RequestBody ${table.className} ${table.instanceName}) {
        ${table.instanceName}ServiceImpl.save(${table.instanceName});
    }

	/**CreateByCodeGeneratorEnd*/
	

}
