package com.example.quotesandjokes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

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
}