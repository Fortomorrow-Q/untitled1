package flywar2;

/**
 * *@Auther: jingqing
 * *@Description:奖励接口
 * *@Date: 2020/7/2 9:19
 */
public interface Award {
    public int FILE = 0;
    public int LIFE = 1;

    public int getAwardType();
}
