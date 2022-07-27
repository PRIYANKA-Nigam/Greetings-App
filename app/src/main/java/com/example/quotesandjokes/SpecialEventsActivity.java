package com.example.quotesandjokes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SpecialEventsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_events);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.vv);
        adapter=new MyFragmentAdapter(getSupportFragmentManager());
        adapter.AddFragment(new BirthDayFragment(),"Birthday");
        adapter.AddFragment(new WeddingFragment(),"Wedding");
        adapter.AddFragment(new JobFragment(),"Job");
        adapter.AddFragment(new HealthFragment(),"Health");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}