package flywar1;

import java.awt.image.BufferedImage;

public class Sky extends FlyingObject{
    private int y1;
    private int steep;

    public Sky() {
        super(World.WIDTH,World.HEIGHT,0,0);
        this.y1 = - World.HEIGHT;
        this.steep = 1;
    }
    @Override
    public void Step() {
        this.y += steep;
        this.y1 += steep;
        if(y>=World.HEIGHT){
            this.y = -World.HEIGHT;
        }
        if(y1>=World.HEIGHT){
            this.y1 = -World.HEIGHT;
        }

    }

    @Override
    public BufferedImage getImage() {
        return Images.sky;
    }
    public int getY1(){
        return y1;
    }
}




























