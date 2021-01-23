package things;

import tools.ImageTool;
import tools.MusicTool;

import javax.swing.*;
import java.awt.*;

//人物类
public class Kid extends Thing{
    //人物当前的状态判定
    private boolean hasJumped =false,onLand=false,moveLeft=false,moveRight=false,isDead=false;
    //人物当前方向
    private int direction = 1;
    //人物跳跃记录
    public int jumpTime=0;
    //人物移动的速度
    private double xSpeed=0,ySpeed=0;
    //人物当前的贴图
    private ImageIcon nowImage;
    //人物跳跃时所受受向上的速度
    private double g=1;
    //血液
    private Blood blood=new Blood();
    //静态类计算人物死亡次数
    public static int deathNum=0;

    public Kid(){
        super(4*32,16*32);
        nowImage=ImageTool.rightStatus[0];
    }

    public int getWidth() {
        return nowImage.getIconWidth();
    }

    public int getHeight(){
        return nowImage.getIconHeight();
    }
    public ImageIcon getNowImage() {
        return nowImage;
    }

    public void setNowImage(ImageIcon nowImage) {
        this.nowImage = nowImage;
    }


    public void setOnLand(boolean onLand) {
        this.onLand = onLand;
    }

    public void setHasJumped(boolean hasJumped) {
        this.hasJumped = hasJumped;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public boolean isDead() {
        return isDead;
    }

    public Blood getBlood() {
        return blood;
    }

    //进入跳跃状态
    public void jump(){
        //跳跃记录加一
        jumpTime++;

        //跳跃记录小于等于2的时候可以跳跃
        if(jumpTime<=2){
            g=1;
            hasJumped=true;
            onLand=false;
            if(jumpTime==1){
                MusicTool.play(MusicTool.jump);
            }
            if(jumpTime==2){//当二段的时候跳跃速度减缓
                MusicTool.play(MusicTool.doubleJump);
                g=0.75;
            }
        }
        else hasJumped=false;
    }
    //进入行走状态
    public void walk(int direction){

        this.direction =direction;
        //根据方向改变人物的状态和水平速度
        if(direction==1){
            moveRight=true;
            xSpeed=3;
        }
        else if(direction==0){
            moveLeft=true;
            xSpeed=-3;

        }
    }
    //进入坠落状态
    public void fall(){
        g=0;
    }
    //进入站立状态
    public void idle(int direction){
        //根据方向改变人物的状态
        if(direction==1){
            moveRight=false;
        }
        else if(direction==0){
            moveLeft=false;
        }
    }

    //进入死亡状态
    public void die(){
        isDead=true;
        deathNum++;
        blood.setBlood(getX(),getY());
        setX(-3*32);
        setY(-3*32);
        MusicTool.play(MusicTool.death);

    }

    public void run() {

        //如果g<=0说明人物在下落
        if(g<=0){
            hasJumped =false;
            fall();
        }
        //人物状态为跳跃时，改变竖直方向的速度
        if(hasJumped) {
            g -= 0.04;
            ySpeed = -g * 7;
            setY((int)(getY()+ySpeed));
        }
        //人物状态不为跳跃时，g=0，改变竖直方向的速度
        else if(!hasJumped){
            g+=0.04;
            ySpeed=g*7;
            setY((int)(getY()+ySpeed));
            if(onLand){
                jumpTime=0;
            }
            else if(!onLand&&jumpTime==0){
                jumpTime=1;
            }
        }

        //如果状态为行走时，改变坐标
        if(moveRight||moveLeft)
            setX((int)(getX()+xSpeed));


    }
    public void draw(Graphics g){
        ImageIcon[] status=new ImageIcon[4];
        if(direction==1) {
           status=ImageTool.rightStatus;
        }
        else if(direction==0){
            status=ImageTool.leftStatus;
        }
        if (hasJumped) {
            nowImage=status[2];
        } else if (!onLand) {
            nowImage=status[3];
        } else if (moveLeft||moveRight) {
            nowImage=status[1];
        } else {
            nowImage=status[0];
        }
        g.drawImage(nowImage.getImage(),getX(),getY(),null);
    }

    public void reset(int kidStartX,int kidStartY){

        setX(kidStartX);
        setY(kidStartY);
//        nowImage=ImageTool.rightStatus[1];
        hasJumped = false;
        onLand=false;
        isDead = false;
        g = 0;
    }
}