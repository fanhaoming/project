package com.project.generator.database.beans;

import java.util.List;

public class Table {

	private List<Column> columns;
	private String tableName;
	private String instanceName;
	private String moduleName;
	private String comment;
	private String className;
	private Column idColumn;
	private Column autoIncrementColumn;
	private List<Column> insertColumns;
	private List<Column> updateColumns;
	private List<Column> withOutBaseColumns;
	private List<Column> viewObjectColumns;
	private List<Column> notViewObjectColumns;
	private List<Column> listColumns;


	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<Column> getNotViewObjectColumns() {
		return notViewObjectColumns;
	}

	public void setNotViewObjectColumns(List<Column> notViewObjectColumns) {
		this.notViewObjectColumns = notViewObjectColumns;
	}

	private List<String> imports;

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Column getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(Column idColumn) {
		this.idColumn = idColumn;
	}

	public Column getAutoIncrementColumn() {
		return autoIncrementColumn;
	}

	public void setAutoIncrementColumn(Column autoIncrementColumn) {
		this.autoIncrementColumn = autoIncrementColumn;
	}

	public List<Column> getInsertColumns() {
		return insertColumns;
	}

	public void setInsertColumns(List<Column> insertColumns) {
		this.insertColumns = insertColumns;
	}

	public List<Column> getUpdateColumns() {
		return updateColumns;
	}

	public void setUpdateColumns(List<Column> updateColumns) {
		this.updateColumns = updateColumns;
	}

	public List<Column> getWithOutBaseColumns() {
		return withOutBaseColumns;
	}

	public void setWithOutBaseColumns(List<Column> withOutBaseColumns) {
		this.withOutBaseColumns = withOutBaseColumns;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public List<Column> getViewObjectColumns() {
		return viewObjectColumns;
	}

	public void setViewObjectColumns(List<Column> viewObjectColumns) {
		this.viewObjectColumns = viewObjectColumns;
	}

	public List<Column> getListColumns() {
		return listColumns;
	}

	public void setListColumns(List<Column> listColumns) {
		this.listColumns = listColumns;
	}

}
