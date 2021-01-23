package tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.font.ImageGraphicAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageTool {



    public static ImageIcon[] rightStatus=new ImageIcon[4];
    public static ImageIcon[] leftStatus=new ImageIcon[4];
    public static ImageIcon[] land=new ImageIcon[3];
    public static ImageIcon[] trap1=new ImageIcon[5];
    public static ImageIcon[] trap2=new ImageIcon[5];
    public static ImageIcon[] airplane=new ImageIcon[5];
    public static ImageIcon[] tree=new ImageIcon[5];
    public static ImageIcon[] apple=new ImageIcon[3];
    public static ImageIcon gameOver =new ImageIcon("image/gameOver/gameOver.png");
    public static ImageIcon save=new ImageIcon("image/save/1.png");
    public static ImageIcon deadMan=new ImageIcon("image/trap/trap4/deadMan.png");
    public static ImageIcon blood=new ImageIcon("image/blood/blood.png");
    public static ImageIcon maker=new ImageIcon("image/maker/maker.png");
    public static BufferedImage flipHorizontalJ2D(BufferedImage bufferedImage)  {
        int width=bufferedImage.getWidth();
        int height=bufferedImage.getHeight();

        BufferedImage dstImage=new BufferedImage(width,height,bufferedImage.getType());

        AffineTransform affineTransform=new AffineTransform(-1,0,0,1,width,0);
        AffineTransformOp affineTransformOp=new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return affineTransformOp.filter(bufferedImage,dstImage);
    }
    public static void init(){

        for(int i=0;i<4;i++){
            rightStatus[i]=new ImageIcon("image/rightStatus/"+(i+1)+".gif");
        }
        for(int i=0;i<4;i++){
            leftStatus[i]=new ImageIcon("image/leftStatus/"+(i+1)+".gif");
        }
        land[0]=new ImageIcon("image/land/greenLand.png");
        land[1]=new ImageIcon("image/land/land.png");
        land[2]=new ImageIcon("image/land/solidLand.png");
        for(int i=1;i<=4;i++){
            trap1[i]=new ImageIcon("image/trap/trap1/"+i+".png");
        }

        for(int i=1;i<=4;i++){
            trap2[i]=new ImageIcon("image/trap/trap2/"+i+".png");
        }

        for(int i=1;i<=4;i++){
            airplane[i]=new ImageIcon("image/trap/trap7/airplane"+i+".png");
        }

        for(int i=1;i<=1;i++){
            tree[i]=new ImageIcon("image/trap/trap8/tree"+i+".png");
        }
        apple[1]=new ImageIcon("image/trap/trap3/apple1.gif");
        apple[2]=new ImageIcon("image/trap/trap3/apple2.png");
    }
}
