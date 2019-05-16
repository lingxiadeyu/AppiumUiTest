package com.meituan.Server;

import com.meituan.Util.DosCmd;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
启动服务端的步骤：
1.自动识别当前连接的设备，并且根据设备自动启动服务端
2.appium可以实现多台android设备同时测试,支持形式是一台设备一个服务端，多台多个服务端
3.appium -p 4490 -bp 3356 -U xxxxx
  -p 端口号表示脚本与服务端连接的端口
  -bp 表示服务端与设备进行通信的端口
  -U 表示针对某台设备启动的服务，值为设备的udid

代码实现思路：
1. 使用代码执行appium -p 4723 -bp 4724 -U xxxxx
   针对这条命令其中的变量是
   1）-p之后的端口号
   2）-bp之后的端口号
   3）设备的udid
2. 自动识别当前连接的设备，并且获取设备的udid
3. 自动生成可用的端口号(未占用的端口)，一组服务需要一对端口号(一个是-p，一个是-bp)
	指定一个起始的端口号，判断端口是否被占用，未被占用则添加到可用list里，并且自增1，一直循环到可用端口数量和设备数量一致

4. 将生成的端口号和获取的udid进行拼接，形成启动服务端的命令
   appium -p 4490 -bp 3356 -U x1 > 路径\x1.log
   appium -p 4491 -bp 3357 -U x2 > 路径\x2.log
5. 启动服务端根据生成的启动服务命令


注意：此方法也是有弊端的，需要先去执行该方法启动服务，然后去日志中查看日志是否启动成功，然后再单独的执行测试方法
 */
public class Servers {

    DosCmd dosCmd = new DosCmd();
    private Log log = LogFactory.getLog(Servers.class);
    String osname = System.getProperty("os.name");

    //判断端口号是否被占用
    public Boolean isPortUsed(int portnum){

        List<String> portres = new ArrayList<String>();
        String command = "";
        if (osname.toLowerCase().contains("mac")){
            command = "netstat -an | grep "+portnum;
        }else if (osname.toLowerCase().contains("win")){
            command = "netstat -ano | findstr "+portnum;
        }
        //执行命令
        portres = dosCmd.execCmdConsole(command);
        for (String s : portres){
            System.out.println(s);
        }

        //如果有返回结果就代表端口号被占用
        if (portres.size() > 0){
            System.out.println(String.valueOf(portnum)+"端口号被占用");
            log.debug(String.valueOf(portnum)+"port has been occupied");
            return true;
        }else {
            System.out.println(String.valueOf(portnum)+"端口号没有被占用");
            log.debug(String.valueOf(portnum)+"port unoccupied");
            return false;
        }

    }

    //生成多个端口号
    public List<Integer> GeneratPortList(int portstart,int devicetotal){
        List<Integer> ports = new ArrayList<Integer>();
        //当端口号数与设备数不一致时就生成端口号，目的就是想生成与设备数匹配的端口号
        while (ports.size() != devicetotal){
            if (portstart >= 0 && portstart <= 65535){
                //先判断端口号是否占用，没有占用再生成
                if (!isPortUsed(portstart)){
                    ports.add(portstart);
                }
                portstart++;
            }
        }
        return ports;
    }

    //获取当前可用设备的udid
    public List<String> getDevices(){
        List<String> devices = dosCmd.execCmdConsole("adb devices");
        List<String> getdevicesUdid = new ArrayList<String>();
        //因为有表头，所以从2开始
        if (devices.size() > 2){
            //因为有表头所以从get(1)开始，因为有空白行，所以要减1
            for (int i=1;i<devices.size()-1;i++){
                //“\t” 分隔符是\t 空格
                String[] devicesudid = devices.get(i).split("\t");
                //获取设备状态正常 为device的设备信息
                if (devicesudid[1].trim().equals("device")){
                    //获取设备号udid，trim是过滤空格
                    getdevicesUdid.add(devicesudid[0].trim());
                }
            }
        }
        return getdevicesUdid;
    }
    /**
     * 根据设备数量生成可用端口列表
     * @param start 端口起始号
     * @return 返回值是一个List<Integer>
     * @throws Exception
     */
    //
    public List<Integer> getPortList(int start) throws Exception{
        List<String> deviceList=getDevices();
        List<Integer> portList=GeneratPortList(start, deviceList.size());
        return portList;
    }

    //在启动服务前杀死进程
    public Boolean killserver() throws IOException, InterruptedException {
        String command = null;
        if (osname.toLowerCase().contains("mac")){
            command="killall node";
        }else if (osname.toLowerCase().contains("win")){
            command = "taskkill -F -PID node.exe";
        }else {
            command = "taskkill -F -PID node.exe";
        }
        if (dosCmd.execCmd(command)){
            System.out.println("node命令已杀死");
            log.debug("node命令已杀死");
            return true;
        }else {
            System.out.println("node命令没有被杀死");
            log.error("node命令没有被杀死");
            return false;
        }
    }


    //启动服务
    public List<Integer> execcommand() throws Exception {
        //获取所有设备
        List<String> devices = getDevices();
        //获取与设备数匹配的端口号，参数为起始端口号，是脚本与服务端之间的端口号，默认是4723
        List<Integer> portsp = getPortList(4723);
        //获取与设备数匹配的端口号，参数为起始端口号，是服务端与设备之间的端口号，默认是4724
        List<Integer> portsbp = getPortList(2232);
        for (int i : portsp){
            System.out.println("脚本与服务端之间的端口号："+i);
        }
        for (int i : portsbp){
            System.out.println("服务端与设备之间的端口号："+i);
        }

        //要执行的命令是appium -p 4723 -bp 4724 -U xxxxx
        //-p是脚本和服务端的端口号，-bp是服务端和设备的端口号

        //拼接命令
        String appium_commad= null;
        for(int i=0;i<devices.size();i++){
            appium_commad="appium -p "+portsp.get(i)+" -bp "+portsbp.get(i)+" -U "
                    + devices.get(i)+" > "+"D:/logs/"+devices.get(i).split(":")[0]+".log";
            System.out.println(appium_commad);
            dosCmd.execCmd(appium_commad);

        }

        return portsp;
    }

    //1、通过usb连接真机，启动模拟器，在cmd中输入adb devices查看有两台设备连接
    //2、手动执行Servers中的main方法，通过命令行的方式启动两台设备的服务
    //3、执行testng.xml配置thread-count=2，线程数为2，配置两个test信息，配置端口号和设备号等参数信息

    public static void main(String[] args) throws Exception {
        Servers servers = new Servers();
        //启动命令
        servers.execcommand();

//        if (servers.killserver())
//        {
//            //启动命令
//            servers.execcommand();
//
//        }


    }

}
