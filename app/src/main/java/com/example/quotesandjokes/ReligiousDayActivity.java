package com.example.quotesandjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReligiousDayActivity extends AppCompatActivity {
    CircleImageView c1,c2,c3,c4,c5,c6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religious_day);
        c1=findViewById(R.id.c1);c1.setTooltipText("Raksha Bandhan");
        c2=findViewById(R.id.c2);c2.setTooltipText("Bhai Dooj");
        c3=findViewById(R.id.c3);c3.setTooltipText("Navratri");
        c4=findViewById(R.id.c4);c4.setTooltipText("Makar Sankranti");
        c5=findViewById(R.id.c5);c5.setTooltipText("Karwa");
        c6=findViewById(R.id.c6);c6.setTooltipText("Ganesh Chaturthi");
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RakshaActivity.class));
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BhaiActivity.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NavratriActivity.class));
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MakarActivity.class));
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KarwaActivity.class));
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GaneshActivity.class));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.dark_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.dark) {
            startActivity(new Intent(getApplicationContext(),DarkModeActivity.class));return true;
        }
        if (item.getItemId()==R.id.home){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));return true;
        }
        return  false;
    }
}