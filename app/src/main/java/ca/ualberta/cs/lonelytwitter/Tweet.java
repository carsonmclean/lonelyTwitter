package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by camclean on 9/16/15.
 */
public abstract class Tweet extends Object implements Tweetable {
    private String text;
    private Date date;
    private ArrayList<Mood> moodList;

    public ArrayList<Mood> getMoodList() {
        return moodList;
    }

    public void setMoodList(ArrayList<Mood> moodList) {
        this.moodList = moodList;
    }

    public Tweet(Date date, String tweet) {
        this.setText(tweet);
        this.text = tweet;
    }

    public Tweet(String tweet) {
        this.setText(tweet);
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws IOException {
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new IOException("Tweet was too long!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();
}
