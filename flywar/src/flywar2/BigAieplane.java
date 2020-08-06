package flywar2;

import java.awt.image.BufferedImage;

/**
 * *@Auther: jingqing
 * *@Description:大敌机
 * *@Date: 2020/7/2 10:03
 */
public class BigAieplane extends FlyingOject implements Enemy{
    private int steep;
    public BigAieplane() {
        super(66,89);
        this.steep = 2;
    }

    @Override
    public void Step() {
        this.y += steep;
    }

    @Override
    public int getScore() {
        return 5;
    }

    private int index = 1;
    public BufferedImage getImage() {
        if (isLife()){
            return Images.bairs[0];
        }else if(isDead()){
            BufferedImage img = Images.bairs[index++];
            if(index==Images.bairs.length){
                state = REMOVE;
            }
            return img;
        }
        return null;
    }
}





















































































