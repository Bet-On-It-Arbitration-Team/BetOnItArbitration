package com.example.betonit;

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

import java.util.List;

public class PendingDetailActivity extends AppCompatActivity {
    public static final String TAG = "PendingDetailActivity";
    public String no;
    TextView tvUsername;
    TextView status;
    TextView description1;
    TextView description2;
    Button prevclass;
    Button decline;
    Button accept;


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


        final String stat = getIntent().getStringExtra("status");
        String desc1 = getIntent().getStringExtra("description1");
        String desc2 = getIntent().getStringExtra("description2");
        status.setText(stat);
        description1.setText(desc1);
        description2.setText(desc2);

        // Search for Cases with the given status
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
                Intent goToFactsList = new Intent(PendingDetailActivity.this, MainActivity.class);
                startActivity(goToFactsList);
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Case> query = ParseQuery.getQuery(Case.class);

                query.getInBackground(no, new GetCallback<Case>() {
                    public void done(Case event, ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Case Has been Accepted", Toast.LENGTH_LONG).show();
                        }
                        event.put("case_Status", "ARBITRATION");
                        event.saveInBackground();
                        Intent goToFactsList = new Intent(PendingDetailActivity.this, MainActivity.class);
                        startActivity(goToFactsList);
                    }
                });
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = new ParseQuery("Case");
                query.getInBackground(no, new GetCallback<ParseObject>() {
                    public void done(ParseObject event, ParseException e) {
                        if (e == null) {

                            Toast.makeText(getApplicationContext(), "Case has been rejected", Toast.LENGTH_LONG).show();
                        }
                        event.put("case_Status", "PENDING");
                        event.saveInBackground();
                        Intent goToFactsList = new Intent(PendingDetailActivity.this, MainActivity.class);
                        startActivity(goToFactsList);
                    }
                });
            }
        });
    }
}