package com.meituan.Util;
/*
生成随机数，供测试数据使用
生成身份证号码
 */

import java.util.Random;

public class RandomUtil {

    //随机生成指定长度的字符串
    public static  String getRndStrByLen(int LengthofString){

        int i,count = 0;
        final String chars = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        String[] charArr = chars.split(",");

        StringBuffer randomstr = new StringBuffer("");
        Random random = new Random();
        int strlen = charArr.length;

        while (count < LengthofString){
            i = random.nextInt(strlen);//生成0~strlen内的随机数
            randomstr.append(charArr[i]);//把随机数作为下标，获取数组中的值
            count++;

        }
        return randomstr.toString().toLowerCase();

    }

    //随机生成指定长度的中文
    public static String getRndStrZWByLen(int LengthofString){
        int i,count=0;
        final String chars = "秦,王,李,徐,司,杨,谢,陈,丛,张,崔,石,齐,谷,吴,文";
        String[] chararr = chars.split(",");
        int charlen = chararr.length;
        Random random = new Random();

        StringBuffer stringBuffer = new StringBuffer("");

        while (count < LengthofString){
            i = random.nextInt(charlen);//生成0~strlen内的随机数
            stringBuffer.append(chararr[i]);//把随机数作为下标，获取数组中的值
            count++;
        }
        return stringBuffer.toString().toLowerCase();
    }

    //随机生成指定长度的数字字符串
    public static String getRndStrNumByLen(int LengthofString){
        int i,count=0;
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer("");
        while (count < LengthofString){
            i = random.nextInt(9);//随机生成0到9之间的数字
            //如果第一位数字为0，就不加入stringbuffer中
            if (i == 0 && count ==0){

            }else {
                stringBuffer.append(String.valueOf(i));
            }
            count++;
        }
        return stringBuffer.toString();


    }

    //生成指定范围内的整数
    public static int getRndNumByDecide(int min,int max){
        Random random = new Random();
        //先生成0到max的随机数，然后求余，然后再加上min
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

    //生成指定范围的浮点数
    private static float randomFloat(int min,int max){
        Random random = new Random();
        float x=min;//x=10
        while(x<=min){
            double db = random.nextDouble() * max * 10;
            x = ((int) db) / 10f;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(getRndStrByLen(5));
        System.out.println(getRndStrZWByLen(3));
        System.out.println(getRndStrNumByLen(5));

        System.out.println(getRndNumByDecide(10,20));
        System.out.println(randomFloat(10,20));

    }

}
