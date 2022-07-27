package com.example.quotesandjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class QuotesFavActivity extends AppCompatActivity { String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList =new ArrayList<>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_fav);
        fileName = "quotes.txt";
        filePath = "MyQuotes";
        try {
            ArrayList<String> ss = getIntent().getStringArrayListExtra("quote");
            arrayList.addAll(ss);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.ll);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder a = new AlertDialog.Builder(QuotesFavActivity.this);
                a.setMessage("do you want to Save or Share this quote ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String s = arrayList.get(position);
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Interesting Quote : " + s);
                                sendIntent.setType("text/plain");
                                Intent shareIntent = Intent.createChooser(sendIntent, null);
                                startActivity(shareIntent);
                                Toast.makeText(getApplicationContext(), "Sharing Quote ...", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!isExternalStorageAvailableForRW()) {
                            Toast.makeText(getApplicationContext(), "Sorry U don't have sdcard mounted on your device", Toast.LENGTH_SHORT).show();
                        } else {
                            String s = arrayList.get(position);
                            fileContent = s;
                            if (!fileContent.equals("")) {
                                File file = new File(getExternalFilesDir(filePath), fileName);
                                FileOutputStream fileOutputStream = null;
                                try {
                                    fileOutputStream = new FileOutputStream(file);
                                    fileOutputStream.write(fileContent.getBytes());
                                } catch (FileNotFoundException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                Toast.makeText(QuotesFavActivity.this, "Quote Saved to SD Card", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(QuotesFavActivity.this, "Text Field can not be Empty", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                AlertDialog alert = a.create();
                alert.setTitle("Save or Share this Quote");
                alert.show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(QuotesFavActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Are You Sure?")
                        .setMessage("Do You Want to delete this Quote").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();
                        SharedPreferences sh = getApplicationContext().getSharedPreferences("saved", Context.MODE_PRIVATE);
                        HashSet<String> set = new HashSet<>(arrayList);
                        sh.edit().putStringSet("notes", set).apply();
                        Toast.makeText(getApplicationContext(), "Deleted this Quote ,Now please Save again ...", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", null).show();
                return true;
            }
        });
//
        try {
            loadData();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
//
private void loadData() {
    SharedPreferences sh = getSharedPreferences("saved", MODE_PRIVATE);
    Set<String> set = sh.getStringSet("notes", new HashSet<String>());
    for (String i : set) {
        arrayList.add(i);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
    }
}
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.fav_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fav:
                SharedPreferences sh=getApplicationContext().getSharedPreferences("saved",MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(arrayList);
                sh.edit().putStringSet("notes",set).apply();
                Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean isExternalStorageAvailableForRW() { String extState= Environment.getExternalStorageState();
        if (extState.equals(Environment.MEDIA_MOUNTED)){ return true; } return false; }
}