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
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MyCasesFragment extends Fragment {

    public static final String TAG = "MyCasesFragment";
    private RecyclerView rvMyCases;

    public MyCasesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cases, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMyCases = view.findViewById(R.id.rvMyCases);

        queryMyCases();
    }

    private void queryMyCases()
    {
        // Define the class we would like to query
        ParseQuery<Case> query = ParseQuery.getQuery(Case.class);
        // Define our query conditions
        query.whereEqualTo(Case.KEY_CASE_ARBITRATOR, ParseUser.getCurrentUser());
        query.whereNotEqualTo(Case.KEY_CASE_STATUS, "RESOLVED");
        // Execute the find asynchronously
        query.findInBackground(new FindCallback<Case>() {
            public void done(List<Case> cases, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                    if(!cases.isEmpty())
                    {
                        for (int i = 0; i < cases.size(); i++)
                        {
                            Log.i(TAG, "MyCase: " + cases.get(i).getKeyCaseBetId().getObjectId().toString());
                            Log.i(TAG, "Arbitrator: " + cases.get(i).getKeyCaseArbitrator().getObjectId().toString());
                        }
                    }
                    else
                    {

                    }
//
                } else {
                    Log.e(TAG, "Error: " + e.getMessage(), e);
                }
            }
        });
    }
}