package com.project.generator;


import com.project.core.utils.ListUtil;
import com.project.generator.config.Configuration;
import com.project.generator.config.TableMapping;

public class TableNameFormat {

	public static String getClassName(String tableName) {
		Configuration configuration = Configuration.getInstance();
		TableMapping tableMapping = ListUtil.find(configuration.getTableMappings(), mapping -> {
			return mapping.getName().equals(tableName);
		});

		if (tableMapping == null) {
			String[] names = tableName.split("_");
			String className = "";
			for (int i = 1; i < names.length; i++) {
				className += names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
			}
			return className;

		} else {
			return tableMapping.getClassName();
		}

	}

	public static String getInstanceName(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}
}
