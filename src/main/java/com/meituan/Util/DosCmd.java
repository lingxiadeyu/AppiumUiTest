package com.meituan.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
执行windows下的doc命令，输入进入cmd输入dos命令
 */

public class DosCmd {
    private Log log = LogFactory.getLog(DosCmd.class);
    String osname = System.getProperty("os.name");

    //执行dos命令有返回结果
    public List<String> execCmdConsole(String cmdString){
        //定义一个list，用来接收输入dos命令后返回的信息
        List<String> dosres = new ArrayList<String>();
        Process process = null;

        try{

            if (osname.toLowerCase().contains("mac")){
                String[] command = {"/bin/sh","-c",cmdString};
                process = Runtime.getRuntime().exec(command);

            }else if (osname.toLowerCase().contains("win")){
                //执行dos命令，并把返回结果存储起来
                process = Runtime.getRuntime().exec("cmd /c "+cmdString);
            }

            //使用流的方式对结果解析
            InputStream in = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(in,"GBK"));
            String line = null;
            //使用while循环，如果没有结果可存储了就为null 就为false
            while ((line = bf.readLine()) != null){
                //没有办法过滤空行，所以就直接加入
//                if (bf.readLine().equals(null)){
//                    //返回的结果中有空行，过滤空行
//                }else {
//                    //存放到list中并返回
//                    dosres.add(line);
//                }
                dosres.add(line);
            }
            process.waitFor();
            process.destroy();
            log.debug("get result of command after execute dos command "+cmdString+" Succeed ");

        }catch (Exception e){
            log.error("get result of command after execute dos command "+cmdString+" Failed ",e);
        }
        return dosres;
    }

    //执行dos命令没有返回结果,这个方法不用也行，直接用上面的方法不接收返回信息就行
    public Boolean execCmd(String cmdString) throws IOException, InterruptedException {
        try{
            if (osname.toLowerCase().contains("mac")){
                String[] command = {"/bin/sh","-c",cmdString};
                Process process = Runtime.getRuntime().exec(command);

            }else if (osname.toLowerCase().contains("win")){
                Process process = Runtime.getRuntime().exec("cmd /c "+cmdString);
            }

            Thread.sleep(10000);
            System.out.println("dos命令执行完成");
            log.debug("execute  command "+cmdString+" Succeed");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("dos命令执行失败");
            log.error("execute  command "+cmdString+" Failed");
            return false;
        }

    }

}
