package com.meituan.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreateIDCardNo {
    //18位身份证的含义：
    //1-2位省、自治区、直辖市代码；
    //3-4位地级市、盟、自治州代码
    //5-6位县、县级市、区代码
    //7-14位出生年月日，比如19670401代表1967年4月1日；
    //15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
    //18位为校验码，0-9和X。
    //作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，
    // 计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替
    //因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10


    //从String[]中随机获取一个字符串
    public String RandomOne(String[] s){
        return s[new Random().nextInt(s.length-1)];
    }
    //随机生成两位数的字符,不足两位的补0
    public String RandomCityCode(int max){
        int i = new Random().nextInt(max)+1;
//        return i > 9 ? i + "" : "0" + i;
        if (i > 9){
            return String.valueOf(i);
        }else{
            return "0"+String.valueOf(i);
        }
    }

    //随机生成minAge到maxAge年龄段的人的生日日期
    public String RandomBirth(int minAge,int maxAge){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        int randomday = new Random().nextInt(365*minAge)+new Random().nextInt(365*(maxAge-minAge));
        date.set(Calendar.DATE,date.get(Calendar.DATE)-randomday);
        return simpleDateFormat.format(date.getTime());
    }
    //随机生成身份证号

    public String getRandomID(){
        String id = "";
        // 随机生成省、自治区、直辖市代码 1-2，71、81、82是台湾、香港、澳门的代码
        String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82" };
        String province = RandomOne(provinces);
        // 随机生成地级市、盟、自治州代码 3-4  其中，01-20，51-70表示地级市；21-50表示地区（自治州、盟）。
        String city = RandomCityCode(70);
        // 随机生成县、县级市、区代码 5-6  01-18表示地级市、自治州、地区、盟辖县级市；21-80表示县（旗）；81-99表示省直辖县级行政单位
        String county = RandomCityCode(18);
        String birth = RandomBirth(20,50);
        String no = new Random().nextInt(899) + 100+"";//加100保证是3位数
        String checks[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X" };
        String check = RandomOne(checks);
        // 拼接身份证号码
        id = province + city + county + birth + no + check;
        return id;

    }
    public static void main(String[] args) {
        CreateIDCardNo createIDCardNo = new CreateIDCardNo();
        System.out.println(createIDCardNo.RandomCityCode(30));
        System.out.println(createIDCardNo.RandomBirth(20,30));

        System.out.println(createIDCardNo.getRandomID());


    }

}
