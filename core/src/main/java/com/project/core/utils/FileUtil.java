package com.project.core.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/14  14:41
 * @Version 1.0
 **/
public class FileUtil {


    public static String readFile(String file) {

        File f = new File(file);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new String(bytes, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static byte[] readFileBytes(String file) {

        File f = new File(file);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFile(String file, String encode) {

        File f = new File(file);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new String(bytes, encode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void writeFile(String fileName, String content) {

        File file = new File(fileName);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        byte[] bytes;
        try {
            bytes = content.getBytes("UTF8");
            Path target = Paths.get(fileName);
            Files.write(target, bytes);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String readImageBase64(String imgFile) {

        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] results = Base64.encodeBase64Chunked(data);
        return new String(results);
    }
}
