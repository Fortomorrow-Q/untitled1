package flywar1;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
    private int steep;

    public Bullet(int x, int y) {
        super(8,20, x, y);
        this.steep = 5;
    }

    @Override
    public void Step() {
        y -= steep;
    }

    @Override
    public BufferedImage getImage() {
        return Images.bullet;
    }

    @Override
    public boolean isOut() {
        return y <= -this.height;
    }
}





























