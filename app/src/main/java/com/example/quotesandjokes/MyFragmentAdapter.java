package com.example.quotesandjokes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> list=new ArrayList<>();
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
    public void AddFragment(Fragment fragment,String string){
        fragmentList.add(fragment);
        list.add(string);
    }
}
