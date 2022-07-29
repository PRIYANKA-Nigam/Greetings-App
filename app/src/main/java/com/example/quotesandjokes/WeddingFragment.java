package com.example.quotesandjokes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class WeddingFragment extends Fragment { String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    public WeddingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        setHasOptionsMenu(true);
        arrayList=new ArrayList<>();
        arrayList.add("Happy Wedding Anniversary");arrayList.add("Many-many Happy returns of the day");
        arrayList.add("Wishing you an anniversary full of joy and Happiness");
        arrayList.add("Happy Conjugal Life to you both");arrayList.add("Congratulations on your 40th togetherness");
        arrayList.add("Wishing for your happy and conjugal life on your anniversary");
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
       View view= inflater.inflate(R.layout.fragment_wedding, container, false);
        listView=view.findViewById(R.id.ll);
        adapter=new WishAdapter(getContext(),arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder a = new AlertDialog.Builder(getContext());
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="we";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getContext(),ShareActivity.class);
                                intent.putExtra("img",s);intent.putExtra("flag",flag);
                                startActivity(intent);
                            }
                        }).setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!isExternalStorageAvailableForRW()) {
                            Toast.makeText(getContext(), "Sorry U don't have sdcard mounted on your device", Toast.LENGTH_SHORT).show();
                        } else {
                            String s = arrayList.get(position);
                            fileContent = s;
                            if (!fileContent.equals("")) {
                                File file = new File(getContext().getExternalFilesDir(filePath), fileName);
                                FileOutputStream fileOutputStream = null;
                                try {
                                    fileOutputStream = new FileOutputStream(file);
                                    fileOutputStream.write(fileContent.getBytes());
                                } catch (FileNotFoundException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                Toast.makeText(getContext(), "Wish Saved to SD Card", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Text Field can not be Empty", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                AlertDialog alert = a.create();
                alert.setTitle("Save/Share this Wish");
                alert.show();
            }
        });
        return view;
    }
    private boolean isExternalStorageAvailableForRW() { String extState= Environment.getExternalStorageState();
        if (extState.equals(Environment.MEDIA_MOUNTED)){ return true; } return false; }
}