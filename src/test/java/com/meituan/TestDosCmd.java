package com.meituan;

import com.meituan.Util.DosCmd;
import com.meituan.Util.ElementSource;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TestDosCmd {
    DosCmd dosCmd = new DosCmd();
    ElementSource elementSource = new ElementSource();

    @Test(description = "测试doscmd")
    public void testdoscmd() throws IOException, InterruptedException {

        System.out.println(elementSource.getproelementsource("username"));
        System.out.println(elementSource.getproelementsource("password1"));

        elementSource.setproelementsource("username","qinzhenxia");
        elementSource.setproelementsource("password1","123456");
        System.out.println(elementSource.getproelementsource("username"));
        System.out.println(elementSource.getproelementsource("password1"));



    }
}
