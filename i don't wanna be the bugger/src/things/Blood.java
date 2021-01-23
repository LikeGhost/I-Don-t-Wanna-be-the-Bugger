package things;

import tools.ImageTool;

import javax.swing.*;
import java.awt.*;

public class Blood {
    private int []bloodX,bloodY;
    private int[] xSpeed,ySpeed;
    private int [] direction;
    private ImageIcon blood;
    private boolean flag=false;
    public Blood(){
        xSpeed =new int[500];
        ySpeed =new int[500];
        direction=new int[500];
        bloodX=new int[500];
        bloodY=new int[500];
        blood= ImageTool.blood;
    }
    public void setBlood(int kidX,int kidY){

        for(int i=0;i<500;i++){
            xSpeed[i]= (int) (Math.random()*(20));
            ySpeed[i]=(int) (Math.random()*(20));
            direction[i]=(int) (Math.random()*(5));
            bloodX[i]=kidX;
            bloodY[i]=kidY;
        }
    }
    public synchronized void move(){
        for(int i=0;i<500;i++){
           if(direction[i]==1){
               bloodX[i]+=xSpeed[i];
               bloodY[i]+=ySpeed[i];
           }
           else  if(direction[i]==2){
               bloodX[i]+=xSpeed[i];
               bloodY[i]-=ySpeed[i];
           }
           else if(direction[i]==3){
                bloodX[i]-=xSpeed[i];
                bloodY[i]+=ySpeed[i];
            }
           else if(direction[i]==4){
               bloodX[i]-=xSpeed[i];
               bloodY[i]-=ySpeed[i];
           }
        }
    }
    public void draw(Graphics g){
        for(int i=0;i<500;i++){
            g.drawImage(blood.getImage(),bloodX[i],bloodY[i],null);
        }

    }

    public void setBloodX(int[] bloodX) {
        this.bloodX = bloodX;
    }

    public void setBloodY(int[] bloodY) {
        this.bloodY = bloodY;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
