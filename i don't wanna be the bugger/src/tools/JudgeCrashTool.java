package tools;

import things.Kid;
import things.Thing;

import javax.swing.*;

public class JudgeCrashTool {
    public static boolean judgeCrash(Thing a,Thing b){
        int aWidth=a.getWidth(),aHeight= a.getHeight(),aX=a.getX(),aY=a.getY();
        int aCx=aX+aWidth/2,aCy=aY+aHeight/2;

        int bWidth=b.getWidth(),bHeight= b.getHeight(),bX=b.getX(),bY=b.getY();
        int bCx=bX+bWidth/2,bCy=bY+bHeight/2;

        if(Math.abs(aCx-bCx)<(aWidth+bWidth)/2){
            if(Math.abs(aCy-bCy)<(aHeight+bHeight)/2){
                return true;
            }
        }
        return false;
    }
}
