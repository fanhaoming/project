package com.project.generator;

import com.project.core.utils.FileUtil;
import com.project.core.utils.FreeMarkerTemplate;
import com.project.generator.config.Configuration;
import com.project.generator.config.Create;
import com.project.generator.database.beans.Schema;
import com.project.generator.database.beans.Table;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeCreate {

	public void handleTemplate(Schema schema, Table table, Create create) {

		Configuration configuration = Configuration.getInstance();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("schema", schema);
		data.put("table", table);
		data.put("configuration", configuration);
		data.put("create", create);

		String content = FreeMarkerTemplate.getInstance().processFile(create.getTemplate(), data);
		String path = create.getOutput();
		
		path = path.replaceAll("\\{tableName\\}", table.getClassName());
		path = path.replaceAll("\\{tableInstanceName\\}", table.getInstanceName());
		
		File outFile = new File(path);
		
		if(StringUtils.isBlank(content)){
			//System.out.println("内容为空,跳过  " + create.getType() +  " " + outFile.getName()+ " " + table.getClassName());
			return;
		}
		File file = new File(path);
		if (file.exists()) {
			
			if (!StringUtils.isNotBlank(create.getReplaceRegex())) {
				String fileContent = FileUtil.readFile(path);

				Pattern pattern = Pattern.compile(create.getReplaceRegex(), Pattern.DOTALL);
				Matcher mather = pattern.matcher(content);

				String replaceContent = "";
				String replaceOriginalContent = "";
				while (mather.find()) {
					replaceContent = mather.group();
				}
				
				mather = pattern.matcher(fileContent);
				while (mather.find()) {
					replaceOriginalContent = mather.group();
				}
				
				if(StringUtils.isBlank(replaceOriginalContent)){
					//System.out.println("原文件已去掉生成标记，跳过 " + create.getType() +  " " + outFile.getName()+ " " + table.getClassName());
					return;
				}
								
				String mFileContent = replaceContent.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
				String mContent = replaceOriginalContent.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
				if (mFileContent.equals(mContent)) {
					//System.out.println("内容相同，跳过 " + create.getType() +  " " + outFile.getName() + " " + table.getClassName());
					return;
				}
				
				fileContent = pattern.matcher(fileContent).replaceAll(replaceContent);
				System.out.println("替换生成 " + create.getType() +  " " + outFile.getName()+ " " + table.getClassName());
				FileUtil.writeFile(path, fileContent);
			}
		} else {
			System.out.println("新生成文件 " + create.getType() +  " " + outFile.getName() + " " + table.getClassName());
			FileUtil.writeFile(path, content);
		}

	}
}
