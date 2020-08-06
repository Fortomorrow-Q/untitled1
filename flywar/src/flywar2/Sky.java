package flywar2;

import java.awt.image.BufferedImage;

/**
 * *@Auther: jingqing
 * *@Description:天空类
 * *@Date: 2020/7/2 9:43
 */
public class Sky extends FlyingOject{
    private int y1;
    private int steep;
    public Sky() {
        super(World.WIDTH,World.HEIGHT,0,0);
        this.y1 = -World.HEIGHT;
        this.steep = 1;
    }

    @Override
    public void Step() {
        y += steep;
        y1 += steep;
        if (y > World.HEIGHT){
            y = -World.HEIGHT;
        }else if (y1 > World.HEIGHT){
            y1 = -World.HEIGHT;
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































