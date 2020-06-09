/*
package com.demo.demo1;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.core.env.Environment;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;

public class Demo {

    */
/**本程序中用到的指令*//*

    public static final String Command_Connection = "connection" ;      //建立连接
    public static final String Command_Disconnection = "disconnection";//断开连接
    public static final String Command_Screen = "screen";               //显示被控端屏幕
    public static final String Command_Control="control";              //建立控制套接

    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        */
/**（一）主控端和被控端通过UDP进行指令交互*//*

        */
/*-主控端向被控端发送指令-*//*

        String command = "1";   //指令
        InetAddress inet = InetAddress.getByName("北京市"); //被控端地址
        int port = 8080;         //端口
        byte sp[] = command.getBytes();
        DatagramPacket packet = new DatagramPacket(sp, sp.length, inet, port);
        DatagramSocket sd = new DatagramSocket();
        sd.send(packet);
        */
/*-被控端接收指令-*//*

        DatagramSocket stockUDP = ...;
        DatagramPacket packet = "";
        stockUDP.receive(packet);
        String message=new String(packet.getData(),0,packet.getLength()); //得到指令

        */
/**（二）被控端向主控端传递图像*//*

        */
/*-被控端获取屏幕图像-*//*

        Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());//全屏区域
        Robot robot=new Robot();
        BufferedImage image = robot.createScreenCapture(rect);
        */
/*-开启图像传送线程-*//*

        Socket socket = ...;
        OutputStream out = socket.getOutputStream();
        BufferedImage image = null ;
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out);

        public void run(){
            while(true){
                image = ...; //获取屏幕图像
                encoder.encode(image); //发送图像给主控端
                Thread.sleep(new Date().getTime()); //图像采集时间间隔
            }
        }
        */
/*-主控端图像接收线程-*//*

        JPEGImageDecoder de = JPEGCodec.createJPEGDecoder(socket.getInputStream());


        public void run(){
            while(true){
                image = de.decodeAsBufferedImage();
                if (image != null) {
                    */
/*显示图像*//*

                }
            }
        }
    }


}
*/
