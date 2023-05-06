package com.example.quotesandjokes.Wish.NationalSpecialEvents.SpecialEvents.JobGreetings;

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

import com.example.quotesandjokes.R;
import com.example.quotesandjokes.Share.ShareActivity;
import com.example.quotesandjokes.Favourite.SpecialEventsFavActivity;
import com.example.quotesandjokes.Wish.WishAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JobPromotionFragment extends Fragment { String fileName="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    public JobPromotionFragment() {
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
        super.onCreate(savedInstanceState);   fileName = "wishes.txt";
        setHasOptionsMenu(true);
        arrayList=new ArrayList<>();
        arrayList.add("Well done for landing your amazing new role.");
        arrayList.add("Well done on your new role! I know you’re going to do great.");
        arrayList.add("You did it! Congrats on the new role, you’re going to do great.");
        arrayList.add("I’d wish you good luck in your new role, but you don’t need it – you have GOT this!");
        arrayList.add("“Congratulations on your well-deserved success.”");
        arrayList.add("“Warmest congratulations on your achievement.”");
        arrayList.add("“Congratulations and best wishes for your next adventure!”");
        arrayList.add("“Sincere congratulations on your hard-earned success.”");
        arrayList.add("My Heartfelt felicitations to you on your promotion");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_job_promotion, container, false);
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
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="pr";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getContext(), ShareActivity.class);
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
       return  view;
    }
}