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

public class KarwaActivity extends AppCompatActivity {String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karwa);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        arrayList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.ll);
      arrayList.add("As you celebrate the bond of marriage, here is wishing you a life of love and togetherness, today and always. Happy Karwa Chauth!");
      arrayList.add("May Goddess Parvati and Lord Shiva fill your life with love, peace happiness, good health and wealth. Happy Karwa Chauth!");
      arrayList.add("Warm wishes to all the women fasting on this auspicious day of Karwa Chauth! May all your come true!");
      arrayList.add("Karwa Chauth is not just an age-old tradition but confidence which a loving and doting wife has on her faith and love!");
      arrayList.add("May the blessing of God Shiva and Goddess Parvati bring love and happiness into your marriage. May your marriage last long and be filled with blessings!");
      arrayList.add("May the jingling of churis, fill your life with good luck. The twinkling of Payal announces your love for him. Happy Karwa Chauth.");
      arrayList.add("The one charm about marriage is that it makes a life of deception absolutely necessary for both parties");
      arrayList.add("The difference between a great marriage and an extraordinary marriage is life-long friendship and never-ending love. Happy Karva Chauth!");
      arrayList.add("May you live a long life and may we always love each other till eternity! Happy Karwa Chauth my beloved!");
        adapter=new WishAdapter(getApplicationContext(),arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder a = new AlertDialog.Builder(KarwaActivity.this);
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String s = arrayList.get(position);
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Good Wish : " + s);
                                sendIntent.setType("text/plain");
                                Intent shareIntent = Intent.createChooser(sendIntent, null);
                                startActivity(shareIntent);
                                Toast.makeText(getApplicationContext(), "Sharing Wish ...", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Save", new DialogInterface.OnClickListener() {
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
                                        Toast.makeText(getApplicationContext(), "Wish Saved to SD Card", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Text Field can not be Empty", Toast.LENGTH_SHORT).show();
                                    }
                                }

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
    private boolean isExternalStorageAvailableForRW() { String extState= Environment.getExternalStorageState();
        if (extState.equals(Environment.MEDIA_MOUNTED)){ return true; } return false; }
}