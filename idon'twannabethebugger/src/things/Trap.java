package things;

import tools.ImageTool;
import tools.JudgeCrashTool;
import tools.MusicTool;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Trap extends Thing {
    public final static int UP=1,RIGHT=2,DOWN=3,LEFT=4,UPBACK=5,RIGHTBACK=6,DOWNBACK=7,LEFTBACK=8;
    public final static int SPIKE=1,AIRPLANE=2,TREE=3,APPLE=4,DEADMAN=5;
    private int direction;
    private ImageIcon nowImage;
    private boolean movable;
    private int speed;
    private Judge judgeP=new Judge(-3,-3,0,0);
    private int trapType =9;
    private boolean trigger=false;
    private int limit=-32;
    private int back;
    private Clip music=null;
    private int type;
    public Trap(int x, int y,int type,int towards) {
        super(x,y);
        this.type=type;
        if(type==1){
            nowImage=ImageTool.trap1[towards];
            music= MusicTool.spikeTrap;
        }
        else if(type==2){
            nowImage=ImageTool.airplane[towards];
            music=MusicTool.airplaneTrap;

        }
        else if(type==3){
            nowImage=ImageTool.tree[towards];
        }
        else if(type==4){
            nowImage=ImageTool.apple[towards];
            music=MusicTool.appleTrap;
        }
        else if(type==5){
            nowImage=ImageTool.deadMan;
        }
        setWidth(nowImage.getIconWidth());
        setHeight(nowImage.getIconHeight());
    }

    public Trap(int x, int y,int type,int towards,boolean movable,int direction,int speed,Judge judgeP) {
        this(x,y,type,towards);
        this.movable=movable;
        this.direction=direction;
        this.speed=speed;
        this.judgeP=judgeP;
        this.trapType =1;
        if(direction== Trap.DOWN)
            limit=21*32;
        else if(direction== Trap.RIGHT){
            limit=26*32;
        }
    }

    public Trap(int x, int y,int type,int towards,boolean movable,int direction,int speed,int limit,int back) {
        this(x,y,type,towards);
        this.movable=movable;
        this.direction=direction;
        this.speed=speed;
        this.trapType =1;
        if(direction== Trap.DOWN)
            limit=21*32;
        else if(direction== Trap.RIGHT){
            limit=26*32;
        }
        this.trigger=true;
        this.back=back;
        this.limit=limit;

    }
    public Trap(int x, int y,int type,int towards,boolean movable,int direction,int speed,Judge judgeP,int limit) {
        this(x,y,type,towards,movable,direction,speed,judgeP);

        this.limit=limit;

    }

    public Trap(int x, int y,int type,int towards,boolean movable,int direction,int speed,Judge judgeP,int limit,int back) {
        this(x,y,type,towards,movable,direction,speed,judgeP);

        this.limit=limit;
        this.back=back;
    }


    @Override
    public ImageIcon getNowImage() {
        return nowImage;
    }

    public boolean isMovable() {
        return movable;
    }

    public int getTrapType() {
        return trapType;
    }

    public Judge getJudgeP() {
        return judgeP;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setJudgeP(Judge judgeP) {
        this.judgeP = judgeP;
    }

    public void setTrapType(int trapType) {
        this.trapType = trapType;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setNowImage(ImageIcon nowImage) {
        this.nowImage = nowImage;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public void judgeKidCrash(Kid kid){

        if(JudgeCrashTool.judgeCrash(kid,this)){
//            kid.setDead(true);
            kid.die();
        }
    }

    public void draw(Graphics g){
        int x=getX(),y=getY();
        g.drawImage(nowImage.getImage(),x, y,null);
    }
    public void move(){
        if(!trigger||trapType ==1) {
            if(!trigger&&music!=null){
                MusicTool.play(music);
            }
            if (direction==UP||direction==UPBACK){
                setY(getY()-speed);

                if(getY()<=limit){
                    setY(limit);
                    trapType =9;
                    if(direction==UPBACK){
                        int t=limit;
                        limit=back;
                        back=t;
                        direction=DOWNBACK;
                        trapType=1;
                    }
                }

            }
            else if(direction==RIGHT||direction==RIGHTBACK){
                setX(getX()+speed);
                if(getX()>=limit){
                    setX(limit);
                    trapType =9;
                    if(direction==RIGHTBACK){
                        int t=limit;
                        limit=back;
                        back=t;
                        direction=LEFTBACK;
                        trapType=1;
                    }
                }
            }
            else if(direction==DOWN||direction==DOWNBACK){
                setY(getY()+speed);
                if(getY()>=limit){
                    setY(limit);
                    trapType =9;
                    if(direction==DOWNBACK){
                        int t=limit;
                        limit=back;
                        back=t;
                        direction=UPBACK;
                        trapType=1;
                    }
                }
            }
            else if(direction==LEFT||direction==LEFTBACK){
                setX(getX()-speed);
                if(getX()<=limit){
                    setX(limit);
                    trapType =9;
                    if(direction==LEFTBACK){
                        int t=limit;
                        limit=back;
                        back=t;
                        direction=RIGHTBACK;
                        trapType=1;
                    }
                }
            }

            trigger=true;
        }
    }


}
