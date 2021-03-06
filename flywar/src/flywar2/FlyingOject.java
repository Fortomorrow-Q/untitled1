package flywar2;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * *@Auther: jingqing
 * *@Description:飞行物超类
 * *@Date: 2020/7/2 9:17
 */
public abstract class FlyingOject {
    public static final int LIFE = 0;
    public static final int DEAD = 1;
    public static final int REMOVE = 2;
    protected int state = LIFE;
    protected int width;
    protected int height;
    protected int x;
    protected int y;

    public FlyingOject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public FlyingOject(int width, int height) {
        this.width = width;
        this.height = height;
        Random rand = new Random();
        this.x = rand.nextInt(World.WIDTH-this.width);
        this.y = -this.height;
    }
    public abstract void Step();

    public abstract BufferedImage getImage();

    public boolean isLife(){
        return state==LIFE;
    }
    public boolean isDead(){
        return state==DEAD;
    }
    public boolean isRemove(){
        return state==REMOVE;
    }

    public boolean isOut(){
        return y > World.HEIGHT;
    }

    public boolean isHit(FlyingOject other){
        int x1 = this.x - other.width;
        int x2 = this.x + this.width;
        int y1 = this.y - other.height;
        int y2 = this.y + this.height;
        int x = other.x;
        int y = other.y;
        return x>x1 && x<x2 && y>y1 && y<y2;
    }

    public void goDead(){
        state = DEAD;
    }
}

























































