package com.example.quotesandjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NavratriActivity extends AppCompatActivity {String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navratri);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        arrayList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.ll);
arrayList.add("Wishing you the energy of Maa Durga, the poise of Maa Saraswati; Wishing you a warm and blessed Navratri.");
arrayList.add("Happy Chaitra Navratri to you and your family. May Maa Durga bless you with goodness and prosperity.");
arrayList.add("Happy Chaitra Navratri. Let darkness pass and the road be paved with happiness and prosperity.");
arrayList.add("Happiness, peace, good health, wealth, prosperity and harmony – let Goddess Durga bring it all to your life. Happy Chaitra Navratri.");
arrayList.add("May Maa Durga bestow upon you and your family nine forms of blessings- Fame, Name, Wealth, Prosperity, Happiness, Education, Health, Power, and Commitment. Happy Navratri!");
arrayList.add("Happy Navratri to you and your family");
arrayList.add("Aap sabhi ko Durga Ashtami ki dheron shubh kamnayein. Jai Mata Di");
arrayList.add("Here's extending my heartfelt greetings and best wishes on the joyous occasion of Durga Ashtami.");
arrayList.add("wishing all those celebrating Chaitra Navratri, a very Happy Durga Ashtami.");
        adapter=new WishAdapter(getApplicationContext(),arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder a = new AlertDialog.Builder(NavratriActivity.this);
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="na";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getApplicationContext(),ShareActivity.class);
                                intent.putExtra("img",s);intent.putExtra("flag",flag);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String s = arrayList.get(position);
                                fileContent = s;
                                FileOutputStream fileOutputStream;
                                try { fileOutputStream=openFileOutput(fileName, Context.MODE_PRIVATE); fileOutputStream.write(fileContent.getBytes());
                                    fileOutputStream.close();
                                    Toast.makeText(getApplicationContext(),fileName + " Data Saved",Toast.LENGTH_LONG).show();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace(); }



                            }
                        });
                AlertDialog alert = a.create();
                alert.setTitle("Save/Share this Wish");
                alert.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.fav_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fav:
                SharedPreferences sh=  getApplicationContext().getSharedPreferences("wish", Context.MODE_PRIVATE);
                Set<String> set = sh.getStringSet("wished", new HashSet<String>());
                ArrayList<String> arrayList =new ArrayList<>(set);
                Intent intent=new Intent(getApplicationContext(), ReligiousEventsFavActivity.class);
                intent.putStringArrayListExtra("quote", arrayList);
                Toast.makeText(getApplicationContext(),"Displaying in Favourite List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent); break;
        }
        return super.onOptionsItemSelected(item);
    }
}