package ${configuration.packageName}.viewobject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.smart.java.utils.mybatis.Table;
import com.smart.java.utils.project.base.BaseViewObject;
<#list table.imports as import>
import ${import};
</#list>

@ApiModel(value = "${table.comment}")
@Table(name = "${table.tableName}")
public class ${table.className}ViewObject extends BaseViewObject {
	/**CreateByCodeGeneratorStart*/
 	<#list table.viewObjectColumns as column>
	<#if column.defaultValue??>
	<#assign defaultValue = "=" + column.defaultValue>
	<#else>
	<#assign defaultValue = "">
	</#if>
	
	<#if column.type == "date">
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	</#if>
	<#if column.type == "datetime">
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
	@ApiModelProperty(value = "${column.comment}")
	<#if column.columnExtension.columnExtensionType == "Enum">
	@EnumMap(enumClass = ${column.columnExtension.name}.class)
	private ${column.columnExtension.name} ${column.property}${defaultValue};
	</#if>
	<#if column.columnExtension.columnExtensionType == "JsonClass">
	private ${column.columnExtension.name} ${column.property}${defaultValue};
	</#if>
	<#if column.columnExtension.columnExtensionType == "JsonList">
	private List<${column.columnExtension.name}> ${column.property}${defaultValue};
	</#if>
	<#if column.columnExtension.columnExtensionType == "Dict">
	@DictJsonAnnotation(code = "${column.columnExtension.name}")
	private int ${column.property} = 0;
	</#if>
	<#if column.columnExtension.columnExtensionType == "None" || column.columnExtension.columnExtensionType == "Url">
	private ${column.javaType} ${column.property}${defaultValue};	
	</#if>
	</#list>
	<#list table.viewObjectColumns as column>
	<#if column.columnExtension.columnExtensionType == "Enum">
	public ${column.columnExtension.name} get${column.propertyGet}() {
		return ${column.property};
	}
	public void set${column.propertySet}(${column.columnExtension.name} ${column.property}) {
		this.${column.property} = ${column.property};
	}
	</#if>
	<#if column.columnExtension.columnExtensionType == "Dict">
	public int get${column.propertyGet}() {
		return ${column.property};
	}
	public void set${column.propertySet}(int ${column.property}) {
		this.${column.property} = ${column.property};
	}
	</#if>
	<#if column.columnExtension.columnExtensionType == "JsonClass">
	public ${column.columnExtension.name} get${column.propertyGet}() {
		return ${column.property};
	}
	public void set${column.propertySet}(${column.columnExtension.name} ${column.property}) {
		this.${column.property} = ${column.property};
	}
	</#if>
	<#if column.columnExtension.columnExtensionType == "JsonList">
	public List<${column.columnExtension.name}> get${column.propertyGet}() {
		return ${column.property};
	}
	public void set${column.propertySet}(List<${column.columnExtension.name}> ${column.property}) {
		this.${column.property} = ${column.property};
	}
	</#if>
	<#if column.columnExtension.columnExtensionType == "None" || column.columnExtension.columnExtensionType == "Url">
	public ${column.javaType} get${column.propertyGet}() {
		return ${column.property};
	}
	public void set${column.propertySet}(${column.javaType} ${column.property}) {
		this.${column.property} = ${column.property};
	}
	</#if>
	</#list>
	/**CreateByCodeGeneratorEnd*/
}