package flywar1;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyingObject implements Award{
    private int xsteep;
    private int ysteep;
    private int awardType;

    public Bee() {
        super(60,51);
        Random rand = new Random();
        this.x = rand.nextInt(World.WIDTH-this.width);
        this.y = -this.height;
        this.xsteep = rand.nextInt(2)>=1?-3:3;
        this.ysteep = 3;
        this.awardType = rand.nextInt(2);
    }
    @Override
    public void Step() {
           this.x += xsteep;
           this.y += ysteep;
           if(x>=World.WIDTH-this.width){
               xsteep = -xsteep;
               x = World.WIDTH-this.width;
           }else if (x<=0){
               xsteep = -xsteep;
               x = 0;
        }
    }
    @Override
    public int getAwardType() {
        return awardType;
    }

    private int index = 1;
    @Override
    public BufferedImage getImage() {
        if (isLife()){
            return Images.bees[0];
        }else if (isDead()){
            BufferedImage img = Images.bees[index++];
            if (index == Images.bees.length){
                state = REMOVE;
            }
            return img;
        }
        return null;
    }

}




























