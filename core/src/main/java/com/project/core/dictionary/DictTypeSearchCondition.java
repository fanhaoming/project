package com.project.core.dictionary;


import com.project.core.base.AbstractCondition;

public class DictTypeSearchCondition extends AbstractCondition {

	private int domainId;
	private String name;
	private String code;

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
