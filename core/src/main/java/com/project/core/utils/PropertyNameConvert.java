package com.project.core.utils;

public class PropertyNameConvert {
	public static String columnToProperty(String column) {
		String property = "";
		boolean nextNeedUpper = false;
		for (int i = 0; i < column.length(); i++) {
			char c = column.charAt(i);
			if (c == '_') {
				nextNeedUpper = true;
				continue;
			}

			if (nextNeedUpper == false)
				property += String.valueOf(c);
			else {
				property += String.valueOf(c).toUpperCase();
				nextNeedUpper = false;
			}
		}
		return property;
	}

	public static String classToProperty(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	public static String propertyToClass(String property) {
		return property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	public static String propertyToColumn(String property) {
		String column = "";
		for (int i = 0; i < property.length(); i++) {
			char c = property.charAt(i);
			if (Character.isUpperCase(c) && i!=0) {
				column += "_";
			}
			column += String.valueOf(c).toLowerCase();
		}
		return column;
	}
}
