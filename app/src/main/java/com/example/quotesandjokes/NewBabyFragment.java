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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NewBabyFragment extends Fragment { String fileName="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    public NewBabyFragment() {
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
        fileName = "wishes.txt";
        setHasOptionsMenu(true);
        arrayList=new ArrayList<>();
        arrayList.add("“Congratulations! So thrilled to hear the news.”");
        arrayList.add("“May your baby be blessed with good health, love and laughter. Congratulations!”");
        arrayList.add(" ““Congratulations! You are a mommy now!” Now is the time to enjoy your baby’s little feet and baby smell. So happy for you!”");
        arrayList.add("“Congratulations on becoming parents for the first time!” “Parenthood is a life-changing phase. May you cherish each and every moment of it with your baby.”");
        arrayList.add("“Congratulations on becoming new parents! We are always here for babysitting.”");
        arrayList.add(" “There is nothing sweeter than the smell of your baby’s breath, happy coos, and gentle kisses. Congratulations on your happy bundle!”");
        arrayList.add("“Sending loads of love to the little baby and wishing you all the happiness.”");
        arrayList.add(" “Congratulations to the proud mamma and papa!”");
        arrayList.add(" “Congratulations on the new addition to your family! Wishing the three of you all the best.”");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_new_baby, container, false);
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
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="baby";
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