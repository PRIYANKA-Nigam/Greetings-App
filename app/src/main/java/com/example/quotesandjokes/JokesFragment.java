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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class JokesFragment extends Fragment {
TextView textView; Button button;  ImageView imageView;
    ArrayList<String> list;
    Random random = new Random();
    public JokesFragment() {

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
                SharedPreferences s=  getContext().getSharedPreferences("jokes", Context.MODE_PRIVATE);
                Set<String> set = s.getStringSet("notes", new HashSet<String>());
                ArrayList<String> arrayList =new ArrayList<>(set);
                Intent intent=new Intent(getContext(), JokesFavActivity.class);
                intent.putStringArrayListExtra("joke", arrayList);
                Toast.makeText(getContext(),"Displaying in Favourite List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.fragment_jokes, container, false);
    textView=view.findViewById(R.id.tt);
    button=view.findViewById(R.id.button);
        imageView=view.findViewById(R.id.img);imageView.setTooltipText("Add to Favourites");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a= textView.getText().toString();
                SharedPreferences sh=   getContext().getSharedPreferences("jokes", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>();
                set.add(a);
                sh.edit().putStringSet("notes",set).commit();
                Toast.makeText(getContext(),"Joke Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r =random.nextInt((15+1)-1)+1;
                String randQuotes="";
                switch (r){
                    case 1: randQuotes=getString(R.string.joke1); break;
                    case 2: randQuotes=getString(R.string.joke2); break;
                    case 3: randQuotes=getString(R.string.joke3); break;
                    case 4: randQuotes=getString(R.string.joke4); break;
                    case 5: randQuotes=getString(R.string.joke5); break;
                    case 6: randQuotes=getString(R.string.joke6); break;
                    case 7: randQuotes=getString(R.string.joke7); break;
                    case 8: randQuotes=getString(R.string.joke8); break;
                    case 9: randQuotes=getString(R.string.joke9); break;
                    case 10: randQuotes=getString(R.string.joke10); break;
                    case 11: randQuotes=getString(R.string.joke11); break;
                    case 12: randQuotes=getString(R.string.joke12); break;
                    case 13: randQuotes=getString(R.string.joke13); break;
                    case 14: randQuotes=getString(R.string.joke14); break;
                    case 15: randQuotes=getString(R.string.joke15); break;
                }
                textView.setText(randQuotes);
            }
        });
    return  view;
    }

}