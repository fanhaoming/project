package com.project.core.dictionary;

import java.util.HashMap;

public class DictJsonSerializerInstance {

	private static DictJsonSerializerInstance instance;

	/**
	 * 单例模式获取字典json序列化实体
	 */
	public static DictJsonSerializerInstance getInstance() {
		synchronized (DictJsonSerializerInstance.class) {
			if (instance == null) {
				instance = new DictJsonSerializerInstance();
				instance.init();
			}
		}
		return instance;
	}

	private HashMap<String, DictJsonSerializer> map = null;

	private void init() {
		map = new HashMap<String, DictJsonSerializer>();
	}

	/**
	* @Author fhm
	* @Description 根据字段的字典注解获取对应的序列化对象
	* @Date 2018/6/4  10:30
	* @Param [annotation] 字典注解对象
	* @Return
	**/
	public DictJsonSerializer getByAnnoation(DictAnnotation annotation) {
		if (map.containsKey(annotation.code())) {
			return map.get(annotation.code());
		} else {
			DictJsonSerializer serializer = new DictJsonSerializer();
			serializer.setDictAnnoation(annotation);
			map.put(annotation.code(), serializer);
			return serializer;
		}
	}

}
