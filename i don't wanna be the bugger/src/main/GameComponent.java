package main;

import things.Kid;
import things.Level;
import tools.ImageTool;
import tools.MusicTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//GameComponent实现了KeyListener接口和ActionListener接口
//接口可以赋予这个类一些特性
public class GameComponent extends JComponent implements KeyListener, ActionListener {

    private Kid kid;
    private Level level;
    //一个计时器，类似于隔段时间执行一次监听的内容
    private Timer t=new Timer(15,this);

    private GameFrame gameFrame;


    public static boolean finish=false;


    public GameComponent(GameFrame gameFrame){
        //ImageTool用来预载图片到内存
//        ImageTool imageTool=new ImageTool();
        this.gameFrame=gameFrame;
        ImageTool.init();
        MusicTool.init();
        //kid是人物类
        kid=new Kid();
        //level是关卡类
        level=new Level(kid);
        //开始计时器
        t.start();
    }
    //重新设定关卡
    public void restart(){
        kid.reset(level.getKidSaveX(),level.getKidSaveY());
        if(level.getLevelNum()!=5) {
            level.setLevelNum(level.getRecordLevelNum());
        }
        else{
            level.setLevelNum(1);
        }
        level.reset();
        MusicTool.reset();
    }

    //实现KeyListener的方法
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        //按空格人物跳跃
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            kid.jump();
        }
        //按左右人物
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            kid.walk(0);
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            kid.walk(1);
        }
        //按r重新开始
        else if(e.getKeyChar()=='r'||e.getKeyChar()=='R'){
            restart();
        }
        else if(e.getKeyChar()=='z'||e.getKeyChar()=='Z'){
            level.saves.get(0).save(kid,level);
        }
        //在得到变化时立刻重绘
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //松开左右时人物恢复站立状态
            if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                kid.idle(0);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                kid.idle(1);
            }
    }

    //timer定时执行的方法
    @Override
    public void actionPerformed(ActionEvent e) {

        //如果人物没有死亡
        if(!kid.isDead()) {
            //根据人物状态改变人物坐标
            kid.run();

        }
        else{
            gameFrame.setTitle("I don't wanna be a bugger.  Death: "+Kid.deathNum);
            kid.getBlood().move();
        }
        //根据判断人物与关卡的关系改变机关等等
        level.scan();

        repaint();
    }
    //绘画方法
    @Override
    public void paintComponent(Graphics g) {
         Dimension d = getSize();


        //调用关卡的绘画
        level.draw(g,d.width,d.height);
        g.setColor(Color.black);
        //如果人物没死
        if(!kid.isDead())
            //调用人物的绘画
            kid.draw(g);
        //如果人物死了
        else {
            //画出“GameOver”图片
            ImageIcon gameOver=ImageTool.gameOver;

            g.drawImage(gameOver.getImage(), 32*25/2-gameOver.getIconWidth()/2,32*20/2-gameOver.getIconHeight()/2,null);
            kid.getBlood().draw(g);
        }

        // 方格线，用于设计关卡时方便观察
//        for(int i=0;i<=26;i++){
//            g.drawLine(0, 32*i, 2000, 32*i);
//            g.drawString(Integer.toString(i),0, 32*i);
//        }
//        for(int i=0;i<=36;i++) {
//            g.drawLine(32*i, 0, 32*i, 2000);
//            g.drawString(Integer.toString(i),32*i, 32);
//        }
    }
}
