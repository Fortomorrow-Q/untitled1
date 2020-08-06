package flywar2;

import java.awt.image.BufferedImage;

/**
 * *@Auther: jingqing
 * *@Description:子弹
 * *@Date: 2020/7/2 10:21
 */
public class Bullet extends FlyingOject{
    private int steep;
    public Bullet(int x, int y) {
        super(8, 20, x, y);
        this.steep = 3;
    }

    @Override
    public void Step() {
        this.y -= steep;
    }

    @Override
    public BufferedImage getImage() {
        return Images.bullet;
    }

    @Override
    public boolean isOut() {
        return y < -this.height;
    }
}
