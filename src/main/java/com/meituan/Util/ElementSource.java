package com.meituan.Util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Map;

/*
从yaml中获取元素信息
 */

public class ElementSource {

    public String getElementsource(String name){
        String testdatayaml = "src/main/resources/testdata.yaml";


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

}
