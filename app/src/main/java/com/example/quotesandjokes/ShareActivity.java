package com.example.quotesandjokes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class ShareActivity extends AppCompatActivity {
    String s="";String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share2);
        Intent intent=getIntent();
        s=intent.getStringExtra("img");
        flag=intent.getStringExtra("flag");
    }
    public void shareDrawable(View view) {
        Intent intent=new Intent(getApplicationContext(),ShareDrawableActivity.class);
        intent.putExtra("text",s);intent.putExtra("flag",flag);
        startActivity(intent);
    }

    public void shareText(View view) {

        Intent intent=new Intent(getApplicationContext(),ShareTextActivity.class);
        intent.putExtra("text",s);
        startActivity(intent);
    }
}