package com.example.quotesandjokes.Quote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotesandjokes.R;
//import com.example.quotesandjokes.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuotesFragment extends Fragment {
    TextView textView; Button button;
    ImageView imageView;
    Random random = new Random();
//    RecyclerViewAdapter arrayAdapter;
//    RecyclerView recyclerView;
    ArrayList<String> list;
    public QuotesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        list=new ArrayList<String>();
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
              SharedPreferences s=  getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
                Set<String> set = s.getStringSet("notes", new HashSet<String>());
                ArrayList<String> arrayList =new ArrayList<>(set);
          //  String q =  s.getString("name", null);
            Intent intent=new Intent(getContext(), QuotesFavActivity.class);
            intent.putStringArrayListExtra("quote", arrayList);
                Toast.makeText(getContext(),"Displaying in Favourite List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_quotes, container, false);
        textView=view.findViewById(R.id.tt);
        button=view.findViewById(R.id.button);
        imageView=view.findViewById(R.id.img);imageView.setTooltipText("Add to Favourites");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a= textView.getText().toString();
             SharedPreferences sh=   getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>();
                set.add(a);
                        sh.edit().putStringSet("notes",set).commit();
                Toast.makeText(getContext(),"Quote moved to Favourites on the menu bar",Toast.LENGTH_LONG).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r =random.nextInt((15+1)-1)+1;
                String randQuotes="";
                switch (r){
                    case 1: randQuotes=getString(R.string.quotes1); break;
                    case 2: randQuotes=getString(R.string.quotes2); break;
                    case 3: randQuotes=getString(R.string.quotes3); break;
                    case 4: randQuotes=getString(R.string.quotes4); break;
                    case 5: randQuotes=getString(R.string.quotes5); break;
                    case 6: randQuotes=getString(R.string.quotes6); break;
                    case 7: randQuotes=getString(R.string.quotes7); break;
                    case 8: randQuotes=getString(R.string.quotes8); break;
                    case 9: randQuotes=getString(R.string.quotes9); break;
                    case 10: randQuotes=getString(R.string.quotes10); break;
                    case 11: randQuotes=getString(R.string.quotes11); break;
                    case 12: randQuotes=getString(R.string.quotes12); break;
                    case 13: randQuotes=getString(R.string.quotes13); break;
                    case 14: randQuotes=getString(R.string.quotes14); break;
                    case 15: randQuotes=getString(R.string.quotes15); break;

                }
                textView.setText(randQuotes);
            }
        });
        return  view;
    }
}