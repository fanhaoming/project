package com.project.generator.config;

import java.util.List;

public class Configuration {

	private static Configuration instance;

	/**
	 * Singleton access point to the manager.
	 */
	public static Configuration getInstance() {
		synchronized (Configuration.class) {
			if (instance == null) {
				instance = new Configuration();
			}
		}
		return instance;
	}

	private Database database;
	private List<TableMapping> tableMappings;
	private List<Create> creates;

	public List<Create> getCreates() {
		return creates;
	}

	public void setCreates(List<Create> creates) {
		this.creates = creates;
	}

	private String packageName;
	private String outputPath;
	private String basePropertys;
	private String enumNamespace;
	private String domainNamespace;
	private String dictNamespace;

	private String skipTables;
	private String createTables;
	private String api;

	public String getApi() {
		return api;
	}

	public String getDictNamespace() {
		return dictNamespace;
	}

	public void setDictNamespace(String dictNamespace) {
		this.dictNamespace = dictNamespace;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSkipTables() {
		return skipTables;
	}

	public void setSkipTables(String skipTables) {
		this.skipTables = skipTables;
	}

	public String getDomainNamespace() {
		return domainNamespace;
	}

	public void setDomainNamespace(String domainNamespace) {
		this.domainNamespace = domainNamespace;
	}

	public String getEnumNamespace() {
		return enumNamespace;
	}

	public void setEnumNamespace(String enumNamespace) {
		this.enumNamespace = enumNamespace;
	}

	public String getBasePropertys() {
		return basePropertys;
	}

	public void setBasePropertys(String basePropertys) {
		this.basePropertys = basePropertys;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public List<TableMapping> getTableMappings() {
		return tableMappings;
	}

	public void setTableMappings(List<TableMapping> tableMappings) {
		this.tableMappings = tableMappings;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getCreateTables() {
		return createTables;
	}

	public void setCreateTables(String createTables) {
		this.createTables = createTables;
	}

}
