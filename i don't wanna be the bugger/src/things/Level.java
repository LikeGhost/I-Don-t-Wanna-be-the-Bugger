package things;

import tools.ImageTool;
import tools.JudgeCrashTool;
import tools.MusicTool;

import java.awt.*;
import java.util.ArrayList;

public class Level {

    Kid kid;//人物类
    private ArrayList<Platform>platforms=new ArrayList<>();//平台对象
    private ArrayList<Trap> trapsDown =new ArrayList<>();//置于最底层的陷阱对象
    private ArrayList<Trap>trapsUp=new ArrayList<>();//置于最底层的陷阱对象
    private ArrayList<Trap>trapsNothing=new ArrayList<>();//不会扫描的陷阱对象等于是没用伤害的贴图
    private ArrayList<Judge>judges=new ArrayList<>();//预定义的判定区域
    public ArrayList<Save>saves=new ArrayList<>();//存档对象
    private int levelNum;//当前关卡数
    private int recordLevelNum;//存档关卡数
    private int kidSaveX, kidSaveY;//存档人物位置

    public Level(Kid kid){
        this.kid=kid;
        levelNum=5;
        recordLevelNum=1;
        kidSaveX =4*32;
        kidSaveY =15*32;
        reset();
    }

    public int getKidSaveX() {
        return kidSaveX;
    }

    public int getKidSaveY() {
        return kidSaveY;
    }

    public void setKidSaveX(int kidSaveX) {
        this.kidSaveX = kidSaveX;
    }

