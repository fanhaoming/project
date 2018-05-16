<#assign symbol_start="#{">
<#assign symbol_end="}">
<#assign symbol_enumHandle="com.smart.java.utils.mybatis.DefaultEnumTypeHandler">
<#assign symbol_jsonHandle="com.smart.java.utils.mybatis.DefaultJsonTypeHandler">
<#assign symbol_jsonListHandle="com.smart.java.utils.mybatis.DefaultJsonListHandler">
<#assign symbol_dictHandle="com.smart.java.utils.mybatis.DefaultDictTypeHandler">
<#assign symbol_urlHandle="com.smart.java.utils.mybatis.DefaultUrlHandle">
<#macro compress_single_line>
    <#local captured><#nested></#local>
    ${captured?replace("^\\s+|\\s+$|\\n|\\r", "", "rm") }
</#macro>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${configuration.packageName}.dao.${table.className}Mapper">
	<!--CreateByCodeGeneratorStart-->
	<resultMap id="BaseResultMap" type="${configuration.packageName}.domain.${table.className}">
		<#list table.columns as column>
			<#if column.isPrimary>
				<id column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}" />
			<#else>				
				<@compress_single_line>
				<#if column.columnExtension.columnExtensionType == "Enum"><result column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}" typeHandler="${symbol_enumHandle}"/></#if>
				<#if column.columnExtension.columnExtensionType == "JsonClass"><result column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}" typeHandler="${symbol_jsonHandle}"/></#if>
				<#if column.columnExtension.columnExtensionType == "JsonList"><result column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}" typeHandler="${symbol_jsonListHandle}"/></#if>
				<#if column.columnExtension.columnExtensionType == "Dict"><result column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}"/></#if>	
				<#if column.columnExtension.columnExtensionType == "Url"><result column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}" typeHandler="${symbol_urlHandle}"/></#if>				
				<#if column.columnExtension.columnExtensionType == "None"><result column="${column.name}" property="${column.property}" jdbcType="${column.jdbcType}"/></#if>
				</@compress_single_line>
			</#if>
		</#list>
	</resultMap>
	<sql id="Base_Column_List">
		<@compress_single_line>
		<#list table.columns as column>
			<#if column_index!=0>,</#if>
			<#if column.geographyType == "none">
				${column.name}
			<#else>
				st_astext(${column.name}) as ${column.name}
			</#if>
		</#list>
		</@compress_single_line>
	</sql>
	<sql id="List_Column_List">
		<@compress_single_line>
		<#list table.listColumns as column>
			<#if column_index!=0>,</#if>
			<#if column.geographyType == "none">
				${column.name}
			<#else>
				st_astext(${column.name}) as ${column.name}
			</#if>
		</#list>
		</@compress_single_line>
	</sql>
	<insert id="save" parameterType="${configuration.packageName}.domain.${table.className}">
		<#if table.autoIncrementColumn??>
		<selectKey resultType="java.lang.${table.autoIncrementColumn.javaType}" keyProperty="${table.autoIncrementColumn.name}" order="AFTER">
			SELECT LAST_INSERT_ID()
		</selectKey>
		</#if>
		insert into ${table.tableName} 
		<@compress_single_line>
			(
			<#list table.insertColumns as column>
				<#if column_index!=0>,</#if>${column.name}
			</#list>
			)
		</@compress_single_line>
		values 
		<@compress_single_line>
		(
			<#list table.insertColumns as column>
			<#if column_index!=0>,</#if>
			<#if column.geographyType == "none">
				${symbol_start}${column.property},jdbcType=${column.jdbcType}
				<#if column.columnExtension.columnExtensionType == "Enum">,typeHandler=${symbol_enumHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "JsonClass">,typeHandler=${symbol_jsonHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "JsonList">,typeHandler=${symbol_jsonListHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "Dict"></#if>
				<#if column.columnExtension.columnExtensionType == "Url">,typeHandler=${symbol_urlHandle}</#if>
				${symbol_end}
			<#else>
				ST_GeomFromText(${symbol_start}${column.property},jdbcType=${column.jdbcType}${symbol_end})
			</#if>
			</#list>
		)
		</@compress_single_line>
	</insert>
	<insert id="saveWithId" parameterType="${configuration.packageName}.domain.${table.className}">	
		insert into ${table.tableName} 
		<@compress_single_line>
			(id,
			<#list table.insertColumns as column>
				<#if column_index!=0>,</#if>${column.name}
			</#list>
			)
		</@compress_single_line>
		values 
		<@compress_single_line>
		(
			${symbol_start}id,jdbcType=INTEGER${symbol_end},
			<#list table.insertColumns as column>
			<#if column_index!=0>,</#if>
			<#if column.geographyType == "none">
				${symbol_start}${column.property},jdbcType=${column.jdbcType}
				<#if column.columnExtension.columnExtensionType == "Enum">,typeHandler=${symbol_enumHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "JsonClass">,typeHandler=${symbol_jsonHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "JsonList">,typeHandler=${symbol_jsonListHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "Dict"></#if>
				<#if column.columnExtension.columnExtensionType == "Url">,typeHandler=${symbol_urlHandle}</#if>
				${symbol_end}
			<#else>
				ST_GeomFromText(${symbol_start}${column.property},jdbcType=${column.jdbcType}${symbol_end})
			</#if>
			</#list>
		)
		</@compress_single_line>
	</insert>
	<insert id="saves" useGeneratedKeys="true" parameterType="java.util.List">  
 		<#if table.autoIncrementColumn??>
		<selectKey resultType="java.lang.${table.autoIncrementColumn.javaType}" keyProperty="${table.autoIncrementColumn.name}" order="AFTER">
			SELECT LAST_INSERT_ID()
		</selectKey>
		</#if>
	    insert into ${table.tableName}
	    <@compress_single_line>
			(
			<#list table.insertColumns as column>
				<#if column_index!=0>,</#if>${column.name}
			</#list>
			)
		</@compress_single_line>
	    values  
	    <foreach collection="list" item="item" index="index" separator="," >  
	    <@compress_single_line>
		(
			<#list table.insertColumns as column>
			<#if column_index!=0>,</#if>
			<#if column.geographyType == "none">
				${symbol_start}item.${column.property},jdbcType=${column.jdbcType}
				<#if column.columnExtension.columnExtensionType == "Enum">,typeHandler=${symbol_enumHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "JsonClass">,typeHandler=${symbol_jsonHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "JsonList">,typeHandler=${symbol_jsonListHandle}</#if>
				<#if column.columnExtension.columnExtensionType == "Dict"></#if>
				<#if column.columnExtension.columnExtensionType == "Url">,typeHandler=${symbol_urlHandle}</#if>
				${symbol_end}
			<#else>
				ST_GeomFromText(${symbol_start}item.${column.property},jdbcType=${column.jdbcType}${symbol_end})
			</#if>
			</#list>
		)
		</@compress_single_line>
	    </foreach>  
	</insert>	
	<update id="update" parameterType="${configuration.packageName}.domain.${table.className}">
		update ${table.tableName} set 
		<@compress_single_line>
		update_time = ${symbol_start}updateTime,jdbcType=TIMESTAMP${symbol_end},
		modifier = ${symbol_start}modifier,jdbcType=INTEGER${symbol_end}
		<#if (table.viewObjectColumns?size>0)>
		,
		</#if>
		<#list table.viewObjectColumns as column>
			${column.name} = 
				<#if column.geographyType == "none">
					${symbol_start}${column.property},jdbcType=${column.jdbcType}
						<#if column.columnExtension.columnExtensionType == "Enum">,typeHandler=${symbol_enumHandle}</#if>
						<#if column.columnExtension.columnExtensionType == "JsonClass">,typeHandler=${symbol_jsonHandle}</#if>
						<#if column.columnExtension.columnExtensionType == "JsonList">,typeHandler=${symbol_jsonListHandle}</#if>
						<#if column.columnExtension.columnExtensionType == "Dict"></#if>
						<#if column.columnExtension.columnExtensionType == "Url">,typeHandler=${symbol_urlHandle}</#if>
					${symbol_end}
				<#else>
					ST_GeomFromText(${symbol_start}${column.property},jdbcType=${column.jdbcType}${symbol_end})
				</#if>
			<#if column_index!=table.viewObjectColumns?size-1>,</#if>
		</#list>
		</@compress_single_line>
		where ${table.idColumn.name} = ${symbol_start}${table.idColumn.property},jdbcType=${table.idColumn.jdbcType}${symbol_end}
	</update>
	<update id="updateAll" parameterType="${configuration.packageName}.domain.${table.className}">
		update ${table.tableName} set 
		<@compress_single_line>
		<#list table.updateColumns as column>
			<#if column_index!=0>,</#if>
			${column.name} = 
				<#if column.geographyType == "none">
					${symbol_start}${column.property},jdbcType=${column.jdbcType}
						<#if column.columnExtension.columnExtensionType == "Enum">,typeHandler=${symbol_enumHandle}</#if>
						<#if column.columnExtension.columnExtensionType == "JsonClass">,typeHandler=${symbol_jsonHandle}</#if>
						<#if column.columnExtension.columnExtensionType == "JsonList">,typeHandler=${symbol_jsonListHandle}</#if>
						<#if column.columnExtension.columnExtensionType == "Dict"></#if>
						<#if column.columnExtension.columnExtensionType == "Url">,typeHandler=${symbol_urlHandle}</#if>
					${symbol_end}
				<#else>
					ST_GeomFromText(${symbol_start}${column.property},jdbcType=${column.jdbcType}${symbol_end})
				</#if>
		</#list>
		</@compress_single_line>
		where ${table.idColumn.name} = ${symbol_start}${table.idColumn.property},jdbcType=${table.idColumn.jdbcType}${symbol_end}
	</update>
	<select id="get" resultMap="BaseResultMap" parameterType="java.lang.Integer">
		select
		<include refid="Base_Column_List" />
		from ${table.tableName}
		where ${table.idColumn.name} = ${r"#{"}${table.idColumn.property},jdbcType=${table.idColumn.jdbcType}${r"}"}
	</select>
	<delete id="delete" parameterType="java.lang.Integer">
		delete from ${table.tableName}
		where ${table.idColumn.name} = ${r"#{"}${table.idColumn.property},jdbcType=${table.idColumn.jdbcType}${r"}"}
	</delete>
	<select id="listByIds" resultMap="BaseResultMap">
		select
		<include refid="List_Column_List" />
		from ${table.tableName}
		where ${table.idColumn.name} in
		<foreach item="item" index="index" collection="list" open="(" separator="," close=")">
			${r"#{item}"}
		</foreach>
	</select>		
	<select id="listAll" resultMap="BaseResultMap">
		select
		<include refid="List_Column_List" />
		from ${table.tableName} order by id asc
	</select>		
	<!--CreateByCodeGeneratorEnd-->
	<select id="listByCondition" resultMap="BaseResultMap">
		select
		<include refid="List_Column_List" />
		from ${table.tableName}
		<include refid="com.smart.java.utils.project.base.BaseDomainMapper.Base_Select_Condition"> 		 
		</include>
		<include refid="com.smart.java.utils.project.base.BaseDomainMapper.Base_Select_Condition_Order"> 		 
		</include>
		<include refid="com.smart.java.utils.project.base.BaseDomainMapper.Base_Select_Condition_Page"> 		 
		</include>
	</select>
	<select id="countByCondition" resultType="java.lang.Integer">
		select
		count(*)
		from ${table.tableName}
		<include refid="com.smart.java.utils.project.base.BaseDomainMapper.Base_Select_Condition"> 		 
		</include>
	</select>
	<select id="getByCondition" resultMap="BaseResultMap">
		select
		<include refid="List_Column_List" />
		from ${table.tableName}
		<include refid="com.smart.java.utils.project.base.BaseDomainMapper.Base_Select_Condition"> 		 
		</include>
		<include refid="com.smart.java.utils.project.base.BaseDomainMapper.Base_Select_Condition_Order"> 		 
		</include>	 	
		limit 0,1
	</select>
	<sql id="Extra_Query">
	</sql>
</mapper>