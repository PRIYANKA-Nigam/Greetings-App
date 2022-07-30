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

public class BhaiActivity extends AppCompatActivity { String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhai);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        arrayList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.ll);
arrayList.add("Happy Bhai Dooj");
arrayList.add("Your presence always made me feel safe…you have been my North Star guiding me the right way…Love You Brother!!! Happy Bhai Dooj!");
arrayList.add("Many best wishes to you on the auspicious occasion of Bhai Dooj, May there be happiness, peace, and prosperity in your life always.");
arrayList.add("It is my heart’s wish that your life is full of happiness, success kisses your feet and our bond is always full of love. Happy Bhai Dooj.");
arrayList.add("Brother, we make the best team together…Cheers to all the beautiful moments of childhood!!! Happy Bhaiya Dooj!!!");
arrayList.add("May our bond become strong and unbreakable as steel. Sending you all my love and blessings on Bhai Dooj. Love you brother!");
arrayList.add("I always pray for your long life and good health. Cheer up and stay a blessed lifetime, Happy Bhai Dooj!");
arrayList.add("Lots of love and blessings for you on the occasion of Bhai Dooj.");
        adapter=new WishAdapter(getApplicationContext(),arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder a = new AlertDialog.Builder(BhaiActivity.this);
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="bh";
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