package things;

import javax.swing.*;

//所有的人物以及关卡元素继承的类
public abstract class Thing {
    private int x, y;
    private int width,height;
    public Thing(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Thing(int x, int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public abstract ImageIcon getNowImage();

}

