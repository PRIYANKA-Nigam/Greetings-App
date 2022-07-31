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

import com.amulyakhare.textdrawable.TextDrawable;

import java.io.File;
import java.io.FileOutputStream;

public class ShareActivity extends AppCompatActivity {
    String s="";String flag="";   private ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share2);
        i1=findViewById(R.id.i1);i1.setTooltipText("Share Wish as Normal Text");
        i2=findViewById(R.id.i2); i2.setTooltipText("Share Wish embedded in image");
        i3=findViewById(R.id.i3); i3.setTooltipText("Share Wish in encoded format as QR ");
        Intent intent=getIntent();
        s=intent.getStringExtra("img");
        flag=intent.getStringExtra("flag");
        TextDrawable roundRect = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRound("T", getResources().getColor(R.color.purple_200)); // radius in px
        i1.setImageDrawable(roundRect);
        TextDrawable roundRect2 = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRound("D", getResources().getColor(R.color.purple_200)); // radius in px
        i2.setImageDrawable(roundRect2);
        TextDrawable roundRect3 = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRound("Q", getResources().getColor(R.color.purple_200)); // radius in px
        i3.setImageDrawable(roundRect3);
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

    public void shareQR(View view) {
        Intent intent=new Intent(getApplicationContext(),ShareQRActivity.class);
        intent.putExtra("text",s);
        startActivity(intent);
    }
}