package com.project.test;

import com.project.core.utils.ListUtil;
import com.project.generator.CodeCreate;
import com.project.generator.CodeSchemaFormat;
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

/**
 * @ClassName test
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/16  16:36
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) throws Exception{
      //  String configFile = args[0];
        //System.out.println("args:"+args[0]);
        String //diretory = FilenameUtils.getFullPath(configFile);
        diretory = ClassLoader.getSystemResource("test.xml").getPath();
        if(StringUtils.isBlank(diretory)){
            File currentFile = new File(".");
            diretory = currentFile.getCanonicalPath();
        }
        System.out.println("diretory:"+diretory);
        Configuration configuration = ConfigurationRead.read(diretory);
        CodeSchemaFormat codeSchemaFormat = new CodeSchemaFormat();
        codeSchemaFormat.setConfiguration(configuration);
       Schema schema = codeSchemaFormat.build();
       /*  CodeCreate code = new CodeCreate();

        List<String> skipTables = new ArrayList<String>();
        if (StringUtils.isBlank(configuration.getSkipTables()) == false) {
            skipTables = ListUtil.select(Arrays.asList(configuration.getSkipTables().split(",")), (r) -> {
                return r;
            });
        }
        Iterator iterator = skipTables.listIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());

        }*/

    }

}
