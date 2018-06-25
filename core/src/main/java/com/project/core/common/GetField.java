package com.project.core.common;

import java.lang.reflect.Field;
import java.util.HashMap;

public class GetField {

	private static GetField instance;

	/**
	 * Singleton access point to the manager.
	 */
	public static GetField getInstance() {
		synchronized (GetField.class) {
			if (instance == null) {
				instance = new GetField();
				instance.init();
			}
		}
		return instance;
	}

	HashMap<Class<?>, Field[]> classMap;

	private void init() {
		classMap = new HashMap<Class<?>, Field[]>();
	}

	public Field getByName(Class<?> clazz, String name) {
		Field[] fields = null;

		if (!classMap.containsKey(clazz)) {
			fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
			}
			classMap.put(clazz, fields);

		} else {
			fields = (Field[]) classMap.get(clazz);
		}

		for (Field field : fields) {
			if (field.getName().endsWith(name)) {
				return field;
			}
		}

		return null;
	}
}
