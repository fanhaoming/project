package com.project.test;

import com.project.core.utils.ListUtil;
import com.project.generator.CodeCreate;
import com.project.generator.CodeSchemaFormat;
import com.project.generator.config.Create;
import com.project.generator.database.beans.Table;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.project.generator.config.Configuration;
import com.project.generator.config.ConfigurationRead;
import com.project.generator.database.beans.Schema;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName test
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/16  16:36
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) throws Exception{
        String configFile = "E:\\IntelliJIDEA\\workspace\\test\\project\\template\\project.xml";
        //System.out.println("args:"+args[0]);
        String diretory = FilenameUtils.getFullPath(configFile);
        String createTable = "";
        if(StringUtils.isBlank(diretory)){
            File currentFile = new File(".");
            diretory = currentFile.getCanonicalPath();
        }
        System.out.println("diretory:"+diretory);
        Configuration configuration = ConfigurationRead.read(configFile);
        CodeSchemaFormat codeSchemaFormat = new CodeSchemaFormat();
        codeSchemaFormat.setConfiguration(configuration);
        Schema schema = codeSchemaFormat.build();
        CodeCreate code = new CodeCreate();

        //获取跳过生成的表
        List<String> skipTables = new ArrayList<String>();
        if (StringUtils.isNotBlank(configuration.getSkipTables())) {
            skipTables = ListUtil.select(Arrays.asList(configuration.getSkipTables().split(",")), (r) -> {
                return r;
            });
        }
        List<String> createTables = new ArrayList<String>();
        if(StringUtils.isBlank(createTable)){
            //如果从命令行中没有读取要创建的表格，则从配置文件中读取
            createTable = configuration.getCreateTables();
        }

        if(StringUtils.isNotBlank(createTable) ){
            createTables = ListUtil.select(Arrays.asList(createTable.split(",")), (r) -> {
                return r;
            });
        }

        for (int i = 0; i < schema.getTables().size(); i++) {
            final Table table = schema.getTables().get(i);

            if (skipTables.size() != 0) {
                boolean isSkip = false;
                for(int j=0;j<skipTables.size();j++){
                    String tableName = skipTables.get(j);
                    if(tableName.contains("*")) {
                        Pattern pattern = Pattern.compile("^" + tableName);
                        Matcher mather = pattern.matcher(table.getTableName());
                        if(mather.find()){
                            isSkip = true;
                            break;
                        }
                    }else {
                        if(table.getTableName().equals(tableName)){
                            isSkip = true;
                            break;
                        }
                    }
                }
                if(isSkip){
                    System.out.println("跳过" + table.getTableName());
                    continue;
                }
            }
            if(createTables.size()!= 0){
                boolean isSkip = true;
                for(int j=0;j<createTables.size();j++){
                    String tableName = createTables.get(j);
                    if(tableName.contains("*")){
                        Pattern pattern = Pattern.compile("^" + tableName);
                        Matcher mather = pattern.matcher(table.getTableName());
                        if(mather.find()){
                            isSkip = false;
                            break;
                        }
                    }
                    else{
                        if(table.getTableName().equals(tableName)){
                            isSkip = false;
                            break;
                        }
                    }
                }
                if(isSkip){
                    System.out.println("跳过" + table.getTableName());
                    continue;
                }
            }

            System.out.println("开始生成table" + table.getTableName());
            for (int j = 0; j < configuration.getCreates().size(); j++) {

                // System.out.println(configuration.getCreates().get(j).getType());

                Create create = configuration.getCreates().get(j);
                create.setOutput(FilenameUtils.concat(diretory, create.getOutput()));
                create.setTemplate(FilenameUtils.concat(diretory, create.getTemplate()));

                code.handleTemplate(schema, schema.getTables().get(i), configuration.getCreates().get(j));
            }
        }

        System.out.println("complete");

    }

}
