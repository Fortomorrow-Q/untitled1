package flywar1;

import java.awt.image.BufferedImage;
import java.util.Random;

public class BigAirplane extends FlyingObject implements Enemy{
    private int steep;

    public BigAirplane() {
        super(66,89);
        Random rand = new Random();
        this.x = rand.nextInt(World.WIDTH-this.width);
        this.y = -this.height;
        this.steep = 2;
    }
    @Override
    public void Step() {
        this.y += steep;
    }

    @Override
    public int getscore() {
        return 5;
    }

    private int index = 1;
    @Override
    public BufferedImage getImage() {
        if (isLife()){
            return Images.bairs[0];
        }else if (isDead()){
            BufferedImage img = Images.bairs[index++];
            if (index == Images.bairs.length){
                state = REMOVE;
            }
            return img;
        }
        return null;
    }

}















































