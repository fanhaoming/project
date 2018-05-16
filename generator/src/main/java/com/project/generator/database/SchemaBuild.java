package com.project.generator.database;

import com.project.core.utils.ListUtil;
import com.project.core.utils.PropertyNameConvert;
import com.project.generator.database.beans.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchemaBuild {

	private Database database;

	public SchemaBuild(Database database) {
		this.database = database;
	}

	public Schema build() {
		Schema schema = new Schema();
		List<Table> tables = new ArrayList<Table>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(database.getUrl() + "information_schema");
		dataSource.setUsername(database.getUsername());
		dataSource.setPassword(database.getPassword());
		jdbcTemplate.setDataSource(dataSource);

		List<Map<String, Object>> result = jdbcTemplate.queryForList("select TABLE_NAME,TABLE_COMMENT from tables where TABLE_SCHEMA=?",
				new Object[] { database.getDatabase() });
		for (Map<String, Object> map : result) {
			Table table = new Table();
			String tableName = map.get("TABLE_NAME").toString();

			String tableComment = map.get("TABLE_COMMENT").toString();
			table.setTableName(tableName);
			table.setComment(tableComment);

			List<Map<String, Object>> columnResult = jdbcTemplate
					.queryForList(
							"select COLUMN_NAME,DATA_TYPE,COLUMN_KEY,EXTRA,COLUMN_TYPE,COLUMN_COMMENT,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_DEFAULT from COLUMNS where TABLE_SCHEMA=? and TABLE_NAME=?",
							new Object[] { database.getDatabase(), tableName });
			List<Column> columns = new ArrayList<Column>();

			for (Map<String, Object> columnMap : columnResult) {

				String columnName = columnMap.get("COLUMN_NAME").toString();
				String dataType = columnMap.get("DATA_TYPE").toString();
				String columnKey = columnMap.get("COLUMN_KEY").toString();
				String extra = columnMap.get("EXTRA").toString();
				String columnType = columnMap.get("COLUMN_TYPE").toString();
				String comment = columnMap.get("COLUMN_COMMENT").toString();
				String nullable = columnMap.get("IS_NULLABLE").toString();
				String maxlength = "0";
				if (columnMap.get("CHARACTER_MAXIMUM_LENGTH") != null)
					maxlength = columnMap.get("CHARACTER_MAXIMUM_LENGTH").toString();
				String defaultValue = "";
				if (columnMap.get("COLUMN_DEFAULT") != null)
					defaultValue = columnMap.get("COLUMN_DEFAULT").toString();

				Column column = new Column();
				column.setName(columnName);
				column.setType(dataType);
				column.setJavaType(ColumnNameFormat.getJavaType(dataType, columnType));
				column.setProperty(PropertyNameConvert.columnToProperty(columnName));
				column.setPropertySet(PropertyNameConvert.propertyToClass(column.getProperty()));
				column.setPropertyGet(PropertyNameConvert.propertyToClass(column.getProperty()));
				column.setComment(comment);
				column.setJdbcType(ColumnNameFormat.getJdbcType(dataType, columnType));
				column.setLength(Integer.parseInt(maxlength));
				column.setGeographyType(ColumnNameFormat.getGeographyType(dataType));
				if (nullable.equals("NO"))
					column.setIsNotNull(true);
				else
					column.setIsNotNull(false);

				if (StringUtils.isNotBlank(defaultValue)) {
					switch (column.getJavaType()) {
					case "String":
						defaultValue = "\"" + defaultValue + "\"";
						break;
					case "Boolean":
						if (defaultValue.equals("1"))
							defaultValue = "true";
						else
							defaultValue = "false";
						break;
					default:
						break;
					}
					column.setDefaultValue(defaultValue);
				}

				ColumnExtension columnExtension = new ColumnExtension();
				columnExtension.setColumnExtensionType(ColumnExtensionType.None);
				columnExtension.setIsViewObject(false);
				columnExtension.setIsJsonIgnore(false);
				columnExtension.setIsListIngore(false);
				
				column.setColumnExtension(columnExtension);

				String regEx = "(.*?)\\{(.*?)\\}";
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(column.getComment());
				boolean rs = matcher.find();
				if (rs) {
					String special = matcher.group(2);
					String[] specials = special.split(",");
					for (String info : specials) {
						switch (info) {
						case "viewObject":
							columnExtension.setIsViewObject(true);
							break;
						case "vo":
							columnExtension.setIsViewObject(true);
							break;
						case "jsonIgnore":
							columnExtension.setIsJsonIgnore(true);
							break;
						case "ji":
							columnExtension.setIsJsonIgnore(true);
							break;
						case "url":
							columnExtension.setColumnExtensionType(ColumnExtensionType.Url);
							break;
						case "li":
							columnExtension.setIsListIngore(true);
							break;
						default:
							break;
						}
					}
				}

				regEx = "(.*?)\\[(.*?)\\]";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(column.getComment());
				// 查找字符串中是否有匹配正则表达式的字符/字符串
				rs = matcher.find();
				if (rs) {
					String special = matcher.group(2);

					regEx = "json\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.JsonClass);
						columnExtension.setName(matcher.group(1));
					}

					regEx = "jsons\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.JsonList);
						columnExtension.setName(matcher.group(1));
					}

					regEx = "enum\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.Enum);
						columnExtension.setName(matcher.group(1));
					}

					regEx = "enums\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.EnumList);
						columnExtension.setName(matcher.group(1));
					}

					regEx = "dict\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.Dict);
						columnExtension.setName(matcher.group(1));
					}
				}

				regEx = "(.*?)(\\[|\\{)";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(column.getComment());
				// 查找字符串中是否有匹配正则表达式的字符/字符串
				rs = matcher.find();
				if (rs) {
					comment = matcher.group(1);
					column.setComment(comment);
				}

				if (columnKey.equals("PRI")) {
					column.setIsPrimary(true);
					table.setIdColumn(column);
				} else {
					column.setIsPrimary(false);
				}
				if (extra.equals("auto_increment")) {
					column.setIsAutoIncrement(true);
					table.setAutoIncrementColumn(column);
				} else {
					column.setIsAutoIncrement(false);
				}

				columns.add(column);
			}

			table.setColumns(columns);
			table.setInsertColumns(ListUtil.findAll(columns, column -> {
				return column.getIsAutoIncrement() == false;
			}));

			table.setUpdateColumns(ListUtil.findAll(columns, column -> {
				return column.getIsPrimary() == false && !column.getName().equals("create_time");
			}));

			table.setListColumns(ListUtil.findAll(columns, column -> {
				return column.getColumnExtension().getIsListIngore() == false;
			}));
			
			tables.add(table);
		}

		schema.setTables(tables);

		return schema;

	}
}
