package flywar1;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
    private int life;
    private int fire;

    public Hero() {
        super(97, 139,140,400);
        this.life = 3;
        this.fire = 0;
    }
    @Override
    public void Step() {

    }

    private int index = 0;
    public BufferedImage getImage() {
        return Images.hero[index++%Images.hero.length];
    }

    public Bullet[] shoot(){
        int xsteep = this.width/4;
        int ysteep = 5;
        if(fire>0){
            Bullet[] bs = new Bullet[2];
            bs[0] = new Bullet(this.x + 1*xsteep-5,this.y - ysteep);
            bs[1] = new Bullet(this.x + 3*xsteep-5,this.y - ysteep);
            fire -=2;
            return bs;

        }else{
            Bullet[] bs = new Bullet[1];
            bs[0] = new Bullet(this.x + 2*xsteep-5,this.y - ysteep);
            return bs;
        }
    }

    public void addlife(){
        life++;
    }
    public void Lesslife(){
        life--;
    }
    public void addfire(){
        fire += 40;
    }
    public void clearfire(){
        fire = 0;
    }
    public boolean isDead(){
        return life<=0;
    }
    public int getLife(){
        return life;
    }
    public int getFire(){
        return fire;
    }
    public void move(int x,int y){
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }
}

























