package flywar2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * *@Auther: jingqing
 * *@Description:测试类
 * *@Date: 2020/7/2 9:02
 */
public class World extends JPanel{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 700;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    private int state = START;

    private Sky sky = new Sky();
    private Hero hero = new Hero();
    private FlyingOject[] enemes = { };
    private Bullet[] bullets = { };



    public FlyingOject nextOne(){
        Random rand = new Random();
        int Type = rand.nextInt(20);
        if(Type < 5){
            return new Bee();
        }else if(Type <13){
            return new Airplane();
        }else{
            return new BigAieplane();
        }
    }

    private int enterType = 0;
    public void enterAction(){
        enterType++;
        if(enterType%15==0){
            FlyingOject foj = nextOne();
            enemes = Arrays.copyOf(enemes,enemes.length+1);
            enemes[enemes.length-1] = foj;
        }
    }

    private int shootType = 0;
    public void shootActiong(){
        shootType++;
        if(shootType%10==0){
            Bullet[] b = hero.Shoot();
            bullets = Arrays.copyOf(bullets,bullets.length+b.length);
            System.arraycopy(b,0,bullets,bullets.length-b.length,b.length);
        }
    }

    public void moveAction(){
        sky.Step();
        for (FlyingOject f : enemes){
            f.Step();
        }
        for (Bullet b : bullets){
            b.Step();
        }
    }

    public void outOfBandsAction(){
        /* 删除越界敌人 */
        int index = 0;
        FlyingOject[] enemeslives = new FlyingOject[enemes.length];
        for(FlyingOject f : enemes){
            if (!f.isOut()&&!f.isRemove()) {
                enemeslives[index] = f;
                index++;
            }
        }
        enemes = Arrays.copyOf(enemeslives,index);
        /* 删除越界子弹 */
        index = 0;
        Bullet[] bulletslives = new Bullet[bullets.length];
        for (Bullet b : bullets){
            if(!b.isOut()&&!b.isRemove()){
                bulletslives[index] = b;
                index++;
            }
        }
        bullets = Arrays.copyOf(bulletslives,index);
    }

    private int score = 0;
    public void HitAction(){
        /* 检查敌人与子弹碰撞 */
        for (Bullet b : bullets){
            for (FlyingOject f : enemes){
                if(b.isLife()&&f.isLife()&&f.isHit(b)){
                    b.goDead();
                    f.goDead();
                    if(f instanceof Enemy){
                        Enemy e = (Enemy)f;
                        score += e.getScore();
                    }
                    if(f instanceof Award){
                        Award a = (Award)f;
                        int type = a.getAwardType();
                        switch (type){
                            case Award.FILE:
                                hero.addFire();
                                break;
                            case Award.LIFE:
                                hero.addLife();
                                break;
                        }
                    }
                }
            }
        }
        /* 检查敌人与英雄级机碰撞 */
        for (FlyingOject f : enemes){
            if(f.isLife()&&hero.isLife()&&f.isHit(hero)){
                f.goDead();
                hero.clearFire();
                hero.reLife();
            }
        }
    }

    public void isOverAction(){
        if (hero.getLife()<=0){
            state = GAME_OVER;
        }
    }



    public void action(){
        MouseAdapter m = new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if(state==RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero.moveTo(x, y);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state){
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        score = 0;
                        sky = new Sky();
                        hero = new Hero();
                        enemes = new FlyingOject[0];
                        bullets = new Bullet[0];
                        state = START;
                        break;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == PAUSE){
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(state == RUNNING){
                    state = PAUSE;
                }
            }
        };
        this.addMouseListener(m);
        this.addMouseMotionListener(m);



        Timer timer = new Timer();
        int interverl = 10;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state==RUNNING) {
                    enterAction();
                    shootActiong();
                    moveAction();
                    outOfBandsAction();
                    HitAction();
                    isOverAction();
                }
                repaint();
            }
        },interverl,interverl);
    }

    public void paint(Graphics g){
        g.drawImage(sky.getImage(),sky.x,sky.y,null);
        g.drawImage(sky.getImage(),sky.x,sky.getY1(),null);
        g.drawImage(hero.getImage(),hero.x,hero.y,null);
        for (FlyingOject f : enemes){
            g.drawImage(f.getImage(),f.x,f.y,null);
        }
        for (Bullet b : bullets){
            g.drawImage(b.getImage(),b.x,b.y,null);
        }

        g.setColor(Color.red);
        g.drawString("当前火力:"+hero.getFire(),15,20);
        g.drawString("当前生命:"+hero.getLife(),15,35);
        g.drawString("当前得分:"+score,15,50);

        switch (state){
            case START:
                g.drawImage(Images.start,0,0,null);
                break;
            case PAUSE:
                g.drawImage(Images.pause,0,0,null);
                break;
            case GAME_OVER:
                g.drawImage(Images.gameover,0,0,null);
        }

    }





    public static void main(String[] args) {
        World world = new World();
        JFrame frame = new JFrame();
        frame.add(world);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        world.action();
    }
}


















































































