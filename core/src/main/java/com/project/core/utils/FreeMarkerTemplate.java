package com.project.core.utils;


import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

public class FreeMarkerTemplate {

	private static FreeMarkerTemplate instance;

	/**
	 * Singleton access point to the manager.
	 */
	public static FreeMarkerTemplate getInstance() {
		synchronized (FreeMarkerTemplate.class) {
			if (instance == null) {
				System.out.println("init");
				instance = new FreeMarkerTemplate();
				instance.init();
			}
		}
		return instance;
	}

	private Configuration cfg = null;
	private HashMap<String, String> templateMap = null;

	private void init() {
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setDefaultEncoding("UTF-8");
		templateMap = new HashMap<String, String>();
	}

	public void setTemplateString(String key, ITemplateLoad templateLoad) {
		String templateContent = null;
		if (!templateMap.containsKey(key)) {
			templateContent = templateLoad.load();
			templateMap.put(key, templateContent);

		} else {
			templateContent = templateMap.get(key);
		}
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("Template_" + key, templateContent);
		cfg.setTemplateLoader(stringLoader);
	}

	public String processFile(String fileName, HashMap<String, Object> data) {
		try {
			
			String directory = FilenameUtils.getFullPath(fileName);
			
			cfg.setDirectoryForTemplateLoading(new File(directory));
 
			Template template = cfg.getTemplate(FilenameUtils.getName(fileName));
			StringWriter writer = new StringWriter();
			template.process(data, writer);
			return writer.toString();
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public String process(String key, HashMap<String, Object> data) {

		try {

			Template template = cfg.getTemplate("Template_" + key, "utf-8");
			StringWriter writer = new StringWriter();
			template.process(data, writer);
			return writer.toString();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public void processOutput(String key, Writer writer, HashMap<String, Object> data) {

		try {
			Template template = cfg.getTemplate("Template_" + key, "utf-8");

			template.process(data, writer);

		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public interface ITemplateLoad {
		public String load();
	}
}