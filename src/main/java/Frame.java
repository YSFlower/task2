/**
 * Created by 小霸王 on 2017/5/11.
 */
public class Frame {
    int ball_1_score;
    int ball_2_score;
    boolean strike = false;
    boolean spare = false;
    boolean plus_ball = false;

    public Frame(int ball1,int ball2)
    {
        ball_1_score = ball1;
        ball_2_score = ball2;
        if(ball1==10&&!plus_ball)
            strike = true;
        else if(ball1!=10&&ball1+ball2==10&&!plus_ball)
            spare = true;
    }
    public void set_plus_ball()
    {
        plus_ball = false;
        strike = false;
        spare = false;
    }
}
