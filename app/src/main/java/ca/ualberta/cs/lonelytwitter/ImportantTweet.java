package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by camclean on 9/16/15.
 */
public class ImportantTweet extends Tweet {
    public ImportantTweet(String tweet) {
        super(tweet);
    }

    public ImportantTweet(Date date, String tweet) {
        super(date, tweet);
        this.setText(tweet);
        this.date = date;
    }

    public Boolean isImportant() {
        return Boolean.TRUE;
    }

    @Override
    public String getText() {
        return "!!!" + super.getText();
    }
}
