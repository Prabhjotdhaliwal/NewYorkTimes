package com.example.newyorktimes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///tyyuy
        System.out.println("dgffg");
        setUpNavigation();
    }

    public void setUpNavigation() {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        drawerLayout = findViewById(R.id.drawer_layout);


        navigationView = findViewById(R.id.navigation_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);
        drawerLayout.closeDrawers();
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.firstmenu:

                Toast.makeText(getApplicationContext(), "First clicked", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.firstFragment);
                break;

            case R.id.secondmenu:

                Toast.makeText(getApplicationContext(), "Second clicked", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.secondFragment);
                break;

            case R.id.thirdmenu:

                Toast.makeText(getApplicationContext(), "Third clicked", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.thirdFragment);
                break;
            case R.id.fourthmenu:

                Toast.makeText(getApplicationContext(), "Third clicked", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.fourthFragment);
                break;
                case R.id.fifthmenu:

                Toast.makeText(getApplicationContext(), "Third clicked", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.fifthFragment);
                break;

        }

        return false;
    }
}
