package com.project.generator.database.beans;

public class ColumnExtension {

	private String name;

	private Boolean isJsonIgnore;
	private Boolean isViewObject;
	private Boolean isListIngore;

	public Boolean getIsListIngore() {
		return isListIngore;
	}

	public void setIsListIngore(Boolean isListIngore) {
		this.isListIngore = isListIngore;
	}

	private ColumnExtensionType columnExtensionType;

	public ColumnExtension() {
		this.isViewObject = false;
		this.isJsonIgnore = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ColumnExtensionType getColumnExtensionType() {
		return columnExtensionType;
	}

	public void setColumnExtensionType(ColumnExtensionType columnExtensionType) {
		this.columnExtensionType = columnExtensionType;
	}

	public Boolean getIsJsonIgnore() {
		return isJsonIgnore;
	}

	public void setIsJsonIgnore(Boolean isJsonIgnore) {
		this.isJsonIgnore = isJsonIgnore;
	}

	public Boolean getIsViewObject() {
		return isViewObject;
	}

	public void setIsViewObject(Boolean isViewObject) {
		this.isViewObject = isViewObject;
	}

}