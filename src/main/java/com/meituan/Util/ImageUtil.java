package com.meituan.Util;

/*
比对测试截图，比对两个测试截图是否一致
 */

import javax.imageio.*;

import java.awt.image.*;
import java.awt.*;//Color
import java.io.*;
public class ImageUtil{

    public static int[] getData(String name)throws Exception{
        BufferedImage img = ImageIO.read(new File(name));
        BufferedImage slt = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        slt.getGraphics().drawImage(img,0,0,100,100,null);
//        System.out.println(slt.getWidth());
//        System.out.println(slt.getHeight());

        int[] data = new int[256];
        for(int x = 0;x<slt.getWidth();x++){
            for(int y = 0;y<slt.getHeight();y++){
                int rgb = slt.getRGB(x,y);
                Color myColor = new Color(rgb);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                data[(r+g+b)/3]++;
            }
        }
        return data;
    }
    public static float compare(int[] s,int[] t){
        float result = 0F;
        for(int i = 0;i<256;i++){
            int abs = Math.abs(s[i]-t[i]);
            int max = Math.max(s[i],t[i]);

            result += (1-((float)abs/(max==0?1:max)));

        }
        return (result/256)*100;
    }
    public static boolean compareImg(String srcName,String desName) throws Exception{
        //相似度是100f，可以降低相似度
        if(compare(getData(srcName), getData(desName))==100f){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args)throws Exception{
        System.out.println(compareImg("测试截图/000.png","测试截图/1.png"));
    }


}
