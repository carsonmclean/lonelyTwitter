package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by carsonmclean on 30/09/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() { // Need to call the default constructor
        super(LonelyTwitterActivity.class);
    }

    // Test may not get done in order. Random. EG: testTeardown could run before testSetup

    public void testDeleteTweet() { // JUnit requires that you start with 'test' prefix
                                    // Anything without 'test' will be ignored by testing framework
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello!");
        tweetList.add(tweet);
        //int tweetCount = tweetList.getTweetCount();
        tweetList.delete(tweet);
        //int newTweetCount = tweetList.getTweetCount();
        //assertTrue((tweetCount - 1) ==  newTweetCount);
        assertFalse(tweetList.hasTweet(tweet));
    }

    public void testHasTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello!");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));


    }

    public void testAddTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello!");
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testTweetCount() {

    }

    public void testGetTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello!");
        tweetList.add(tweet);
        Tweet returnedTweet = tweetList.getTweet();
        assertTrue((tweet.date.equals(returnedTweet.date)) && (tweet.getText().equals(returnedTweet.getText())));
    }

    public void testGetTweetType() {

    }

}