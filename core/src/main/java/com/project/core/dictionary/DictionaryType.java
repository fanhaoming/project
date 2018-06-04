package com.project.core.dictionary;

import java.util.List;

public class DictionaryType {
	private Integer id;
	private String name;
	private String code;
	private Boolean isTree;
	private Integer domainId;
	private List<DictionaryItem> dicts;
	private Boolean isEnable;

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getIsTree() {
		return isTree;
	}

	public void setIsTree(Boolean isTree) {
		this.isTree = isTree;
	}

	public List<DictionaryItem> getDicts() {
		return dicts;
	}

	public void setDicts(List<DictionaryItem> dicts) {
		this.dicts = dicts;
	}

	public Boolean getTree() {
		return isTree;
	}

	public void setTree(Boolean tree) {
		isTree = tree;
	}

	public Boolean getEnable() {
		return isEnable;
	}

	public void setEnable(Boolean enable) {
		isEnable = enable;
	}
}