    public void setKidSaveY(int kidSaveY) {
        this.kidSaveY = kidSaveY;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public int getRecordLevelNum() {
        return recordLevelNum;
    }

    public void setRecordLevelNum(int recordLevelNum) {
        this.recordLevelNum = recordLevelNum;
    }

    public void reset(){
//      Platform(int x, int y, int type, int row, int col,boolean movable,int direction,int speed)
//      Trap(int x, int y,int type,int towards,boolean movable,int direction,int speed,Judge judgeP,int limit)
        platforms.clear();
        trapsDown.clear();
        trapsUp.clear();
        trapsNothing.clear();
        judges.clear();
        saves.clear();


        //level0
        if(levelNum==0){
            platforms.add(new Platform(20*32,17*32, Platform.GREEN,1,5,true,Platform.UPBACK,1,7*32,17*32));
        }

        //level1
        if(levelNum==1) {

//            saves.add(new Save(3*32,4*32));
            saves.add(new Save(4*32,16*32));

            judges.add(new Judge(22 * 32, 0 , 32, 32 * 7));
            judges.add(new Judge(3 * 32, 3 * 32, 32 * 2, 32));

            platforms.add(new Platform(0, 32 * 17, 1, 3, 9));
            platforms.add(new Platform(22 * 32, 32 * 6, 1, 14, 3));
            platforms.add(new Platform(10 * 32, 32 * 14, 1, 2, 2));
            platforms.add(new Platform(15 * 32, 32 * 14, 1, 2, 2, true, Platform.RIGHT, 3, new Judge(13 * 32, 32 * 9, 32, 32 * 3), 16 * 32));
            platforms.add(new Platform(19 * 32, 32 * 13, 1, 2, 2));
            platforms.add(new Platform(18 * 32, 32 * 9, 1, 2, 2));

            platforms.add(new Platform(9 * 32, 32 * -2, 1, 2, 4, true, Platform.DOWN, 5, judges.get(0), 32 * 5));
            platforms.add(new Platform(2 * 32, 32 * -2, 1, 2, 4, true, Platform.DOWN, 5, judges.get(0), 32 * 5));


            trapsDown.add(new Trap(10 * 32, 13 * 32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(16 * 32, 13 * 32, Trap.SPIKE, Trap.UP, true, Trap.RIGHT, 1, new Judge(17 * 32, 13 * 32, 32, 32), 17 * 32));

            trapsDown.add(new Trap(22 * 32, 6 * 32, Trap.SPIKE, Trap.UP, true, Trap.UP, 2, judges.get(0), 32 * 5));
            trapsDown.add(new Trap(23 * 32, 6 * 32, Trap.SPIKE, Trap.UP, true, Trap.UP, 2, judges.get(0), 32 * 5));
            trapsDown.add(new Trap(24 * 32, 6 * 32, Trap.SPIKE, Trap.UP, true, Trap.UP, 2, judges.get(0), 32 * 5));

            trapsDown.add(new Trap(10 * 32, -3 * 32, Trap.SPIKE, Trap.UP, true, Trap.DOWN, 5, judges.get(0), 32 * 4));
            trapsDown.add(new Trap(11 * 32, -3 * 32, Trap.SPIKE, Trap.UP, true, Trap.DOWN, 5, judges.get(0), 32 * 4));
            trapsDown.add(new Trap(2 * 32, -3 * 32, Trap.SPIKE, Trap.UP, true, Trap.DOWN, 5, judges.get(0), 32 * 4));
            trapsDown.add(new Trap(5 * 32, -3 * 32, Trap.SPIKE, Trap.UP, true, Trap.DOWN, 5, judges.get(0), 32 * 4));

        }

        //level2
        else if(levelNum==2){

            saves.add(new Save(32,5*32));
            judges.add(new Judge(6*32,6*32,2*32,3*32));
            judges.add(new Judge(11*32,6*32,32*2,32*2));
            judges.add(new Judge(11*32, 14*32,5*32,3*32));
            judges.add(new Judge(21*32+16,14*32,32,4*32));

            platforms.add(new Platform(32,9*32,1,3,5,true, Platform.RIGHT,7,judges.get(0),6*32));
            platforms.add(new Platform(0,32*6,1,6,3));
            platforms.add(new Platform(0,32*12,2,8,2));
            platforms.add(new Platform(3*32,9*32,1,3,3));


            platforms.add(new Platform(5*32,4*32,1,2,6));
            platforms.add(new Platform(8*32,6*32,2,4,3));
            platforms.add(new Platform(8*32,8*32,2,2,3,true, Platform.RIGHT,7,judges.get(1),11*32));

            platforms.add(new Platform(13*32,4*32,1,4,2));
            platforms.add(new Platform(15*32,5*32,1,3,3));
            platforms.add(new Platform(18*32,6*32,1,2,4));

            platforms.add(new Platform(35*32,11*32,1,2,9,true,Platform.LEFT,32,new Judge(22*32,8*32,3*32,5*32),16*32));

            platforms.add(new Platform(7*32,19*32,1,1,7,true, Platform.UP,5,new Judge(21*32,15*32,32,32),15*32));

            platforms.add(new Platform(4*32,18*32,1,2,21));



            trapsUp.add(new Trap(-9*32,4*32,Trap.AIRPLANE,Trap.RIGHT,true,Trap.RIGHT,10,judges.get(0),34*32));

            trapsDown.add(new Trap(6*32,3*32,Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(9*32,3*32,Trap.SPIKE, Trap.UP,true,Trap.LEFT,2,new Judge(7*32,3*32,2*32,32),7*32));

            trapsUp.add(new Trap(-9*32,4*32,Trap.AIRPLANE,Trap.RIGHT,true,Trap.RIGHT,9,judges.get(1),34*32));


            trapsDown.add(new Trap(14*32,4*32, Trap.SPIKE, Trap.UP,true, Trap.UP,2,new Judge(14*32,3*32,32,2*32),3*32));
            trapsDown.add(new Trap(15*32,5*32, Trap.SPIKE, Trap.UP,true, Trap.UP,3,new Judge(15*32,4*32,32,2*32),4*32));
            trapsDown.add(new Trap(16*32,5*32, Trap.SPIKE, Trap.UP,true, Trap.UP,3,new Judge(16*32,4*32,32,2*32),4*32));
            trapsDown.add(new Trap(17*32,5*32, Trap.SPIKE, Trap.UP,true, Trap.UP,3,new Judge(17*32,4*32,32,2*32),4*32));
            trapsDown.add(new Trap(18*32,6*32, Trap.SPIKE, Trap.UP,true, Trap.UP,2,new Judge(18*32,5*32,32,32),5*32));
            trapsDown.add(new Trap(19*32,6*32, Trap.SPIKE, Trap.UP,true, Trap.UP,2,new Judge(19*32,5*32,32,32),5*32));

            for(int i=13;i<=20;i++){
                trapsDown.add(new Trap(i*32,8*32, Trap.SPIKE, Trap.DOWN));
            }
            trapsDown.add(new Trap(18*32,8*32, Trap.SPIKE, Trap.DOWN,true, Trap.DOWN,5,new Judge(18*32,8*32,32,3*32),12*32));


            for(int i=4;i<=11;i++){
                trapsDown.add(new Trap(i*32,17*32, Trap.SPIKE, Trap.UP));
            }
            for(int i=12;i<=15;i++){
                trapsDown.add(new Trap(i*32,18*32, Trap.SPIKE, Trap.UP,true, Trap.UP,3,judges.get(2),17*32));
            }

            trapsNothing.add(new Trap(19*32,13*32, Trap.TREE, Trap.UP));
            trapsUp.add(new Trap(20*32,14*32+16, Trap.APPLE, Trap.RIGHT,true, Trap.DOWN,10,new Judge(20*32,14*32,32,32*4)));
            trapsNothing.add(new Trap(21*32+16,15*32, Trap.APPLE, Trap.RIGHT));

        }
        //level3
        else if(levelNum==3){

            saves.add(new Save(32,3*32));
            judges.add(new Judge(13*32,7*32, 32,4*32));
            judges.add(new Judge(15*32,7*32,2*32,4*32));
            judges.add(new Judge(16*32,2*32,6*32,32));
            judges.add(new Judge(21*32,2*32,3*32,2*32));

            platforms.add(new Platform(0,0,Platform.LAND,20,1));
            platforms.add(new Platform(32,4*32, Platform.SOLID,2,1));
            platforms.add(new Platform(2*32,5*32, Platform.LAND,1,2));
            platforms.add(new Platform(4*32,4*32, Platform.SOLID,2,2));
            platforms.add(new Platform(10*32,3*32, Platform.SOLID,1,2,true, Platform.RIGHT,20,new Judge(13*32+16,2*32+16,16,16),12*32));
            platforms.add(new Platform(11*32,3*32, Platform.LAND,4,1,true, Platform.DOWN,8,judges.get(0),7*32));
            platforms.add(new Platform(6*32,3*32, Platform.SOLID,3,6));
            platforms.add(new Platform(14*32,3*32, Platform.SOLID,2,9));
            platforms.add(new Platform(10*32,5*32, Platform.LAND,2,9));


            platforms.add(new Platform(21*32,8*32, Platform.SOLID,1,4));
            platforms.add(new Platform(22*32,9*32, Platform.LAND,7,3));

            platforms.add(new Platform(21*32,9*32, Platform.LAND,4,1,true, Platform.RIGHT,5,new Judge(21*32-5,9*32,32,32),22*32));
            platforms.add(new Platform(9*32,12*32, Platform.LAND,1,12,true, Platform.DOWN,5,new Judge(21*32+5,12*32,32,32),13*32));
            platforms.add(new Platform(8*32,9*32, Platform.SOLID,4,1,true, Platform.LEFT,5,new Judge(9*32+5,12*32,32,32),7*32));

            platforms.add(new Platform(20*32,10*32, Platform.SOLID,2,1));
            platforms.add(new Platform(11*32,11*32, Platform.SOLID,1,9));
            platforms.add(new Platform(9*32,10*32, Platform.SOLID,2,2));

            platforms.add(new Platform(8*32,13*32, Platform.LAND,1,14));

            platforms.add(new Platform(5*32,9*32, Platform.SOLID,6,3));
            platforms.add(new Platform(6*32,18*32,Platform.SOLID,2,6));

            platforms.add(new Platform(12*32,17*32,Platform.LAND,3,1));

            platforms.add(new Platform(13*32,19*32,Platform.SOLID,1,12));

            platforms.add(new Platform(13*32,13*32, Platform.LAND,1,2,true, Trap.DOWN,8,new Judge(13*32+16,18*32,2*32, 32 +16),32*17));


            trapsDown.add(new Trap(2*32,4*32, Trap.SPIKE, Trap.UP));

            trapsDown.add(new Trap(8*32,2*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(12*32,4*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(13*32,4*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(12*32,0, Trap.SPIKE, Trap.DOWN));
            trapsDown.add(new Trap(13*32,0, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(13*32,32, Trap.SPIKE, Trap.DOWN));
            trapsDown.add(new Trap(14*32,0, Trap.SPIKE, Trap.DOWN));


            trapsDown.add(new Trap(2*32,4*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(3*32,4*32, Trap.SPIKE, Trap.UP));

            trapsDown.add(new Trap(9*32,9*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(10*32,9*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(8*32,6*32, Trap.SPIKE, Trap.DOWN));
            trapsDown.add(new Trap(9*32,6*32, Trap.SPIKE, Trap.DOWN));

            trapsDown.add(new Trap(2*32,6*32, Trap.SPIKE, Trap.DOWN));
            trapsDown.add(new Trap(3*32,6*32, Trap.SPIKE, Trap.DOWN));
            for(int i=11;i<=13;i++){
                trapsDown.add(new Trap(32,i*32, Trap.SPIKE, Trap.RIGHT));
                trapsDown.add(new Trap(4*32,i*32, Trap.SPIKE, Trap.LEFT));
            }

            trapsDown.add(new Trap(9*32,17*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(10*32,14*32, Trap.SPIKE, Trap.DOWN));
            trapsDown.add(new Trap(12*32,16*32, Trap.SPIKE, Trap.UP));

            trapsDown.add(new Trap(15*32,18*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(16*32,18*32, Trap.SPIKE, Trap.UP));
            for(int i=18;i<=20;i++) {
                trapsDown.add(new Trap(i* 32, 14 * 32, Trap.SPIKE, Trap.DOWN));
            }
            trapsDown.add(new Trap(26*32,18*32, Trap.DEADMAN, Trap.LEFT,true, Trap.LEFT,7,new Judge(16*32,18*32,32*4,32),12*32));
        }
        //level4
        else if(levelNum==4) {

            saves.add(new Save(4*32,18*32));

            platforms.add(new Platform(0,19*32, Platform.SOLID,1,14));
            platforms.add(new Platform(0,8*32, Platform.LAND,8,1));
            platforms.add(new Platform(5*32,17*32, Platform.LAND,3,1));
            platforms.add(new Platform(14*32,16*32, Platform.LAND,4,3));

            platforms.add(new Platform(17*32,19*32, Platform.SOLID,1,3,true,Platform.UP,3,new Judge(17*32,18*32,3*32, 32),11*32));

            platforms.add(new Platform(20*32,9*32, Platform.LAND,11,1));

            platforms.add(new Platform(21*32,19*32, Platform.SOLID,1,3,true,Platform.UP,2,new Judge(21*32,18*32,3*32, 32)));

            platforms.add(new Platform(32,9*32, Platform.SOLID,5,11));
            platforms.add(new Platform(9*32,6*32, Platform.SOLID,4,9));




            platforms.add(new Platform(2*32,0, Platform.LAND,4,8));
            platforms.add(new Platform(10*32,0, Platform.LAND,1,15));
            platforms.add(new Platform(24*32, 32, Platform.LAND,19,1));


            platforms.add(new Platform(14*32,32, Platform.LAND,2,1));
            platforms.add(new Platform(11*32,3*32, Platform.LAND,4,2));
            platforms.add(new Platform(11*32,2*32, Platform.SOLID,1,1,true,Platform.DOWN,1,new Judge(11*32, 32,10,32)));
            platforms.add(new Platform(12*32,2*32, Platform.SOLID,1,1,true,Platform.DOWN,1,new Judge(12*32, 32,10,32)));

            platforms.add(new Platform(5*32,7*32+16, Platform.SOLID,1,2,true,Platform.RIGHTBACK,1,7*32,5*32));


            platforms.add(new Platform(0,7*32, Platform.SOLID,1,2,true,Platform.UP,1,new Judge(0,4*32,3*32,3*32)));

//            trapsDown.add(new Trap(26*32,18*32, Trap.SPIKE, Trap.UP,true, Trap.LEFT,7,new Judge(16*32,18*32,32*4,32),12*32));
            trapsDown.add(new Trap(5*32,16*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(9*32,14*32, Trap.APPLE, Trap.RIGHT,true, Trap.DOWNBACK,3,18*32,14*32));
            trapsDown.add(new Trap(10*32,18*32, Trap.APPLE, Trap.RIGHT,true, Trap.UPBACK,3,14*32,18*32));
            trapsDown.add(new Trap(14*32,15*32, Trap.SPIKE, Trap.UP));

            for(int i=17;i<=19;i++){
                trapsDown.add(new Trap(i*32,18*32, Trap.SPIKE, Trap.UP,true, Trap.UP,5,new Judge(17*32,11*32,3*32,2*32)));
            }
            trapsDown.add(new Trap(20*32,9*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(20*32,8*32, Trap.SPIKE, Trap.UP));

            trapsDown.add(new Trap(21*32,11*32, Trap.APPLE, Trap.UP));
            trapsDown.add(new Trap(21*32+10,11*32+20, Trap.APPLE, Trap.UP));
            trapsDown.add(new Trap(21*32+20,11*32+40, Trap.APPLE, Trap.UP));
            trapsDown.add(new Trap(21*32,13*32, Trap.APPLE, Trap.UP));
            trapsDown.add(new Trap(24*32-20,15*32, Trap.APPLE, Trap.UP));
            trapsDown.add(new Trap(24*32-40,15*32+20, Trap.APPLE, Trap.UP));

            for(int i=19;i<=23;i++){
                trapsDown.add(new Trap(i*32,32, Trap.SPIKE, Trap.DOWN));
            }

            trapsDown.add(new Trap(17*32,5*32, Trap.SPIKE, Trap.UP));
            trapsDown.add(new Trap(14*32,3*32, Trap.SPIKE, Trap.DOWN));

            trapsUp.add(new Trap(11*32+5,3*32-16, Trap.APPLE, Trap.UP));
            trapsUp.add(new Trap(12*32+5,3*32-16, Trap.APPLE, Trap.UP));


            for(int i=6*32;i<=8*32;i=i+16){
                trapsUp.add(new Trap(4*32,i, Trap.APPLE, Trap.UP));

            }
            for(int i=4*32;i<=8*32;i=i+16){
                trapsUp.add(new Trap(i,8*32+16, Trap.APPLE, Trap.UP));

            }

            for(int i=8*32+16;i>=6*32;i=i-16){
                trapsUp.add(new Trap(8*32+16,i, Trap.APPLE, Trap.UP));

            }
            trapsUp.add(new Trap(32,8*32, Trap.SPIKE, Trap.UP,true, Trap.UP,5,new Judge(0,2*32,2*32,32)));
            trapsUp.add(new Trap(2*32,8*32, Trap.SPIKE, Trap.UP));
            trapsUp.add(new Trap(3*32,8*32, Trap.SPIKE, Trap.UP));
        }
        else if(levelNum==5){
            platforms.add(new Platform(0,17*32, Platform.SOLID,1,2,true,Platform.UPBACK,1,7*32,17*32));
            MusicTool.play(MusicTool.victory);
        }

    }
    public void scan() {
        if(kid.getX()<=0&&kid.getX()>=-32){
            kid.setX(0);
        }
        else if(kid.getX()+ kid.getWidth()>=25*32&&kid.getX()+kid.getWidth()<=26*32){
            kid.setX(25*32- kid.getWidth());
        }


        if(kid.getY()+ kid.getHeight()>=20*32){
            kid.die();
        }


        for (Platform platform : platforms) {
            platform.judgeKidCrash(kid);
            if (platform.getTrapType()>0&&JudgeCrashTool.judgeCrash(platform.getJudgeP(),kid)||platform.isTrigger()) {
                platform.move(kid);
                platform.getJudgeP().setTrigger(true);
            }

        }
        for (Trap trap : trapsDown) {
            trap.judgeKidCrash(kid);
            if (trap.getTrapType()>0&& JudgeCrashTool.judgeCrash(trap.getJudgeP(),kid)||trap.isTrigger()) {
                trap.move();
                trap.getJudgeP().setTrigger(true);
            }
        }
        for (Trap trap : trapsUp) {
            trap.judgeKidCrash(kid);
            if (trap.getTrapType()>0&& JudgeCrashTool.judgeCrash(trap.getJudgeP(),kid)||trap.isTrigger()) {
                trap.move();
                trap.getJudgeP().setTrigger(true);
            }
        }


        if(levelNum==0){

            if(kid.getX()+kid.getWidth()>=25*32&&kid.getY()>=11*32&&kid.getY()<=17*32){
                kid.setX(0);
                levelNum++;
                reset();
            }
        }
        else if (levelNum == 1){


            if(kid.getX()<=0&&kid.getY()<=17*32&&kid.getY()>=11*32){
                kid.setX(24*32);
                levelNum--;
                reset();
            }


            if (levelNum==1&&!judges.get(1).isTrigger()&&JudgeCrashTool.judgeCrash(judges.get(1), kid)) {
                for (int i = 2; i <= 4; i++) {
                    trapsDown.get(i).setJudgeP(judges.get(1));
                    trapsDown.get(i).setDirection(Trap.DOWN);
                    trapsDown.get(i).setLimit(7 * 32);
                    trapsDown.get(i).setTrapType(1);
                    trapsDown.get(i).setTrigger(false);
                }
                trapsDown.add(new Trap(5 * 32, 4 * 32, Trap.SPIKE, Trap.UP, true, Trap.UP, 5, new Judge(5*32, 0,32*2,32*5), 32 * -3));
                judges.get(1).setTrigger(true);
            }


            if(kid.getX()+kid.getWidth()>=25*32&&kid.getY()<=7*32&&kid.getY()>=0){
                kid.setX(0);
                levelNum++;
                reset();
            }
        }
        else if(levelNum==2){
            if(kid.getX()<=0&&kid.getY()<=7*32&&kid.getY()>=0){
                kid.setX(24*32);
                levelNum--;
                reset();
            }


            if (levelNum==2&&!judges.get(3).isTrigger()&&JudgeCrashTool.judgeCrash(judges.get(3), kid)) {
                trapsNothing.get(1).setNowImage(ImageTool.apple[1]);
                judges.get(3).setTrigger(true);
            }


            if(kid.getY()+kid.getHeight()>=20*32-20&&kid.getX()<=3*32&&kid.getY()>=2*32){
                kid.setY(0);
                levelNum++;
                reset();
            }


        }
        else if(levelNum==3){

            if(kid.getY()<=-10&&kid.getX()<3*32&&kid.getX()>2*32){
                kid.setY(18*32);
                levelNum--;
                reset();
            }
            if(levelNum==3&&judges.get(0).isTrigger()){
                if (levelNum==3&&!judges.get(1).isTrigger()&&JudgeCrashTool.judgeCrash(judges.get(1), kid)) {
                    platforms.add(new Platform(17*32,3*32, Platform.LAND,4,1,true, Platform.DOWN,20,judges.get(1),7*32));
                    for(int i=12;i<=16;i++) {
                        trapsDown.add(new Trap(i * 32, 7 * 32, Trap.SPIKE, Trap.DOWN,true, Trap.DOWN,1,judges.get(1),9*32));
                    }
                }
            }

            if (levelNum==3&&!judges.get(2).isTrigger()&&JudgeCrashTool.judgeCrash(judges.get(2), kid)) {
                trapsDown.get(3).setJudgeP(judges.get(2));
                trapsDown.get(3).setDirection(Trap.RIGHT);
                trapsDown.get(3).setSpeed(10);
                trapsDown.get(3).setLimit(22 * 32);
                trapsDown.get(3).setTrapType(1);
                trapsDown.get(3).setTrigger(false);
                judges.get(2).setTrigger(true);


            }

            if (levelNum==3&&!judges.get(3).isTrigger()&&JudgeCrashTool.judgeCrash(judges.get(3), kid)) {
                trapsDown.get(3).setJudgeP(new Judge(23*32,4*32-16,32*2,3*32));
                trapsDown.get(3).setDirection(Trap.RIGHT);
                trapsDown.get(3).setLimit(26 * 32);
                trapsDown.get(3).setTrapType(1);
                trapsDown.get(3).setTrigger(false);
                judges.get(3).setTrigger(true);
            }

            if(kid.getX()+kid.getWidth()>=25*32&&kid.getY()<=20*32&&kid.getY()>=16*32){
                kid.setX(0);
                levelNum++;
                reset();
            }
        }
        else if(levelNum==4){
            if(kid.getX()<=0&&kid.getY()<=20*32&&kid.getY()>=16*32){
                kid.setX(24*32);
                levelNum--;
                reset();
            }
            if(kid.getY()+kid.getHeight()<=0&&kid.getX()>=0&&kid.getX()<2*32){
                kid.setY(16*32);
                levelNum++;
                reset();
            }


        }
        else if(levelNum==5)
        {
            if(kid.getY()+kid.getHeight()>19*32+16&&kid.getX()>=0&&kid.getX()<2*32){
                kid.setY(0);
                levelNum--;
                reset();
            }
        }

    }
    public void draw(Graphics g,int width,int height){

        if(levelNum==0||levelNum==1||levelNum==2||levelNum==5){
            g.setColor(new Color(195, 215, 247));

        }
        else if(levelNum==3||levelNum==4){
            g.setColor(new Color(77, 36, 14));
        }
        g.fillRect(0,0,width,height);
        if(levelNum==0){
            g.drawImage(ImageTool.maker.getImage(),4*32,height/2-ImageTool.maker.getIconHeight()/2,null);
        }
        else if(levelNum==5){
            g.drawImage(ImageTool.gameOver.getImage(), 32*25/2-ImageTool.gameOver.getIconWidth()/2,32*20/2-ImageTool.gameOver.getIconHeight()/2,null);
        }
        for(Trap trap: trapsDown){
            trap.draw(g);
        }
        for(Trap trap: trapsNothing){
            trap.draw(g);
        }
        for(Platform platform:platforms){
            platform.draw(g);
        }
        for(Trap trap: trapsUp){
            trap.draw(g);
        }
        for(Save save:saves){
            save.draw(g);
        }
    }
}
