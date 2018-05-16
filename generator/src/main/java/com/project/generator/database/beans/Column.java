package com.project.generator.database.beans;

public class Column {

	private String name;
	private String type;
	private String jdbcType;
	private String javaType;
	private String property;
	private String propertySet;
	private String propertyGet;
	private String comment;
	private int length;
	private Boolean isPrimary;
	private Boolean isNotNull;
	private Boolean isAutoIncrement;
	private ColumnExtension columnExtension;
	private GeographyType geographyType;
	private String defaultValue;

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public GeographyType getGeographyType() {
		return geographyType;
	}

	public void setGeographyType(GeographyType geographyType) {
		this.geographyType = geographyType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getPropertySet() {
		return propertySet;
	}

	public void setPropertySet(String propertySet) {
		this.propertySet = propertySet;
	}

	public String getPropertyGet() {
		return propertyGet;
	}

	public void setPropertyGet(String propertyGet) {
		this.propertyGet = propertyGet;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Boolean getIsAutoIncrement() {
		return isAutoIncrement;
	}

	public void setIsAutoIncrement(Boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public ColumnExtension getColumnExtension() {
		return columnExtension;
	}

	public void setColumnExtension(ColumnExtension columnExtension) {
		this.columnExtension = columnExtension;
	}

	public Boolean getIsNotNull() {
		return isNotNull;
	}

	public void setIsNotNull(Boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

}
