package com.project.generator.database.beans;
/**
* @Author fhm
* @Description 列的扩展
* @Date 2018/5/17  17:38
* @Param
* @Return
**/
public class ColumnExtension {

	private String name;
	//序列化时是否忽略生成为json
	private Boolean isJsonIgnore;
	//是否为数据传输对象
	private Boolean isViewObject;
	//生成list时是否忽略
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