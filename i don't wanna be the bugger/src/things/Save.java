package things;

import tools.ImageTool;
import tools.JudgeCrashTool;
import tools.MusicTool;

import javax.swing.*;
import java.awt.*;


public class Save extends Thing {
    public final static int UP=1,RIGHT=2,DOWN=3,LEFT=4;
    private ImageIcon nowImage;
    private int direction;
    //    private int towards;
    private boolean movable;
    private int speed;
    private int trigger=9;
    private int limit=-32;
    public Save(int x,int y){
        super(x,y);
        nowImage= ImageTool.save;
        setWidth(nowImage.getIconWidth());
        setHeight(nowImage.getIconHeight());
    }

    @Override
    public ImageIcon getNowImage() {
        return nowImage;
    }


    public void save(Kid kid, Level level){
        if(JudgeCrashTool.judgeCrash(kid,this)){
            level.setKidSaveX(kid.getX());
            level.setKidSaveY(kid.getY());
            level.setRecordLevelNum(level.getLevelNum());
            MusicTool.play(MusicTool.save);
        }
    }
    public void draw(Graphics g){
        g.drawImage(nowImage.getImage(),getX(),getY(),null);
    }

}
