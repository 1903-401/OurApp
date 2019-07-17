package com.example.timerpart1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle mtoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Setting up the toolbar and then action bar
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Setting up the drawer layout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mtoggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mtoggle.syncState();
        drawer.addDrawerListener(mtoggle);

    }


    // when the back button is pressed

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // setting up the menu

    public boolean onCreateOptionsMenu(Menu menu) {

        // inflate the menu; adds items to action bar if it is present
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    // when you choose an item

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // to navigate between fragments
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();


        if (id == R.id.nav_home) {
            fragment = new introFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        } else if (id == R.id.nav_firstTimer) {
            fragment = new pomodoroFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        } else if (id == R.id.nav_secondTimer) {
            fragment = new timerFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        } else if (id == R.id.nav_calendar) {
            fragment = new calendarFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        } else if (id == R.id.nav_list) {
            fragment = new todolistFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        } else if (id == R.id.nav_notes) {
            fragment = new notesFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
