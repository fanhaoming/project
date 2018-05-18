package ${configuration.packageName}.api.control;

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
import com.smart.java.utils.web.control.ResponseResult;
 
import ${configuration.packageName}.condition.${table.className}Condition;
import ${configuration.packageName}.domain.${table.className};
import ${configuration.packageName}.service.I${table.className}Service;
import ${configuration.packageName}.viewobject.${table.className}ViewObject;


@Controller
@Api(tags = "${table.comment}")
public class ${table.className}Control extends BaseControl {
	/**CreateByCodeGeneratorStart*/

	@Autowired
	I${table.className}Service ${table.instanceName}Service;

	@RequestMapping(value = "/api/${table.instanceName}/listByIds", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "批量获取${table.comment}", response = ${table.className}.class, produces = "application/json")
	public ResponseResult listByIds(@RequestBody List<Integer> ids) {
		List<${table.className}> ${table.instanceName}s = ${table.instanceName}Service.listByIds(ids);
		return ResponseResult.ok(data -> {	 
			data.put("${table.instanceName}s", ${table.instanceName}s);
		});
	}
	
	
	@RequestMapping(value = "/api/${table.instanceName}/save", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "保存${table.comment}", response = ${table.className}.class, produces = "application/json")
	public ResponseResult save(@RequestBody ${table.className}ViewObject ${table.instanceName}ViewObject) {
		${table.className} ${table.instanceName} = ${table.instanceName}Service.save(new ${table.className}().setViewObject(${table.instanceName}ViewObject));
		return ResponseResult.ok(data -> {	 
			data.put("${table.instanceName}", ${table.instanceName});
		});
	}
	

	@RequestMapping(value = "/api/${table.instanceName}/search", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询${table.comment}列表", response = ${table.className}.class, produces = "application/json")
	public ResponseResult search(@RequestBody ${table.className}Condition condition) {
		List<${table.className}> ${table.instanceName}s = ${table.instanceName}Service.listByCondition(condition);
		int count = ${table.instanceName}Service.countByCondition(condition);
		return ResponseResult.ok(data -> {
			data.put("count", count);
			data.put("${table.instanceName}s", ${table.instanceName}s);
		});
	}
	
	@RequestMapping(value = "/api/${table.instanceName}/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "查询${table.comment}", response = ${table.className}.class, produces = "application/json")
	public ResponseResult get(@PathVariable int id) {
		${table.className} ${table.instanceName} = ${table.instanceName}Service.get(id);
		return ResponseResult.ok(data -> {
			data.put("${table.instanceName}", ${table.instanceName});
		});
	}
	

	@RequestMapping(value = "/api/${table.instanceName}/edit", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "修改${table.comment}", response = ${table.className}.class, produces = "application/json")
	public ResponseResult edit(@RequestBody ${table.className}ViewObject ${table.instanceName}ViewObject) {
		${table.instanceName}Service.update(new ${table.className}().setViewObject(${table.instanceName}ViewObject));
		return ResponseResult.ok();
	}

	@RequestMapping(value = "/api/${table.instanceName}/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除${table.comment}", produces = "application/json")
	public ResponseResult delete(@PathVariable int id) {
		${table.instanceName}Service.delete(id);
		return ResponseResult.ok();
	}
	/**CreateByCodeGeneratorEnd*/
	

}
