package com.meituan.Base;

/*
为测试用例提供测试数据
*/
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class GetDataProvider {

    @DataProvider(name = "getdata")
    public static Object[][] dataprovider(Method method){
        Object[][] result = null;

        if (method.getName().equals("test004")){
            result = new Object[][]{
                    {"北京"}
            };
        }
        if (method.getName().equals("test007")){
            result = new Object[][]{
                    {"果多美"}
            };
        }
        if (method.getName().equals("test008")){
            result = new Object[][]{
                    {"上海"}
            };
        }
        if (method.getName().equals("test022")){
            result = new Object[][]{
                    {"亮健容天土锅羊蝎子（安贞店）"}
            };
        }
        if (method.getName().equals("test023") ||
                method.getName().equals("test024") ||
                method.getName().equals("test025") ||
                method.getName().equals("test026") ||
                method.getName().equals("test027") ||
                method.getName().equals("test028")){
            result = new Object[][]{
                    {"亮健容天土锅羊蝎子（安贞店）"}
            };
        }
        if (method.getName().equals("test029") ||
                method.getName().equals("test030") ||
                method.getName().equals("test031")){
            result = new Object[][]{
                    {"汉堡王（安贞环贸店）"}
            };
        }
        if (method.getName().equals("test034")){
            result = new Object[][]{
                    {"老街时光牛肉汤&凉皮（第十四档口-顺诚美食城店）"},
                    {"麦特派炸鸡汉堡（国展宾馆店）"},
                    {"太太好粥（第B4档口-云加厨房美食城店）"},
                    {"35秒烤肉拌饭（第4档口火赢美食城店）"}
            };
        }
        return result;


    }
}
