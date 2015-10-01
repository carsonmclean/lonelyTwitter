package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by carsonmclean on 30/09/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        tweets.add(tweet);
    }

    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    public boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    public Tweet getTweet() {
        return tweets.get(0);
    }
}
