package flywar2;

import java.awt.image.BufferedImage;

/**
 * *@Auther: jingqing
 * *@Description:英雄机类
 * *@Date: 2020/7/2 9:49
 */
public class Hero extends FlyingOject{
    private int life;
    private int fire;
    public Hero() {
        super(97,139,140,400);
        this.fire = 0;
        this.life = 3;
    }
    @Override
    public void Step() {

    }

    private int index = 0;
    public BufferedImage getImage() {
        return Images.hero[index++%Images.hero.length];
    }

    public Bullet[] Shoot(){
        int xStep = this.width/4;
        int yStep = 1;
        if(fire>0){
            Bullet[] bs = new Bullet[2];
            bs[0] = new Bullet(this.x + 1*xStep-5,this.y - yStep);
            bs[1] = new Bullet(this.x + 3*xStep-5,this.y - yStep);
            fire -= 2;
            return bs;
        }else{
            Bullet[] bs = new Bullet[1];
            bs[0] = new Bullet(this.x + 2*xStep-5,this.y - yStep);
            return bs;
        }
    }

    public void moveTo(int x,int y){
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }

    public int getLife() {
        return life;
    }

    public int getFire() {
        return fire;
    }

    public void addFire(){
        this.fire += 40;
    }
    public void addLife(){
        this.life++;
    }
    public void clearFire(){
        this.fire = 0;
    }
    public void reLife(){
        this.life--;
    }
}























