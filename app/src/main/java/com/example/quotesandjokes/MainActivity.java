package com.example.quotesandjokes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PictureInPictureParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentAdapter adapter;
 private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.floatingActionButton1);
        floatingActionButton.setTooltipText("Check list of favourites");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         startActivity(new Intent(MainActivity.this,FavouritesActivity.class));
            }
        });
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.vv);
        adapter=new MyFragmentAdapter(getSupportFragmentManager());
        adapter.AddFragment(new WishesFragment(),"Wish");
        adapter.AddFragment(new SloganFragment(),"Slogan");
        adapter.AddFragment(new QuotesFragment(),"Quote");
        adapter.AddFragment(new JokesFragment(),"Joke");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onUserLeaveHint () {
        PictureInPictureParams pictureInPictureParams= new PictureInPictureParams.Builder().build();
        enterPictureInPictureMode(pictureInPictureParams);

    }

}