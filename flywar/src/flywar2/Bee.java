package flywar2;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * *@Auther: jingqing
 * *@Description:小蜜蜂
 * *@Date: 2020/7/2 10:06
 */
public class Bee extends FlyingOject implements Award{
    private int xsteep;
    private int ysteep;
    private int awardType;

    public Bee() {
        super(60,51);
        Random rand = new Random();
        this.awardType = rand.nextInt(2);
        this.xsteep = rand.nextInt(10)>5?-4:4;
        this.ysteep = 3;
    }

    @Override
    public int getAwardType() {
        return awardType;
    }

    @Override
    public void Step() {
        this.y += ysteep;
        this.x += xsteep;
        if(x>World.WIDTH-this.height){
            xsteep = -xsteep;
            x = World.WIDTH-this.height;
        }else if(x<0){
            xsteep = -xsteep;
            x = 0;
        }
    }

    private int index = 1;
    public BufferedImage getImage() {
        if (isLife()){
            return Images.bees[0];
        }else if(isDead()){
            BufferedImage img = Images.bees[index++];
            if(index==Images.bees.length){
                state = REMOVE;
            }
            return img;
        }
        return null;
    }
}

































