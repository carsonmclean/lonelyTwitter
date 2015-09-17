package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by camclean on 9/16/15.
 */
public class NormalTweet extends Tweet {
    public NormalTweet(Date date, String tweet) {
        super(date, tweet);
    }

    public NormalTweet(String tweet) {
        super(tweet);
    }

    public Boolean isImportant () {
        return Boolean.FALSE;
    }
}
