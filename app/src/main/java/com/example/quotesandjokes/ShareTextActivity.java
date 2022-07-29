package com.example.quotesandjokes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ShareTextActivity extends AppCompatActivity {
String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_text2);
        Intent intent=getIntent();
        s=intent.getStringExtra("text");
                           Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Good Wish : " + s);
                                sendIntent.setType("text/plain");
                                Intent shareIntent = Intent.createChooser(sendIntent, null);
                                startActivity(shareIntent);
                                Toast.makeText(getApplicationContext(), "Sharing Wish ...", Toast.LENGTH_SHORT).show();
    }
}