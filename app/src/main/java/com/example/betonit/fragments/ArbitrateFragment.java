package com.example.betonit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.betonit.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ArbitrateFragment extends Fragment {

    public ArbitrateFragment() {
        // Required empty public constructor
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                FragmentManager fragmentManager = getChildFragmentManager();

                Fragment pendingCasesFragment =  new PendingCasesFragment();
                Fragment myCasesFragment = new MyCasesFragment();
                Fragment ratedCasesFragment = new RatedCasesFragment();

                Fragment fragment;
                switch (menuItem.getItemId())
                {
                    case R.id.action_pending_cases:
                        fragment = pendingCasesFragment;
                        break;
                    case R.id.action_rated_cases:
                        fragment = ratedCasesFragment;
                        break;
                    case R.id.action_my_cases:
                    default:
                        fragment = myCasesFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flArbitrateContent, fragment).commit();
                return true;
            }
        };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View v = inflater.inflate(R.layout.fragment_arbitrate, container, false);

        BottomNavigationView bottomArbitrationNav = v.findViewById(R.id.bottom_arbitration_navigation);
        bottomArbitrationNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        // Keep this. It defaults the arbitratefragment to "My Cases"
        if (savedInstanceState == null)
        {
            bottomArbitrationNav.setSelectedItemId(R.id.action_my_cases);
        }

        // Inflate the layout for this fragment
        return v;
    }
}