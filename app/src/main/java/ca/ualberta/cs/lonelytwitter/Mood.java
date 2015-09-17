package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by camclean on 9/16/15.
 */
public abstract class Mood {
    private Date date;

    public Mood(Date date) {
        this.date = date;
    }

    public abstract String getMood();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
