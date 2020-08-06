package flywar1;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Airplane extends FlyingObject implements Enemy{
    private int steep;

    public Airplane() {
        super(48,50);
        Random rand = new Random();
        this.x = rand.nextInt(World.WIDTH-this.width);
        this.y = -this.height;
        this.steep = 3;
    }

    @Override
    public void Step() {
        this.y += steep;
    }

    @Override
    public int getscore() {
        return 3;
    }

    private int index = 1;
    public BufferedImage getImage() {
        if(isLife()){
            return Images.airs[0];
        }else if (isDead()){
            BufferedImage img = Images.airs[index++];
            if (index == Images.airs.length){
                state = REMOVE;
            }
            return img;
        }
        return null;
    }
}


















































