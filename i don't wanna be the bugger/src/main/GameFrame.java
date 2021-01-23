package main;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(32*25, 32*20);//长宽
        setVisible(true);//能否看见
        //得到JFrame边框
        Insets a=this.getInsets();
        //加上边框的长宽
        setSize(32*25+a.left+a.right,32*20+a.top+a.bottom);
        //设置是否能够改变窗体
        setResizable(false);
        //设置居中
        setLocationRelativeTo(null);
        //设置背景
        setBackground(new Color(195, 215, 247));
        //创建GameComponent对象
        GameComponent gameComPonent =new GameComponent(this);

        setTitle("I don't wanna be a bugger.  Death: 0");
        //将gameComponent加入GameFrame
        add(gameComPonent);
        //将gameComponent实现的keyListener加入GameFrame
        addKeyListener(gameComPonent);
    }
}
