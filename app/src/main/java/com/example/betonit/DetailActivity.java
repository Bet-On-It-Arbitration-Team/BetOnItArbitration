package com.example.betonit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = "DetailActivity";
    protected CaseAdapter adapter;
    private Context context;
    protected List<Case> allPosts;
    public String no;
    Case post;
    public Date start_date;
    public Date end_date;
    public String user2 = "I am user2's explanation";
    public String user1 = "I am user1's explanation";
    String objectID;
    TextView tvUsername;
    TextView status;
    TextView description1;
    TextView description2;
    Button prevclass;
    Button decline;
    Button accept;

    /*public DetailActivity(Case post) {
        this.post = post;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvUsername = findViewById(R.id.tvUsername);
        status = findViewById(R.id.status);
        description1 = findViewById(R.id.description1);
        description2 = findViewById(R.id.description2);
        prevclass = findViewById(R.id.retbtn);
        decline = findViewById(R.id.decline);
        accept = findViewById(R.id.accept);
        /*post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Success!
                    String objectID = post.getObjectId();

                } else {
                    // Failure!
                }
            }
        });*/

        final String stat = getIntent().getStringExtra("status");
        String desc1 = getIntent().getStringExtra("description1");
        String desc2 = getIntent().getStringExtra("description2");
        status.setText(stat);
        description1.setText(desc1);
        description2.setText(desc2);

        ParseQuery<Case> query = ParseQuery.getQuery(Case.class);
        query.whereEqualTo(Case.KEY_CASE_STATUS, stat);
        query.findInBackground(new FindCallback<Case>() {
            public void done(List<Case> cases, ParseException e) {
                if (e != null) {
                    // Access the array of results here
                    Log.e(TAG, "Error: " + e.getMessage(), e);
                }
                for (Case case1 : cases) {
                    no = case1.getObjectId();
                    Log.i(TAG, "MyCase: " + case1.getKeyCaseBetId().getObjectId().toString());
                    Log.i(TAG, "Arbitrator: " + case1.getKeyCaseBetId().getObjectId().toString());
                }

            }
        });

        prevclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFactsList = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(goToFactsList);
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Case> query = ParseQuery.getQuery(Case.class);
                //query.whereEqualTo("case_Status", stat);
                query.getInBackground(no, new GetCallback<Case>() {
                    public void done(Case event, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            Toast.makeText(getApplicationContext(), "Case Has been Accepted", Toast.LENGTH_LONG).show();
                        }
                        event.put("case_Status", "ARBITRATION");
                        event.saveInBackground();
                        Intent goToFactsList = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(goToFactsList);
                    }
                });
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = new ParseQuery("Case");
                //post.getParseObject("Case").getObjectId();
                query.getInBackground(no, new GetCallback<ParseObject>() {
                    public void done(ParseObject event, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            Toast.makeText(getApplicationContext(), "Case has been rejected", Toast.LENGTH_LONG).show();
                        }
                        event.put("case_Status", "PENDING");
                        event.saveInBackground();
                        Intent goToFactsList = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(goToFactsList);
                    }
                });
            }
        });
    }

    /*public void updateObject() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Case");

        // Retrieve the object by id
        query.getInBackground("<zQx5cs7Lo2>", new GetCallback<ParseObject>() {
            public void done(ParseObject entity, ParseException e) {
                if (e == null) {
                    // Update the fields we want to
                    /*entity.put("case_Bet_ID", new ParseObject("Bet"));
                    entity.put("case_Date_Start", new Date());
                    entity.put("case_Date_End", new Date());
                    entity.put("case_Status", "DECLINED");
                    /*entity.put("user1_Case", "A string");
                    entity.put("user2_Case", "A string");
                    entity.put("case_Arbitrator", ParseUser.getCurrentUser());

                    // All other fields will remain the same
                    entity.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // No error, the object was saved
                                Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_LONG).show();
                            } else {
                                // Error saving object, print the logs
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }*/
    private void saveCase(String status, ParseUser currentUser) {
        Case cases = new Case();
        cases.setKeyCaseStatus(status);
        cases.setKeyCaseArbitrator(currentUser);
        cases.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(DetailActivity.this, "Error While Saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Case Save Successful");
            }
        });
    }
}