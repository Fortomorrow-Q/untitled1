package flywar2;

import java.awt.image.BufferedImage;

/**
 * *@Auther: jingqing
 * *@Description:小敌机
 * *@Date: 2020/7/2 9:55
 */
public class Airplane extends FlyingOject implements Enemy{
    private int steep;

    public Airplane() {
        super(48, 50);
        this.steep = 2;
    }

    @Override
    public void Step() {
        this.y += steep;
    }

    private int index = 1;
    public BufferedImage getImage() {
        if (isLife()){
            return Images.airs[0];
        }else if(isDead()){
            BufferedImage img = Images.airs[index++];
            if(index==Images.airs.length){
                state = REMOVE;
            }
            return img;
        }
        return null;
    }

    @Override
    public int getScore() {
        return 3;
    }
}






















































