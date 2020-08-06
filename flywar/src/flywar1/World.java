package flywar1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class World extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 700;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    public int state = START;

    Sky sky = new Sky();
    Hero hero = new Hero();
    FlyingObject[] enemies = { };
    Bullet[] bullets = { };

    public FlyingObject nextOne(){
        Random rand = new Random();
        int type = rand.nextInt(20);
        if(type<6){
            return new Bee();
        }else if (type<14){
            return new Airplane();
        }else {
            return new BigAirplane();
        }
    }

    private int entertype = 0;
    public void enterAction(){
        entertype++;
        if(entertype%20 == 0){
            FlyingObject f = nextOne();
            enemies = Arrays.copyOf(enemies,enemies.length+1);
            enemies[enemies.length-1] = f;
        }
    }

    int shoottype = 0;
    public void shootAction(){
        shoottype++;
        if(shoottype%15 == 0){
            Bullet[] bul = hero.shoot();
            bullets = Arrays.copyOf(bullets,bullets.length+bul.length);
            System.arraycopy(bul,0,bullets,bullets.length-bul.length,bul.length);
        }
    }

    public void removeAction(){
        int index = 0;
        FlyingObject[] enemieslife = new FlyingObject[enemies.length];
        for (int i=0;i<enemies.length;i++){
            FlyingObject f = enemies[i];
            if(!f.isRemove() && !f.isDead() && !f.isOut()) {
                enemieslife[index] = f;
                index++;
            }
        }
        enemies = Arrays.copyOf(enemieslife,index);

        index = 0;
        Bullet[] bulletslife = new Bullet[bullets.length];
        for (int i=0;i<bullets.length;i++){
            Bullet b = bullets[i];
            if(!b.isRemove() && !b.isDead() && !b.isOut()){
                bulletslife[index] = b;
                index++;
            }
        }
        bullets = Arrays.copyOf(bulletslife,index);
    }

    public void moveAction(){
        sky.Step();
        for (int i=0;i<enemies.length;i++){
            FlyingObject f = enemies[i];
            f.Step();
        }
        for (int j=0;j<bullets.length;j++){
            Bullet b = bullets[j];
            b.Step();
        }
    }

    private int Score = 0;
    public void bulletscollision(){
        for (int i=0;i<enemies.length;i++){
            FlyingObject f = enemies[i];
            for (int j=0;j<bullets.length;j++){
                Bullet b = bullets[j];
                if (f.isLife() && b.isLife() && f.hit(b)){
                    f.goDead();
                    b.goDead();
                    if(f instanceof Enemy){
                        Enemy e = (Enemy)f;
                        Score += e.getscore();
                    }else if (f instanceof Award){
                        Award a = (Award)f;
                        int type = a.getAwardType();
                        switch (type){
                            case Award.LIFE:
                                hero.addlife();
                                break;
                            case Award.FIRE:
                                hero.addfire();
                                break;
                        }
                    }
                }
            }
        }
    }

    public void herocollision(){
        for (int i = 0; i <enemies.length; i++) {
            FlyingObject f= enemies[i];
            if(f.isLife() && hero.isLife() && f.hit(hero)){
                f.goDead();
                hero.Lesslife();
                hero.clearfire();
            }
        }
    }

    public void overAction(){
        if(hero.isDead()){
            state = GAME_OVER;
        }
    }


    public void action(){
        MouseAdapter m = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero.move(x, y);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state){
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        Score = 0;
                        sky = new Sky();
                        hero = new Hero();
                        enemies = new FlyingObject[0];
                        bullets = new Bullet[0];
                        state = START;
                        break;
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(state == RUNNING){
                    state = PAUSE;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == PAUSE){
                    state = RUNNING;
                }
            }
        };

        this.addMouseListener(m);
        this.addMouseMotionListener(m);

        Timer timer = new Timer();
        int intevel = 5;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state == RUNNING) {
                    enterAction();              //敌机入场
                    shootAction();              //子弹入场
                    moveAction();               //飞行物移动
                    removeAction();             //删除越界飞行物
                    bulletscollision();         //敌机与子弹碰撞
                    herocollision();            //敌机与英雄机碰撞
                    overAction();               //检查游戏结束
                }
                repaint();
            }
        },intevel,intevel);

    }

    @Override
    public void paint(Graphics g){
        g.drawImage(sky.getImage(),sky.x,sky.y,null);
        g.drawImage(sky.getImage(),sky.x,sky.getY1(),null);
        g.drawImage(hero.getImage(),hero.x,hero.y,null);
        for(int i=0;i<enemies.length;i++){
            FlyingObject f = enemies[i];
            g.drawImage(f.getImage(),f.x,f.y,null);
        }
        for(int i=0;i<bullets.length;i++){
            Bullet b = bullets[i];
            g.drawImage(b.getImage(),b.x,b.y,null);
        }

        g.setColor(Color.green);
        g.drawString("获得分数:"+Score,10,15);
        g.drawString("英雄命数:"+hero.getLife(),10,30);
        g.drawString("当前火力:"+hero.getFire(),10,45);

        switch(state){
            case START:
                g.drawImage(Images.start,0,0,null);
                break;
            case PAUSE:
                g.drawImage(Images.pause,0,0,null);
                break;
            case GAME_OVER:
                g.drawImage(Images.gameover,0,0,null);
                break;
        }


    }


    public static void main(String[] args){
        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        world.action();
    };
}
