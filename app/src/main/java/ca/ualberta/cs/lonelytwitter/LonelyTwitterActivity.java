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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson; //  generic Gson starting point
import com.google.gson.reflect.TypeToken; // allows generic types

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav"; // model

	public Button getSaveButton() {
		return saveButton;
	}

	private Button saveButton;

	public EditText getBodyText() {
		return bodyText;
	}

	private EditText bodyText; // model
	private ListView oldTweetsList; // model

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); // model
	private ArrayAdapter<Tweet> adapter; // model

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { // view

		super.onCreate(savedInstanceState); // view
		setContentView(R.layout.main); // view

		bodyText = (EditText) findViewById(R.id.body); // model

		// Button saveButton = (Button) findViewById(R.id.save);
		saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear); // view

		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); // model

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // controller
				setResult(RESULT_OK); // controller
				String text = bodyText.getText().toString(); // model
//				saveInFile(text, new Date(System.currentTimeMillis()));
//				finish();
				tweets.add(new NormalTweet(text)); // controller
				saveInFile(); // model
				adapter.notifyDataSetChanged(); // view
				bodyText.setText("");


			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener()) {
			public void onItemClick Object parent
					private ac;
			(AdapterView<?> parent, View view, int
			this; )
		}

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // view
                tweets.clear(); // controller
                saveInFile(); // Open stream to disk, write text from buffer // controller
                adapter.notifyDataSetChanged(); // New data, so update the views that display data // view
            }
        });
	}

	@Override
	protected void onStart() { // view
		// TODO Auto-generated method stub
		super.onStart(); // view
//		String[] tweets = loadFromFile(); // Not storing/reading as string anymore. Writing to disk.
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        loadFromFile(); // Load written data directly // model
        adapter = new ArrayAdapter<Tweet>(this, // // model
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter); // model
        adapter.notifyDataSetChanged(); // data has changed, views that use it should refresh // model
	}

//	private String[] loadFromFile() { // No longer loading from a string array
//		ArrayList<String> tweets = new ArrayList<String>();
    private void loadFromFile() { // model
		try { //model
			FileInputStream fis = openFileInput(FILENAME); // model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); // model
//			String line = in.readLine(); //  No more strings!
//			while (line != null) {
//				tweets.add(line);
//				line = in.readLine();
//			}
            Gson gson = new Gson(); // model
//          https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/24
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); // model
            tweets = gson.fromJson(in, listType); // model

		} catch (FileNotFoundException e) { // model
			// TODO Auto-generated catch block
			// e.printStackTrace();
            tweets = new ArrayList<Tweet>(); // Tweets list doesn't exist, create it // model
		} catch (IOException e) { // model
			// TODO Auto-generated catch block
			// e.printStackTrace();
            throw new RuntimeException(e); // model
		}
//		return tweets.toArray(new String[tweets.size()]); // now void function
	}
	
	//private void saveInFile(String text, Date date) {
    private void saveInFile() { // model
		try { // model
//			FileOutputStream fos = openFileOutput(FILENAME,
//					Context.MODE_APPEND);
//			fos.write(new String(date.toString() + " | " + text)
//					.getBytes());
//			fos.close();
            FileOutputStream fos = openFileOutput(FILENAME, 0); // model
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); // model
		} catch (FileNotFoundException e) { // model
			// TODO Auto-generated catch block
			// e.printStackTrace();
            throw new RuntimeException(e); // model
		} catch (IOException e) { // model
			// TODO Auto-generated catch block
			//e.printStackTrace();
            throw new RuntimeException(e); // model
		}
	}
}