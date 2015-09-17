package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by camclean on 9/16/15.
 */
public class HappyMood extends Mood {
    private String mood = "Happy :D";

    public HappyMood(Date date) {
        super(date);
    }
    public HappyMood() {
        super(new Date());
    }

    public String getMood() {
        return this.mood;
    }
}
