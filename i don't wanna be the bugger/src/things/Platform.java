package things;

import tools.ImageTool;
import tools.MusicTool;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Platform extends Thing{
    public int type;//1代表solidWithGreenLand,2代表land,3代表solidLand
    public static int GREEN=1, LAND =2,SOLID=3;//平台的类型常量
    public final static int UP=1,RIGHT=2,DOWN=3,LEFT=4,UPBACK=5,RIGHTBACK=6,DOWNBACK=7,LEFTBACK=8;//平台移动的方式常量
    private ImageIcon greenLand,land;//平台当前贴图
    private int row,col;//平台的行列大小
    private boolean onHere;//人物是否在该平台上
    private boolean movable;//平台是否可以移动
    private int direction;//平台移动的方式
    private int trapType;//平台的陷阱类型
    private boolean trigger=false;//陷阱是否触发
    private int speed;//平台移动的速度
    private Judge judgeP=new Judge(-3,-3,0,0);//平台移动的判定区域
    private int limit=-64;//平台移动的最终位置
    private int back=-999;//平台回移的位置
    private Clip music=null;//触发时的音乐
    public Platform(int x,int y,int type,int row,int col){
        super(x,y);
        this.type=type;
        if(type==1||type==2){
            if(type==1){
                music=MusicTool.blockChange;
            }
            else if(type==2){
                music=MusicTool.blockDown;
            }
            greenLand=ImageTool.land[0];
            land=ImageTool.land[1];
        }
        else if(type==3){
            music=MusicTool.blockDown;
            greenLand=ImageTool.land[2];
            land=ImageTool.land[1];
        }

        setWidth(col*land.getIconWidth());
        setHeight(row*land.getIconHeight());
        this.row=row;
        this.col=col;
        onHere=false;
        this.movable=false;
        this.direction=0;
        this.speed=0;
        this.trapType =0;

    }

    public Platform(int x, int y, int type, int row, int col,boolean movable,int direction,int speed,Judge judgeP){
        this(x,y,type,row,col);
        this.movable=movable;
        this.direction=direction;
        this.speed=speed;
        this.judgeP=judgeP;
        this.trapType =1;
        if(direction== Platform.DOWN)
            limit=21*32;
        else if(direction== Platform.RIGHT){
            limit=26*32;
        }
    }

    public Platform(int x, int y, int type, int row, int col,boolean movable,int direction,int speed,int limit,int back){
        this(x,y,type,row,col);
        this.movable=movable;
        this.direction=direction;
        this.speed=speed;
        this.trigger=true;
        this.trapType =1;
        this.limit=limit;
        this.back= back;

    }
    public Platform(int x, int y, int type, int row, int col,boolean movable,int direction,int speed, Judge judgeP,int limit){
        this(x,y,type,row,col,movable,direction,speed,judgeP);
        this.limit=limit;
    }

    public boolean isMovable() {
        return movable;
    }

    public Judge getJudgeP() {
        return judgeP;
    }

    public int getTrapType() {
        return trapType;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public int getBack() {
        return back;
    }

    public synchronized void judgeKidCrash(Kid kid){
        ImageIcon kidI=kid.getNowImage();
        int kidWidth=kidI.getIconWidth(),kidHeight= kidI.getIconHeight(),kidX=kid.getX(),kidY=kid.getY();
        int width=getWidth(),height=getHeight(),x=getX(),y=getY();


        if(kidX+kidWidth*2/3>=x&&kidX+ kidWidth /3<=x+width){
            if(kidY+kidHeight>=y&&kidY+kidHeight<=y+height){
                trapType =1;
                onHere=true;
                kid.setG(0);
                kid.setOnLand(true);
                kid.setY(y-kidHeight);
            }
            else if(kidY<=y+height&&kidY>=y ){
                kid.setHasJumped(false);
                kid.setY(y+height);
            }

        }

        if(onHere&&(kidX+kidWidth*2/3<=x||kidX+ kidWidth /3>=x+width||kidY+kidHeight<y-5)){
            onHere=false;
            kid.setOnLand(false);

        }
        if(kidY+kidHeight>y && kidY<=y+height){

           if(kidX+kidWidth>=x&& kidX+kidWidth<=x+10 ){
                kid.setX(x-kidWidth);
            }
           else if(kidX<=x+width&&kidX>=x+width-10){
               kid.setX(x+width);
           }
        }


    }

    public void draw(Graphics g){
        int x=getX(),y=getY();

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++) {

                if((type==1||type==3)&&i==0) {
                    g.drawImage(greenLand.getImage(),x+j* 32, y,null);

                }
                else g.drawImage(land.getImage(),x+j* 32, y+i * 32,null);
            }
        }

    }
    public void move(Kid kid){

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

    @Override
    public ImageIcon getNowImage() {
        return land;
    }
}
