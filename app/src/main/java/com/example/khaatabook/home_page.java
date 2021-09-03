package com.example.khaatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_page extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomNavigationView=findViewById(R.id.navBar);

        //loading the default fragment
        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        bottomNavigationView.setOnNavigationItemSelectedListener(this);




    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {

        Fragment fragment=null;


        switch (item.getItemId()){
            case R.id.navigation_home:
          fragment=new HomeFragment();
          break;

            case R.id.navigation_money:
                fragment=new MoneyFragment();
                break;

            case R.id.navigation_more:
                fragment=new MoreFragment();
                break;




        }


        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {


        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame,fragment)
                    .commit();
            return true;

        }
      return false;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}