package com.example.taskmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.taskmanager.LoginActivity;
import com.example.taskmanager.R;
import com.example.taskmanager.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AddToDoActivity.class));
        });
        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                binding.appBarMain.toolbar, R.string.nav_header_title,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            HomeFragment myFrag = new HomeFragment();

            Intent intent = getIntent();
            if (null != intent) {
                //Null Checking
                int id = intent.getIntExtra("cat_id",0);
                Bundle bundle = new Bundle();
                if (id != 0){
                    bundle.putInt("cat_id", id);
                    myFrag.setArguments(bundle);
                }
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,
                    myFrag).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home)
            startActivity(new Intent(this, MainActivity.class));
        else if(item.getItemId() == R.id.nav_categories)
            startActivity(new Intent(this, CategoriesActivity.class));
        else if(item.getItemId() == R.id.nav_settings)
            startActivity(new Intent(this, SettingsActivity.class));
        else if(item.getItemId() == R.id.nav_notifications)
            startActivity(new Intent(this, NotificationsActivity.class));
        else if(item.getItemId() == R.id.nav_logout)
            startActivity(new Intent(this, LoginActivity.class));
        drawer.close();
        return true;
    }
}