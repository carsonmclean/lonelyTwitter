package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by camclean on 9/16/15.
 */
public class SadMood extends Mood {
    private String mood = "Sad :(";

    public SadMood(Date date) {
        super(date);
    }

    public SadMood() {
        super(new Date());
    }

    public String getMood() {
        return this.mood;
    }
}
