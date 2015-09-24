package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter; // Writes text to a character-output stream
import java.io.OutputStreamWriter; // Bridge from character streams to byte streams
import java.lang.reflect.Type; // Gives info on an object's type
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson; //  generic Gson starting point
import com.google.gson.reflect.TypeToken; // allows generic types

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav"; // hardcoded?
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);

		// Button saveButton = (Button) findViewById(R.id.save);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);

		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
//				saveInFile(text, new Date(System.currentTimeMillis()));
//				finish();
                tweets.add(new NormalTweet(text));
                saveInFile();
                adapter.notifyDataSetChanged();
                bodyText.setText("");


            }
		});

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tweets.clear();
                saveInFile();
                adapter.notifyDataSetChanged();
            }
        });
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		String[] tweets = loadFromFile(); // Not storing/reading as string anymore. Writing to disk.
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        loadFromFile(); // Load written data directly
        adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // data has changed, views that use it should refresh
	}

//	private String[] loadFromFile() { // No longer loading from a string array
//		ArrayList<String> tweets = new ArrayList<String>();
    private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//			String line = in.readLine(); //  No more strings!
//			while (line != null) {
//				tweets.add(line);
//				line = in.readLine();
//			}
            Gson gson = new Gson();
//          https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/24
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
            tweets = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
            tweets = new ArrayList<Tweet>(); // Tweets list doesn't exist, create it
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
            throw new RuntimeException(e);
		}
//		return tweets.toArray(new String[tweets.size()]); // now void function
	}
	
	//private void saveInFile(String text, Date date) {
    private void saveInFile() {
		try {
//			FileOutputStream fos = openFileOutput(FILENAME,
//					Context.MODE_APPEND);
//			fos.write(new String(date.toString() + " | " + text)
//					.getBytes());
//			fos.close();
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
            throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
            throw new RuntimeException(e);
		}
	}
}