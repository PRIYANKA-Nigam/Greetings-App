package com.example.quotesandjokes.Wish.ReligiousMemorableEvents.ReligiousEvents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quotesandjokes.DarkModeActivity;
import com.example.quotesandjokes.R;
import com.example.quotesandjokes.Favourite.ReligiousEventsFavActivity;
import com.example.quotesandjokes.Share.ShareActivity;
import com.example.quotesandjokes.Wish.WishAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MakarActivity extends AppCompatActivity { String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makar);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        arrayList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.ll);
arrayList.add("May the Sun God bring happiness and a lot of success and prosperity in your life. Wishing you a very happy Makar Sankranti.");
arrayList.add("May this harvest festival help you reap lot of success and happiness in your life. Happy Makar Sankranti!");
arrayList.add(" May the Sun God throw rays of happiness and prosperity in your life. Wishing you and your family very happy Makar Sankranti!");
arrayList.add("May you reach new points of happiness and cheerfulness like the beautiful kites in the sky. Happy Makar Sankranti!");
arrayList.add("May you rise high just like the colourful kites in the sky. Wishing you a very happy Makar Sankranti!");
arrayList.add("May God bring a lot of colours in your life on this day of new beginnings. Happy Makar Sankranti!");
arrayList.add("Wishing you a very happy Makar Sankranti, from our family to yours!");
arrayList.add("May the Sun God shower you and your family will a lot of blessings this Makar Sankranti!");
arrayList.add("Wishing you a very delightful Makar Sankranti with a sky full of kites and life full of success.");
arrayList.add(" On this Makar Sankranti may the Sun God bless you with love, prosperity and peace. Happy Makar Sankranti!");
        adapter=new WishAdapter(getApplicationContext(),arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder a = new AlertDialog.Builder(MakarActivity.this);
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="ma";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getApplicationContext(), ShareActivity.class);
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
        menuInflater.inflate(R.menu.dark_fav,menu);
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
            case R.id.dark:
                startActivity(new Intent(getApplicationContext(), DarkModeActivity.class));break;
        }
        return super.onOptionsItemSelected(item);
    }
}