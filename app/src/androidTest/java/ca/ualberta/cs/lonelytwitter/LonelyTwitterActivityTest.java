package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditTweet() {
        //when you call getActivity, Android will start the app and the activity
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        // reset the app to a known state
        activity.getTweets().clear(); // needs Getter for Tweets

        // add a tweet using the UI
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("test tweet"); // neds to be on UI thread
            }
        });
        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton(); //  need to refactor, extract, field to make it non-local
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // make sure the tweet was actually added
        final ListView oldTweetsList = activity.getOldTweetsList(); // Ask the UI, not the model's array
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("test tweet", tweet.getText());

        // Click on tweet to edit
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // ensure the tweet editor activity starts up

        // https://developer.android.com/training/activity-testing/activity-functional-testing.html; 2015-10-14
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);


        // Validate that ReceiverActivity is started
        //TouchUtils.clickView(this, sendToReceiverButton);
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // test that the editor starts up with the right tweet in it to edit;
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                assertTrue(bodyText == "test tweet");
            }
        });
        getInstrumentation().waitForIdleSync();


        // test that we can edit that tweet
        bodyText = "Edited the value";
        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });



        // test that we can push some kind of save button for last tweet

        // the new modified tweet text was actually saved

        // the new modified text iis displayed on the other activity


        // clean up our activities at the end of out test
        receiverActivity.finish();
    }


}