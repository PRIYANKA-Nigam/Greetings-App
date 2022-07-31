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
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JokesFavActivity extends AppCompatActivity { String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList =new ArrayList<>();
    ArrayAdapter adapter;   ArrayList<String> userSelection =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_fav);  fileName="jokes.txt"; filePath="MyJokes";
        try{
            ArrayList<String> ss = getIntent().getStringArrayListExtra("joke");
            arrayList.addAll(ss);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        listView=findViewById(R.id.ll);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(modeListener);
        adapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder a=new AlertDialog.Builder(JokesFavActivity.this);
                a.setMessage("do you want to Save or share this Joke ?....").setCancelable(true)
                        .setPositiveButton("Share",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {  String s = arrayList.get(position);
                               sharedLogic(s);
                            }}).setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = arrayList.get(position);
                        fileContent =s;
                       savedLogic(fileContent);
                    }
                });
                AlertDialog alert= a.create();alert.setTitle("Save or Share this Item");alert.show();
            }
        });
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                new AlertDialog.Builder(JokesFavActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Are You Sure?")
//                        .setMessage("Do You Want to delete this Joke").setPositiveButton("Yes", new DialogInterface.OnClickListener() {@Override
//                public void onClick(DialogInterface dialog, int which) {
//                    arrayList.remove(position);adapter.notifyDataSetChanged();
//                    SharedPreferences sh=getApplicationContext().getSharedPreferences("safe", Context.MODE_PRIVATE);
//                    HashSet<String> set=new HashSet<>(arrayList);sh.edit().putStringSet("notes",set).apply();
//                    Toast.makeText(getApplicationContext(), "Deleted this Joke ,Now please Save again ...", Toast.LENGTH_SHORT).show();
//                }
//                }).setNegativeButton("No",null).show();
//                return true;
//            }
//        });
        try {
            loadData();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    public void removeItem(List<String> list){
        for (String i : list){
            arrayList.remove(i);
        }
    }
    public void saveItem(List<String> list){
        for (String s : list) {
            fileContent += s+"\n";
        }
        savedLogic(fileContent);

    }
    public void shareItem(List<String> list){
        String select="";
        for (String s : list) {
            //  String s = arrayList.get(i);
            select+=s+"\n";
        }
        sharedLogic(select);

    }
    public void sharedLogic(String s){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Interesting Quote : " + s);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        Toast.makeText(getApplicationContext(), "Sharing Quote ...", Toast.LENGTH_SHORT).show();
        finish();

    }
    public void savedLogic(String s){
        if (!isExternalStorageAvailableForRW()) {
            Toast.makeText(getApplicationContext(), "Sorry U don't have sdcard mounted on your device", Toast.LENGTH_SHORT).show();
        } else {
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
                Toast.makeText(JokesFavActivity.this, "Quote Saved to SD Card", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(JokesFavActivity.this, "Text Field can not be Empty", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void loadData() {
        SharedPreferences sh = getSharedPreferences("safe", MODE_PRIVATE);
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
        menuInflater.inflate(R.menu.save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                SharedPreferences sh=getApplicationContext().getSharedPreferences("safe",MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(arrayList);
                sh.edit().putStringSet("notes",set).apply();
                Toast.makeText(this,"Joke Saved Successfully ",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean isExternalStorageAvailableForRW() { String extState= Environment.getExternalStorageState();
        if (extState.equals(Environment.MEDIA_MOUNTED)){ return true; } return false; }

    AbsListView.MultiChoiceModeListener modeListener =new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
            if (userSelection.contains(arrayList.get(i))){
                userSelection.remove(arrayList.get(i));
            }else {
                userSelection.add(arrayList.get(i));
            }
            actionMode.setTitle(userSelection.size() +" items selected...");
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater menuInflater =actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.delete_save_share,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.delete:
                    removeItem(userSelection);
                    adapter.notifyDataSetChanged();
                    SharedPreferences sh = getApplicationContext().getSharedPreferences("safe", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet<>(arrayList);
                    sh.edit().putStringSet("notes", set).apply();
                    Toast.makeText(getApplicationContext(), "Deleted this Quote ...", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.share:
                    shareItem(userSelection);
                    return true;
                case R.id.save:
                    saveItem(userSelection);
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            userSelection.clear();
        }
    };
}