package com.example.quotesandjokes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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

public class firstJobFragment extends Fragment { String fileName="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    public firstJobFragment() {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);   fileName = "wishes.txt";
        arrayList=new ArrayList<>();
 arrayList.add("Congratulations on your new dream job!");
 arrayList.add("Welcome to the world of work");
 arrayList.add(" I don’t need to wish you ‘Good Luck’ because I know luck is always with you. After all, you’re a hard worker and a passionate dreamer. Well done and always make me proud like this.");
 arrayList.add("Wishing you good luck with your first job. May this job takes you to the way of success in the journey of success!");
 arrayList.add("Congratulations! I wish you all the best as you venture into this new job of yours. It’s a perfect match for you. Enjoy your new career!");
 arrayList.add("May this first job start a journey of success in your life that never ends. Congratulations my friend. I hope nothing but the best for you.");
 arrayList.add("Congratulations on getting appointed! May this job build a firm path for your growth and development. Good luck in joining your new office soon!");
        arrayList.add("Congratulations on your new job");arrayList.add("My best wishes to you for your career ahead");
        arrayList.add("Hope you grow and flourish in your professional life ");arrayList.add("May god bless you with a good career in your future");
        arrayList.add("Congratulations on your 1st job.May your future rise and shine like this day.");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_first_job, container, false);
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
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="fi";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getContext(),ShareActivity.class);
                                intent.putExtra("img",s);intent.putExtra("flag",flag);
                                startActivity(intent);
                            }
                        }).setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = arrayList.get(position); fileContent = s;
                        FileOutputStream fileOutputStream;
                        try { fileOutputStream=getContext().openFileOutput(fileName, Context.MODE_PRIVATE); fileOutputStream.write(fileContent.getBytes());
                            fileOutputStream.close();
                            Toast.makeText(getContext(),fileName + " Data Saved",Toast.LENGTH_LONG).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace(); }

//                        if (!isExternalStorageAvailableForRW()) {
//                            Toast.makeText(getContext(), "Sorry U don't have sdcard mounted on your device", Toast.LENGTH_SHORT).show();
//                        } else {
//                            String s = arrayList.get(position);
//                            fileContent = s;
//                            if (!fileContent.equals("")) {
//                                File file = new File(getContext().getExternalFilesDir(filePath), fileName);
//                                FileOutputStream fileOutputStream = null;
//                                try {
//                                    fileOutputStream = new FileOutputStream(file);
//                                    fileOutputStream.write(fileContent.getBytes());
//                                } catch (FileNotFoundException fileNotFoundException) {
//                                    fileNotFoundException.printStackTrace();
//                                } catch (IOException ioException) {
//                                    ioException.printStackTrace();
//                                }
//                                Toast.makeText(getContext(), "Wish Saved to SD Card", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getContext(), "Text Field can not be Empty", Toast.LENGTH_SHORT).show();
//                            }
//                        }

                    }
                });
                AlertDialog alert = a.create();
                alert.setTitle("Save/Share this Wish");
                alert.show();
            }
        });
        return view;
    }
}