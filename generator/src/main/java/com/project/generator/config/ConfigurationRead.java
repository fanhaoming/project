package com.project.generator.config;

import com.project.core.utils.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationRead {

	public static Configuration read(String file) {
		Configuration configuration = Configuration.getInstance();

		String fileContent = FileUtil.readFile(file);
		try {
			Document doc = DocumentHelper.parseText(fileContent);
			Node databaseNode = doc.selectSingleNode("/config/database");
			Node tableNode = doc.selectSingleNode("/config/tables");
			Node createNode = doc.selectSingleNode("/config/creates");
			configuration.setDatabase(getByDataBaseNode(databaseNode));
			configuration.setTableMappings(getByTableNode(tableNode));
			configuration.setCreates(getByCreateNode(createNode));
			configuration.setApi(doc.selectSingleNode("/config/api").getText());
			configuration.setPackageName(doc.selectSingleNode("/config/package").getText());
			configuration.setOutputPath(doc.selectSingleNode("/config/output").getText());
			configuration.setBasePropertys(doc.selectSingleNode("/config/basePropertys").getText());
			configuration.setEnumNamespace(doc.selectSingleNode("/config/enumNamespace").getText());
			configuration.setDomainNamespace(doc.selectSingleNode("/config/domainNamespace").getText());
			configuration.setDictNamespace(doc.selectSingleNode("/config/dictNamespace").getText());
			configuration.setSkipTables(doc.selectSingleNode("/config/skipTables").getText());

			Node createTableNode = doc.selectSingleNode("/config/createTables");
			if(createTableNode!=null){
				configuration.setCreateTables(createTableNode.getText());
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return configuration;
	}

	@SuppressWarnings("rawtypes")
	private static List<TableMapping> getByTableNode(Node node) {
		List<TableMapping> tableMappings = new ArrayList<TableMapping>();
		List nodes = node.selectNodes("table");
		for (int i = 0; i < nodes.size(); i++) {
			Node table = (Node) nodes.get(i);
			String name = table.valueOf("@name");
			String className = table.valueOf("@class");
			TableMapping tableMapping = new TableMapping();
			tableMapping.setClassName(className);
			tableMapping.setName(name);

			tableMappings.add(tableMapping);
		}
		return tableMappings;
	}

	@SuppressWarnings("rawtypes")
	private static List<Create> getByCreateNode(Node node) {
		List<Create> creates = new ArrayList<Create>();
		List nodes = node.selectNodes("create");
		for (int i = 0; i < nodes.size(); i++) {
			Node childNode = (Node) nodes.get(i);
			String output = childNode.valueOf("@output");
			String type = childNode.valueOf("@type");
			String template = childNode.valueOf("@template");
			String replaceRegex = childNode.getText();

			Create create = new Create();
			create.setOutput(output);
			create.setType(type);
			create.setTemplate(template);
			create.setReplaceRegex(replaceRegex);
			creates.add(create);
		}

		return creates;
	}

	private static Database getByDataBaseNode(Node node) {
		Database database = new Database();
		String url = node.selectSingleNode("url").getText();
		String name = node.selectSingleNode("name").getText();
		String username = node.selectSingleNode("username").getText();
		String password = node.selectSingleNode("password").getText();
		database.setUrl(url);
		database.setTarget(name);
		database.setUsername(username);
		database.setPassword(password);
		return database;
	}

}
