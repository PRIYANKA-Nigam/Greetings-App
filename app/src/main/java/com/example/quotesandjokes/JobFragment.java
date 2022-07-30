package com.example.quotesandjokes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class JobFragment extends Fragment { String fileName="",filePath="",fileContent ="";
    ViewPager viewPager;
    TabLayout tabLayout;
    public JobFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_job, container, false);
        viewPager=view.findViewById(R.id.v1);
        tabLayout=view.findViewById(R.id.j1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        JobHealthAdapter adapter =new JobHealthAdapter(getChildFragmentManager());
        adapter.AddFragment(new firstJobFragment(),"First Job");
        adapter.AddFragment(new NewJobFragment(),"New Job");
        adapter.AddFragment(new JobPromotionFragment(),"Job Promotion");
        viewPager.setAdapter(adapter);

    }
}