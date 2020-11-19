package com.example.betonit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.betonit.Case;
import com.example.betonit.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class RatedCasesFragment extends Fragment {

    public static final String TAG = "RatedCasesFragment";
    private RecyclerView rvRatedCases;

    public RatedCasesFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rated_cases, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRatedCases = view.findViewById(R.id.rvRatedCases);

        queryRatedCases();
    }

    private void queryRatedCases()
    {
        // Define the class we would like to query
        ParseQuery<Case> query = ParseQuery.getQuery(Case.class);
        // Define our query conditions
        query.whereNotEqualTo(Case.KEY_CASE_ARBITRATOR, ParseUser.getCurrentUser());
        query.whereEqualTo(Case.KEY_CASE_STATUS, "RESOLVED");
        // Execute the find asynchronously
        query.findInBackground(new FindCallback<Case>() {
            public void done(List<Case> cases, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                    for (int i = 0; i < cases.size(); i++)
                    {
                        Log.i(TAG, "RatedCase: " + cases.get(i).getKeyCaseBetId().getObjectId().toString());
                        Log.i(TAG, "Arbitrator: " + cases.get(i).getKeyCaseArbitrator().getObjectId().toString());
                    }
//                    Toast.makeText(TodoItemsActivity.this, firstItemId, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Error: " + e.getMessage(), e);
                }
            }
        });
    }
}