package com.example.quotesandjokes.Wish.ReligiousMemorableEvents.MemorableEvents;

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
import com.example.quotesandjokes.Favourite.MemorableEventFavActivity;
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

public class FriendDayActivity extends AppCompatActivity {
    String fileName="",filePath="",fileContent ="";
    ListView listView; ArrayList<String> arrayList;
    WishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_day);
        fileName = "wishes.txt";
        filePath = "MyWishes";
        arrayList=new ArrayList<>();
        listView=(ListView)findViewById(R.id.ll);
arrayList.add("You are like the candle that brightens up the room. Many happy days are wished for you this Friendship Day.");
arrayList.add("For the happiness you bring into other's lives, I wish more happiness for you on this Friendship Day.");
arrayList.add("A message to tell you I am so glad that we are friends, Happy Friendship Day");
arrayList.add("You've been more than a friend. You've been my confidante, my shoulder to cry on, and someone to laugh with. Thanks for just being my friend.\n");
arrayList.add("A friend like you is rare. The person who no matter what is always there. I so appreciate you. Happy Friendship Day.");
arrayList.add("The most invaluable thing I have is your friendship. I will forever cherish it. Happy Friendship Day Bestie.");
arrayList.add("You are an angel sent from heaven to make my life beautiful, my heart happy and take care of my smile…. Happy Friendship Day to you my dear.”");
arrayList.add("“If there is someone on whom I can depend without a single thought then it is you my friend. Wishing you Happy Friendship Day.”");
arrayList.add("Not many things in life make me happy. But you are an exception. Happy friendship day, my friend!");
arrayList.add("I am one of those lucky individuals who have gotten to experience the meaning of true friendship. Happy friendship day, buddy!");
        adapter=new WishAdapter(getApplicationContext(),arrayList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder a = new AlertDialog.Builder(FriendDayActivity.this);
                a.setMessage("do you want to Save or Share this Wish ?....").setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { String flag="fr";
                                String s = arrayList.get(position);
                                Intent intent=new Intent(getApplicationContext(), ShareActivity.class);
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
                Intent intent=new Intent(getApplicationContext(), MemorableEventFavActivity.class);
                intent.putStringArrayListExtra("quote", arrayList);
                Toast.makeText(getApplicationContext(),"Displaying in Favourite List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent); break;
            case R.id.dark:
                startActivity(new Intent(getApplicationContext(), DarkModeActivity.class));break;
        }
        return super.onOptionsItemSelected(item);
    }
}