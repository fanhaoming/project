package com.project.core.dictionary;

import java.util.List;
/**
 * @ClassName DictionaryItem
 * @Description TODO 数据字典项
 * @Author fanhaoming
 * @Date 2018/6/4  9:25
 * @Version 1.0
 **/
public class DictionaryItem {
	private Integer id;
	private String name;
	private String code;
	private Integer parentId;
	private Integer dictionaryTypeId;
	private List<DictionaryItem> dicts;
	private Integer domainId;
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

	public List<DictionaryItem> getDicts() {
		return dicts;
	}

	public void setDicts(List<DictionaryItem> dicts) {
		this.dicts = dicts;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDictionaryTypeId() {
		return dictionaryTypeId;
	}

	public void setDictionaryTypeId(Integer dictionaryTypeId) {
		this.dictionaryTypeId = dictionaryTypeId;
	}
	
	
}

