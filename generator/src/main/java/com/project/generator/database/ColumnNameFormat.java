package com.project.generator.database;


import com.project.generator.database.beans.GeographyType;

public class ColumnNameFormat {

	
	/**
	* @Author fhm
	* @Description 将数据库类型转换成java类型
	* @Date 2018/5/17  17:28
	* @Param [dataType, columnType]
	* @Return java.lang.String
	**/
	public static String getJavaType(String dataType, String columnType) {

		if (dataType.equalsIgnoreCase("int")) {
			return "Integer";
		}

		if (dataType.equalsIgnoreCase("varchar")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("date")) {
			return "Date";
		}

		if (dataType.equalsIgnoreCase("tinyint")) {

			if (columnType.startsWith("tinyint(1)")) {
				return "Boolean";
			} else {
				return "Byte";
			}
		}

		if (dataType.equalsIgnoreCase("datetime")) {
			return "Date";
		}

		if (dataType.equalsIgnoreCase("bit")) {
			return "Boolean";
		}

		if (dataType.equalsIgnoreCase("text")) {
			return "String";
		}
		
		if (dataType.equalsIgnoreCase("char")) {
			return "String";
		}
		
		if (dataType.equalsIgnoreCase("double")) {
			return "Float";
		}

		if (dataType.equalsIgnoreCase("float")) {
			return "Float";
		}
		
		if(dataType.equalsIgnoreCase("smallint")){
			return "Integer";
		}

		if (dataType.equalsIgnoreCase("point")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("polygon")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("linestring")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("multipoint")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("multilinestring")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("multipolygon")) {
			return "String";
		}

		if (dataType.equalsIgnoreCase("geometrycollection")) {
			return "String";
		}

		return "";
	}

	/**
	* @Author
	* @Description 定位类型
	* @Date 2018/5/17  17:35
	* @Param [dataType]
	* @Return com.project.generator.database.beans.GeographyType
	**/
	public static GeographyType getGeographyType(String dataType) {

		if (dataType.equalsIgnoreCase("point")) {
			return GeographyType.point;
		}

		if (dataType.equalsIgnoreCase("polygon")) {
			return GeographyType.polygon;
		}

		if (dataType.equalsIgnoreCase("linestring")) {
			return GeographyType.linestring;
		}

		if (dataType.equalsIgnoreCase("multipoint")) {
			return GeographyType.multipoint;
		}

		if (dataType.equalsIgnoreCase("multilinestring")) {
			return GeographyType.multilinestring;
		}

		if (dataType.equalsIgnoreCase("multipolygon")) {
			return GeographyType.multipolygon;
		}

		if (dataType.equalsIgnoreCase("geometrycollection")) {
			return GeographyType.geometrycollection;
		}

		return GeographyType.none;
	}

	/**
	* @Author fhm
	* @Description 获取数据库类型
	* @Date 2018/5/17  17:33
	* @Param [dataType, columnType]
	* @Return java.lang.String
	**/
	public static String getJdbcType(String dataType, String columnType) {

		if (dataType.equalsIgnoreCase("int")) {
			return "INTEGER";
		}

		if (dataType.equalsIgnoreCase("smallint")) {
			return "INTEGER";
		}
		
		if (dataType.equalsIgnoreCase("varchar")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("date")) {
			return "DATE";
		}

		if (dataType.equalsIgnoreCase("tinyint")) {
			return "TINYINT";
		}

		if (dataType.equalsIgnoreCase("datetime")) {
			return "TIMESTAMP";
		}

		if (dataType.equalsIgnoreCase("bit")) {
			return "BIT";
		}
		
		if (dataType.equalsIgnoreCase("char")) {
			return "CHAR";
		}
		
		if (dataType.equalsIgnoreCase("double")) {
			return "DOUBLE";
		}
		
		if (dataType.equalsIgnoreCase("float")) {
			return "FLOAT";
		}

		if (dataType.equalsIgnoreCase("text")) {
			return "LONGVARCHAR";
		}

		if (dataType.equalsIgnoreCase("point")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("polygon")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("linestring")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("multipoint")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("multilinestring")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("multipolygon")) {
			return "VARCHAR";
		}

		if (dataType.equalsIgnoreCase("geometrycollection")) {
			return "VARCHAR";
		}

		return "";
	}
}
