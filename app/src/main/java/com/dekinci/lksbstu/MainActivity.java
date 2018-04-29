package com.dekinci.lksbstu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dekinci.lksbstu.fragment.DocsFragment;
import com.dekinci.lksbstu.fragment.NewsFragment;
import com.dekinci.lksbstu.fragment.ProfileFragment;
import com.dekinci.lksbstu.fragment.ScheduleFragment;
import com.dekinci.lksbstu.fragment.TasksFragment;
import com.dekinci.lksbstu.fragment.TetATetFragment;
import com.dekinci.lksbstu.model.CurrentUser;
import com.dekinci.lksbstu.model.PolyManager;
import com.example.hackaton.goprojectpisat.R;

public class MainActivity extends AppCompatActivity implements
        ProfileFragment.LogOutListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initFragment();
        initDrawer();
    }

    private void initFragment() {
        Fragment fragment = new ProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentHolder, fragment);
        fragmentTransaction.commit();
    }

    private void initDrawer() {
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment = null;
            if (id == R.id.nav_schedule) {
                fragment = new ScheduleFragment();
            } else if (id == R.id.nav_messages) {
                fragment = new TetATetFragment();
            } else if (id == R.id.nav_news) {
                fragment = new NewsFragment();
            } else if (id == R.id.nav_settings) {
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
            } else if (id == R.id.nav_about) {
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.commit();
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        View header = navigationView.getHeaderView(0);
        initHeaderButton(header);
        initHeaderProfile(header);
    }

    private void initHeaderButton(View view) {
        LinearLayout prfiBtn = view.findViewById(R.id.profileViewClickable);
        prfiBtn.setOnClickListener(
                v -> {
                    goTo(new ProfileFragment());

                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
        );
    }

    private void initHeaderProfile(View view) {
        CurrentUser user = PolyManager.get().getUser();

        user.getUser(u -> runOnUiThread(() -> {
            TextView surname = view.findViewById(R.id.nav_header_surname);
            surname.setText(u.getSurname());

            TextView name = view.findViewById(R.id.nav_header_name);
            name.setText(u.getName());

            TextView login = view.findViewById(R.id.nav_header_login);
            login.setText(u.getLogin());
        }));
    }

    private void goTo(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void logout() {
        PolyManager.get().getApi().logOut();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
