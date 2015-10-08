package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by carsonmclean on 30/09/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver {
    public TweetListTest() { // Need to call the default constructor
        super(LonelyTwitterActivity.class);
    }

    private boolean wasNotified = false;



    // Test may not get done in order. Random. EG: testTeardown could run before testSetup

    public void testDeleteTweet() { // JUnit requires that you start with 'test' prefix
                                    // Anything without 'test' will be ignored by testing framework
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello!");
        tweetList.add(tweet);
        //int tweetCount = tweetList.getTweetCount();
        tweetList.remove(tweet);
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
        Tweet duplicate = new NormalTweet("Hello!");
        assertTrue(tweetList.hasTweet(tweet));
        try {
            tweetList.add(tweet);
            Assert.fail();
        }
        catch(IllegalArgumentException e) {
        }
    }

    public void testTweetCount() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello!");
        tweetList.add(tweet);
        Tweet tweet2 = new NormalTweet("Second!");
        tweetList.add(tweet2);
        Tweet tweet3 = new NormalTweet("Third!");
        tweetList.add(tweet3);
        assertTrue(tweetList.getCount() == 3);
    }


//    public void testGetTweets() {
//        TweetList tweetList = new TweetList();
//        Tweet tweet = new NormalTweet("Hello!");
//        tweetList.add(tweet);
//        Tweet second = new NormalTweet("My second tweet");
//        tweetList.add(tweet);
//
//        List<Tweet> returnedList = tweetList.getTweets();
//        Date date = returnedList[0].date();
//        for (int i = 0; i < tweetList.getCount(); i++) {
//            assertTrue(date < returnedList[i+1]);
//        }
//    }

    public void testTweetListChanged() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihihi");
        tweetList.addObserver(this);
        wasNotified = false;
        assertFalse(wasNotified);
        tweetList.add(tweet);
        assertTrue(wasNotified);
    }

    public void myNotify() {
        wasNotified = true;
    }
}