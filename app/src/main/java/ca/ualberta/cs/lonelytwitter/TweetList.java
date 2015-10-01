package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by carsonmclean on 30/09/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
       if (!tweets.contains(tweet)) {
           tweets.add(tweet);
       } else {
           throw new IllegalArgumentException();
       }
    }

    public void remove(Tweet tweet) {
        tweets.remove(tweet);
    }

    public boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    public List<Tweet> getTweets() {
        Collections.sort(tweets, new Comparator<Tweet>() {
            public int compare(Tweet tweet1, Tweet tweet2) {
                return tweet1.getDate().compareTo(tweet2.getDate());
            }
        });

        return tweets;
    }

    public int getCount() {
        return tweets.size();
    }
}
