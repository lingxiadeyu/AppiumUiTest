package com.meituan.Util;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/*
该类中封装了从yaml中获取配置信息和从properties中获取配置信息、修改配置信息3个方法

测试类中直接调用该方法传入key值就可以了。
 */

public class ElementSource {
    private static String testdatayaml = "src/main/resources/testdata.yaml";
    private static String testdataproperties = "src/main/resources/testdata.properties";
    //从yaml文件中获取信息
    public String getElementsource(String name){

        try {
            Yaml yaml = new Yaml();
            Map map;
            map = (Map) yaml.load(new FileInputStream(testdatayaml));

            if (map == null){
                return null;
            }else if (map.get(name) == null){
                return null;
            }else {
                String resource = map.get(name).toString();
                return resource;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    //从properties配置文件中获取配置信息,其实不用写这个工具类，直接利用resourcebundle就可以直接获取配置信息
    public String getproelementsource(String keyname){
        Properties properties = new Properties();
        try{
            InputStream inputStream = new FileInputStream(testdataproperties);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            properties.load(bufferedReader);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        if (properties.containsKey(keyname)){
            return properties.getProperty(keyname);
        }else {
            System.out.println("获取的值不存在");
            return null;
        }

    }

    //根据某个key值修改properties配置文件中的value值
    public void setproelementsource(String keyname,String value ){
        try{
            //重新new一个properties
            Properties properties = new Properties();
            //要先创建一个inputstream的流，把文件中的内容赋予给properties，要不然properties的内容就是null，就会导致修改时把之前的内容覆盖
            InputStream inputStream = new FileInputStream(testdataproperties);
            properties.load(inputStream);
            //要关闭inputstream流
            inputStream.close();


            //创建输出流
            OutputStream outputStream = new FileOutputStream(testdataproperties);
            //创建bufferwriter 写入数据
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
            //往配置文件中写入数据，如果没有对应的key值则新增，如果有就修改
            properties.setProperty(keyname,value);
            //添加注释信息
            properties.store(bufferedWriter,keyname+" "+value);
            //关闭bufferwriter
            bufferedWriter.close();
            //关闭outputstream流
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }






}
