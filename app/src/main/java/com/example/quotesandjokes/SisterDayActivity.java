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

public class SisterDayActivity extends AppCompatActivity {
    String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sister_day);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        arrayList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.ll);
arrayList.add("Happy Sister day! Although we are not sister by blood we have a bond and love that usually sisters have.");
arrayList.add("You are not just my elder sister as you always help me as a friend and guide me as a mentor. You are the best sister I would have, have a happy sister day!");
arrayList.add("You are a person in my life whom I look up to whenever I am in trouble or did something wrong as I know you will come to rescue me. Thank You a happy sister day");
arrayList.add("Sharing, caring, bearing, teasing and helping that’s all we sisters do, Happy Sister day!");
arrayList.add("Sisterhood is a blessing that only a few people can’t understand and trust me those are the most depressed people in this world. Thanks to God I have a sister like you. Happy Sister’s day!");
arrayList.add("In the honor of Sister day, I want to tell you that I feel so lucky to have you as my sister, Happy\n" +
        "Sister day!");
arrayList.add("Happy Sister Day to my sister and friend, because of you I never need a friend!");
arrayList.add("A sister is better than a friend, as you prove this to me. Happy sister’s day!");
arrayList.add("This message is for the best sister in my world…Happy Sister day! .Sisters are the best companions in life as they make your life easier. Happy sister’s day!");
        adapter=new WishAdapter(getApplicationContext(),arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder a = new AlertDialog.Builder(SisterDayActivity.this);
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="si";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getApplicationContext(),ShareActivity.class);
                                intent.putExtra("img",s);intent.putExtra("flag",flag);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String s = arrayList.get(position); fileContent = s;
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
                Intent intent=new Intent(getApplicationContext(), MemorableEventFavActivity.class);
                intent.putStringArrayListExtra("quote", arrayList);
                Toast.makeText(getApplicationContext(),"Displaying in Favourite List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent); break;
        }
        return super.onOptionsItemSelected(item);
    }

}