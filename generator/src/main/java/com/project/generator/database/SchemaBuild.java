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

			//通过表的注释，如：权限{permission},会将权限放在table的comment字段中，permission放在table的moduleName模块名中
			String regEx = "(.*?)\\{(.*?)\\}";
			Pattern pattern = Pattern.compile(regEx);
			//根据正则判断各个列的注释中的标识
			Matcher matcher = pattern.matcher(tableComment);
			boolean rs = matcher.find();
			if(rs){
				table.setComment(matcher.group(1));
				table.setModuleName(matcher.group(2));
			}else {//如果不匹配，默认放到core模块中
				table.setComment(tableComment);
				table.setModuleName("core");
			}


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
				//CHARACTER_MAXIMUM_LENGTH:以字符为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据
				if (columnMap.get("CHARACTER_MAXIMUM_LENGTH") != null)
					maxlength = columnMap.get("CHARACTER_MAXIMUM_LENGTH").toString();
				String defaultValue = "";
				//默认值
				if (columnMap.get("COLUMN_DEFAULT") != null)
					defaultValue = columnMap.get("COLUMN_DEFAULT").toString();

				Column column = new Column();
				column.setName(columnName);
				column.setType(dataType);
				//将列的数据库类型转换成java类型
				column.setJavaType(ColumnNameFormat.getJavaType(dataType, columnType));
				//设置java类成员变量命名格式
				column.setProperty(PropertyNameConvert.columnToProperty(columnName));
				//将属性转换成类名
				column.setPropertySet(PropertyNameConvert.propertyToClass(column.getProperty()));
				column.setPropertyGet(PropertyNameConvert.propertyToClass(column.getProperty()));
				//设置列描述
				column.setComment(comment);
				column.setJdbcType(ColumnNameFormat.getJdbcType(dataType, columnType));
				//获取列的最长长度
				column.setLength(Integer.parseInt(maxlength));
				column.setGeographyType(ColumnNameFormat.getGeographyType(dataType));
				//设置是否可空
				if (nullable.equals("NO"))
					column.setIsNotNull(true);
				else
					column.setIsNotNull(false);

				//设置默认值
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
				//判断是注释中有｛｝的
				regEx = "(.*?)\\{(.*?)\\}";
				pattern = Pattern.compile(regEx);
				//根据正则判断各个列的注释中的标识
				matcher = pattern.matcher(column.getComment());
				rs = matcher.find();
				if (rs) {
					String special = matcher.group(2);//获取第二部分的数据，即大括号中的内容
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

				//判断注释中有中括号[]的
				regEx = "(.*?)\\[(.*?)\\]";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(column.getComment());
				// 查找字符串中是否有匹配正则表达式的字符/字符串
				rs = matcher.find();
				if (rs) {
					String special = matcher.group(2);
					//中括号中有以json(内容)为json数据
					regEx = "json\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.JsonClass);
						columnExtension.setName(matcher.group(1));
					}
					//中括号中有jsons(内容)为json数组
					regEx = "jsons\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.JsonList);
						columnExtension.setName(matcher.group(1));
					}
					//中括号中有以enum(内容)为枚举
					regEx = "enum\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.Enum);
						columnExtension.setName(matcher.group(1));
					}
					//中括号中有以enums(内容)为枚举集合
					regEx = "enums\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.EnumList);
						columnExtension.setName(matcher.group(1));
					}
					//中括号中有以dict(内容)为数据字典
					regEx = "dict\\((.*?)\\)";
					pattern = Pattern.compile(regEx);
					matcher = pattern.matcher(special);
					rs = matcher.find();
					if (rs) {
						columnExtension.setColumnExtensionType(ColumnExtensionType.Dict);
						columnExtension.setName(matcher.group(1));
					}
				}
				//XXX[或{，即匹配后面以[或｛结尾的数据
				regEx = "(.*?)(\\[|\\{)";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(column.getComment());
				// 查找字符串中是否有匹配正则表达式的字符/字符串
				rs = matcher.find();
				if (rs) {
					comment = matcher.group(1);
					column.setComment(comment);
				}
				//判断是否主键
				if (columnKey.equals("PRI")) {
					column.setIsPrimary(true);
					table.setIdColumn(column);
				} else {
					column.setIsPrimary(false);
				}
				//判断是否自增长
				if (extra.equals("auto_increment")) {
					column.setIsAutoIncrement(true);
					table.setAutoIncrementColumn(column);
				} else {
					column.setIsAutoIncrement(false);
				}

				columns.add(column);
			}

			table.setColumns(columns);
			//将不是自增长的列放入 新增需插入的字段集合，到时生成的sql，这些字段将会是需要插入的字段
			table.setInsertColumns(ListUtil.findAll(columns, column -> {
				return column.getIsAutoIncrement() == false;
			}));
			//将不是create_time并且不是主键的字段加入 更新需改变的字段集合
			table.setUpdateColumns(ListUtil.findAll(columns, column -> {
				return column.getIsPrimary() == false && !column.getName().equals("create_time");
			}));

			//设置获取列表时不忽略的字段集合
			table.setListColumns(ListUtil.findAll(columns, column -> {
				return column.getColumnExtension().getIsListIngore() == false;
			}));
			
			tables.add(table);
		}

		schema.setTables(tables);

		return schema;

	}
}
