package com.example.quotesandjokes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BirthDayFragment extends Fragment {
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    public BirthDayFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        arrayList=new ArrayList<>();
        arrayList.add("Happy Birthday To You");arrayList.add("Happy 24th Birthday To You");
        arrayList.add("Wishing you a Happiest Birthday");arrayList.add("Many-Many Happy return of the day");
        arrayList.add("Wishing you many more candles to blow");arrayList.add("Happy Aloha Birthday");
        arrayList.add("Happy Level Up Day");
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fav_icon,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fav:
                SharedPreferences sh=  getContext().getSharedPreferences("wish", Context.MODE_PRIVATE);
                Set<String> set = sh.getStringSet("wished", new HashSet<String>());
                ArrayList<String> arrayList =new ArrayList<>(set);
                Intent intent=new Intent(getContext(), SpecialEventsFavActivity.class);
                intent.putStringArrayListExtra("quote", arrayList);
                Toast.makeText(getContext(),"Displaying in Favourite List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent); break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_birth_day, container, false);
        listView=view.findViewById(R.id.ll);
        adapter=new WishAdapter(getContext(),arrayList);
        listView.setAdapter(adapter);
       return view;
    }
}