package things;

import javax.swing.*;

//关卡的触发判断区域
public class Judge extends Thing{
    private boolean trigger=false;
    public Judge(int x,int y,int width,int height) {
        super(x,y,width,height);
    }

    @Override
    public ImageIcon getNowImage() {
        return null;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }
}
