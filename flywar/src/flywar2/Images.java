package flywar2;


import flywar1.FlyingObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * *@Auther: jingqing
 * *@Description:描述
 * *@Date: 2020/7/2 10:26
 */
public class Images {
    public static BufferedImage sky;
    public static BufferedImage bullet;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    public static BufferedImage[] hero;
    public static BufferedImage[] airs;
    public static BufferedImage[] bairs;
    public static BufferedImage[] bees;

    static {
        sky = readImage("background.png");
        bullet = readImage("bullet.png");
        start = readImage("start.png");
        pause = readImage("pause.png");
        gameover = readImage("gameover.png");

        hero = new BufferedImage[2];
        hero[0] = readImage("hero0.png");
        hero[1] = readImage("hero1.png");
        airs = new BufferedImage[5];
        bairs = new BufferedImage[5];
        bees = new BufferedImage[5];
        airs[0] = readImage("airplane.png");
        bairs[0] = readImage("bigairplane.png");
        bees[0] = readImage("bee.png");
        for (int i=1;i<airs.length;i++){
            airs[i] = readImage("bom"+i+".png");
            bairs[i] = readImage("bom"+i+".png");
            bees[i] = readImage("bom"+i+".png");
        }
    }




    public static BufferedImage readImage(String fileName){
        try{
            BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));
            return img;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
































