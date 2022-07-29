package com.example.quotesandjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class InternationalActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.vv);
        adapter=new MyFragmentAdapter(getSupportFragmentManager());
        adapter.AddFragment(new DiwaliFragment(),"Diwali");
        adapter.AddFragment(new HoliFragment(),"Holi");
        adapter.AddFragment(new XmasFragment(),"Xmas");
        adapter.AddFragment(new EidFragment(),"Eid");
        adapter.AddFragment(new NewYearFragment(),"New Year");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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