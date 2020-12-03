package com.example.betonit;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.betonit.fragments.ArbitrateFragment;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;

//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "BettorActivity";
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a toolbar to replace ActionBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        
        nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
    }

    private ActionBarDrawerToggle setupDrawerToggle()
    {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView nvDrawer)
    {
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem menuItem)
    {
        Fragment fragment = null;
        Class fragClass = null;
        switch (menuItem.getItemId())
        {
            case R.id.nav_profile_fragment:
//                fragClass = ProfileFragment.class;
                break;
            case R.id.nav_search_fragment:
//                fragClass = SearchFragment.class;
                break;
            case R.id.nav_bet_fragment:
//                    fragClass = BetFragment.class;
                break;
            case R.id.nav_settings_fragment:
//                fragClass = SettingsFragment.class;
                break;
            case R.id.nav_sign_out_fragment:
 //               fragClass = SignOutFragment.class;
                ParseUser.logOut();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_arbitrate_fragment:
            default:
                fragClass = ArbitrateFragment.class;
        }

        try
        {
            fragment = (Fragment) fragClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        FragmentManager fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}