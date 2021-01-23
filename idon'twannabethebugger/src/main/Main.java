package main;

import java.awt.*;

public class Main {
    public static void main(String []args){
        EventQueue.invokeLater(new Runnable() {//启动系统线程的格式


            @Override
            public void run() {
                new GameFrame();   //GameFrame类继承于基本的JFrame框架格式
            }
        });
    }

}
