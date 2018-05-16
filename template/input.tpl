<#list table.viewObjectColumns as column>
<div class="form-group">
	<label class="font-noraml">
		<span><#if column.isNotNull>*</#if>${column.comment}</span>
		<#if column.columnExtension.columnExtensionType == "Dict">
			<select class="form-control dict-select <#if column.isNotNull>required</#if>" dict="${column.columnExtension.name}" name="${column.property}"/>
		</#if>
		<#if column.columnExtension.columnExtensionType == "None">
			<input class="form-control <#if column.isNotNull>required</#if>" name="${column.property}"/>
		</#if>
	</label>
</div>
<#if ((column_index+1) % 2) == 0>
<div class="hr-line-dashed"></div>
</#if>
</#list>