package ${configuration.packageName}.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.smart.java.utils.mybatis.Table;
import ${configuration.packageName}.viewobject.${table.className}ViewObject;
<#list table.imports as import>
import ${import};
</#list>

@ApiModel(value = "${table.comment}")
@Table(name = "${table.tableName}")
public class ${table.className} extends BaseBusinessObject {
	/**CreateByCodeGeneratorStart*/
	public ${table.className} setViewObject(${table.className}ViewObject ${table.instanceName}){
		this.setId(${table.instanceName}.getId());
		<#list table.viewObjectColumns as column>
		this.${column.property} = ${table.instanceName}.get${column.propertyGet}();
		</#list>	
		return this;
	}
 	<#list table.withOutBaseColumns as column>
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
	<#if column.columnExtension.isJsonIgnore>
	@JsonProperty(access = Access.WRITE_ONLY)
	</#if>
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
	@JsonDeserialize(using = DictDeserializer.class)
	private int ${column.property} = 0;
	</#if>
	<#if column.columnExtension.columnExtensionType == "None" || column.columnExtension.columnExtensionType == "Url">
	private ${column.javaType} ${column.property}${defaultValue};	
	</#if>
	</#list>
	<#list table.withOutBaseColumns as column>
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