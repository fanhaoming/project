package com.project.core.dictionary;

import com.project.core.common.ApplicationContextProvider;
import com.project.core.utils.ListUtil;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
/**
 * @ClassName DictionaryLoader
 * @Description TODO 数据字典工具
 * @Author fanhaoming
 * @Date 2018/6/4  9:25
 * @Version 1.0
 **/
public class DictionaryLoader {

	private static DictionaryLoader instance;

	/**
	 * Singleton access point to the manager.
	 */
	public static DictionaryLoader getInstance() {
		synchronized (DictionaryLoader.class) {
			if (instance == null) {
				instance = new DictionaryLoader();
				instance.init();
			}
		}
		return instance;
	}

	public void clearCache() {
		dictionaryTypes.clear();
		dicts.clear();
		readData();
	}

	private void readData() {
		DictionaryTypeService dictionaryTypeService = (DictionaryTypeService) ApplicationContextProvider.popBean(DictionaryTypeService.class);
		DictionaryService dictionaryService = (DictionaryService) ApplicationContextProvider.popBean(DictionaryService.class);

		dicts = dictionaryService.listAll();
		dictionaryTypes = dictionaryTypeService.listAll();

		dictionaryTypes.forEach(dictionaryType -> {
			dictionaryType.setDicts(new ArrayList<DictionaryItem>());
			dictionaryType.getDicts().addAll(ListUtil.findAll(dicts, dict -> {
				return dict.getDictionaryTypeId().equals(dictionaryType.getId());
			}));
			
			if(dictionaryType.getIsEnable()){
				enabledDictionaryTypes.add(dictionaryType);
				dictionaryType.setDicts(new ArrayList<DictionaryItem>());
				dictionaryType.getDicts().addAll(ListUtil.findAll(dicts, dict -> {
					return dict.getDictionaryTypeId().equals(dictionaryType.getId()) && dict.getIsEnable();
				}));
			}
		});
		
	}

	List<DictionaryType> dictionaryTypes = null;
	List<DictionaryItem> dicts = null;
	List<DictionaryType> enabledDictionaryTypes = null;

	private void init() {
		dictionaryTypes = new ArrayList<DictionaryType>();
		dicts = new ArrayList<DictionaryItem>();
		enabledDictionaryTypes = new ArrayList<DictionaryType>(); 
		readData();
	}

	public List<DictionaryType> getDictionaryTypes() {
		return dictionaryTypes;
	}
	
	public List<DictionaryType> getEnabledDictionaryTypes() {
		return enabledDictionaryTypes;
	}
	
	
	public List<DictionaryType> getDictionaryTypes(int domainId) {
		return ListUtil.findAll(dictionaryTypes, t -> {
			return t.getDomainId() == domainId;
		});
	}

	public List<DictionaryItem> getDicts() {
		return dicts;
	}

	public void setDictionaryTypes(List<DictionaryType> dictionaryTypes) {
		this.dictionaryTypes = dictionaryTypes;
	}

	public DictionaryType getDictionaryTypeByCode(String typeCode) {
		return ListUtil.find(dictionaryTypes, type -> {
			return type.getCode().equals(typeCode);
		});
	}

	public DictionaryItem getDictionaryByCode(String typeCode, String code) {
		DictionaryType dictionaryType = ListUtil.find(dictionaryTypes, type -> {
			return type.getCode().equalsIgnoreCase(typeCode);
		});
		return ListUtil.find(dictionaryType.getDicts(), d -> {
			return d.getCode().equals(code);
		});
	}

	public DictionaryItem getDictionaryByName(String typeCode, String name) {
		DictionaryType dictionaryType = ListUtil.find(dictionaryTypes, type -> {
			return type.getCode().equalsIgnoreCase(typeCode);
		});
		return ListUtil.find(dictionaryType.getDicts(), d -> {
			return d.getName().equals(name);
		});
	}




	public DictionaryItem getDictionaryByValue(String typeCode, int value) {
		DictionaryType dictionaryType = ListUtil.find(dictionaryTypes, type -> {
			return type.getCode().equalsIgnoreCase(typeCode);
		});
		
		return ListUtil.find(dictionaryType.getDicts(), d -> {
			return d.getId() == value;
		});
	 

	}

}
