package com.project.generator;

import com.project.core.utils.ListUtil;
import com.project.generator.config.Configuration;
import com.project.generator.database.SchemaBuild;
import com.project.generator.database.beans.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeSchemaFormat {

	private Configuration configuration;

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	private void addImportString(List<String> imports, String importString) {

		if (StringUtils.isNotBlank(importString)) {
			if (!ListUtil.isExists(imports, (p) -> {
				return p.equals(importString);
			})) {
				imports.add(importString);
			}
		}
	}

	public Schema build() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(configuration.getDatabase().getUrl() + "information_schema");
		dataSource.setUsername(configuration.getDatabase().getUsername());
		dataSource.setPassword(configuration.getDatabase().getPassword());
		jdbcTemplate.setDataSource(dataSource);

		Database database = new Database();
		database.setUrl(configuration.getDatabase().getUrl());
		database.setUsername(configuration.getDatabase().getUsername());
		database.setPassword(configuration.getDatabase().getPassword());
		database.setDatabase(configuration.getDatabase().getTarget());

		Schema schema = new SchemaBuild(database).build();

		List<String> basePropertys = new ArrayList<String>();
		if (StringUtils.isBlank(configuration.getBasePropertys()) == false) {
			basePropertys = ListUtil.select(Arrays.asList(configuration.getBasePropertys().split(",")), (r) -> {
				return r;
			});
		}
		final List<String> finalBasePropertys = basePropertys;

		for (Table table : schema.getTables()) {
			table.setClassName(TableNameFormat.getClassName(table.getTableName()));
			table.setInstanceName(TableNameFormat.getInstanceName(table.getClassName()));

			List<String> imports = new ArrayList<String>();

			for (Column column : table.getColumns()) {

				if (!ListUtil.isExists(finalBasePropertys, p -> {
					return p.equals(column.getName());
				})) {

					if (column.getColumnExtension().getColumnExtensionType() == ColumnExtensionType.Enum) {
						String enumMapString = "com.smart.java.utils.enums.EnumMap";
						addImportString(imports, enumMapString);
						addImportString(imports, configuration.getPackageName() + ".enums." + column.getColumnExtension().getName());
					}

					if (column.getColumnExtension().getColumnExtensionType() == ColumnExtensionType.JsonClass) {

						addImportString(imports, configuration.getPackageName() + ".domain." + column.getColumnExtension().getName());
					}

					if (column.getColumnExtension().getColumnExtensionType() == ColumnExtensionType.JsonList) {
						addImportString(imports, configuration.getPackageName() + ".domain." + column.getColumnExtension().getName());
						addImportString(imports, "java.util.List");
					}

					if (column.getColumnExtension().getColumnExtensionType() == ColumnExtensionType.Dict) {
						addImportString(imports, "com.fasterxml.jackson.databind.annotation.JsonDeserialize");						
						addImportString(imports, "com.smart.java.utils.dictionary.DictJsonAnnotation;");
						addImportString(imports, "com.smart.java.utils.project.json.DictDeserializer");
					}

					if (column.getColumnExtension().getIsJsonIgnore()) {
						addImportString(imports, "com.fasterxml.jackson.annotation.JsonProperty");
						addImportString(imports, "com.fasterxml.jackson.annotation.JsonProperty.Access");
					}

					if (column.getType().equals("date") || column.getType().equals("datetime")) {
						addImportString(imports, "com.fasterxml.jackson.annotation.JsonFormat");
						addImportString(imports, "java.util.Date");
					}

				}
			}

			table.setImports(imports);
			table.setWithOutBaseColumns(ListUtil.findAll(table.getColumns(), column -> {
				return !ListUtil.isExists(finalBasePropertys, p -> {
					return p.equals(column.getName());
				});
			}));

			table.setViewObjectColumns(ListUtil.findAll(table.getWithOutBaseColumns(), column -> {
				return column.getColumnExtension().getIsViewObject() == true;
			}));

			table.setNotViewObjectColumns(ListUtil.findAll(table.getWithOutBaseColumns(), column -> {
				return column.getColumnExtension().getIsViewObject() == false;
			}));

		}

		return schema;

	}
}
