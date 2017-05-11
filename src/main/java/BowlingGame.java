public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
        String[] round_with_plus = bowlingCode.split("\\|\\|");    //分离前十个球和附加球
        if(round_with_plus.length!=1&&round_with_plus.length!=2)
            System.out.println("Error Input!\n");
        String[] round_10 = round_with_plus[0].split("\\|");
        assert(round_10.length==10);
        
        String round_plus = null;
        if(round_with_plus.length>1)
            round_plus = round_with_plus[1];

        Frame[] frames = new Frame[11];
        set_frame(round_10, frames);                                     //记录每一个frame的情况

        if(round_plus!=null)
            add_plus_ball(round_plus, frames);                           //记录附加球的情况

        int[] scores = new int[10];
        int sum=0;
        sum = compute_total_score(frames, scores, sum);                  //sum

        return sum;
    }

    public int compute_total_score(Frame[] frames, int[] scores, int sum) {
        for(int i=0;i<10;++i)
        {
            if(frames[i].strike)
            {
                if(frames[i+1].strike)
                    scores[i] = 10+10+frames[i+2].ball_1_score;
                else
                    scores[i] = 10+frames[i+1].ball_1_score+frames[i+1].ball_2_score;
            }
            else if(frames[i].spare)
            {
                scores[i] = 10+frames[i+1].ball_1_score;
            }
            else
                scores[i] = frames[i].ball_1_score+frames[i].ball_2_score;
            sum+=scores[i];

        }
        return sum;
    }

    public void add_plus_ball(String round_plus, Frame[] frames) {
        int[] ball = new int[2];
        for (int i = 0; i < round_plus.length(); ++i) {

            if (round_plus.charAt(i) == '-')
                ball[i] = 0;
            else if (round_plus.charAt(i) == 'X')
                ball[i] = 10;
            else {
                assert (round_plus.charAt(i) >= '1' && round_plus.charAt(i) <= '9');
                ball[i] = round_plus.charAt(i) - 48;
            }
        }
        frames[10] = new Frame(ball[0], ball[1]);
        frames[10].set_plus_ball();
    }

    public void set_frame(String[] round_10, Frame[] frames) {
        for(int i=0;i<10;++i)
        {
            String s =round_10[i];
            if(round_10[i].equals("X"))
                frames[i] = new Frame(10,0);
            else
            {
                assert(round_10[i].length()==2);
                int[] ball = new int[2];
                for(int j=0;j<2;++j)
                {
                    if(round_10[i].charAt(j)=='-')
                        ball[j] = 0;
                    else if(round_10[i].charAt(j)=='/')
                        ball[1] = 10-ball[0];
                    else
                    {
                        assert(round_10[i].charAt(j)>='1'&&round_10[i].charAt(j)<='9');
                        ball[j]+= round_10[i].charAt(j)-48;
                    }
                }
                frames[i] = new Frame(ball[0],ball[1]);
            }
        }
    }


}