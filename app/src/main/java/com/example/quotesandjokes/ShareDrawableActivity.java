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

public class ShareDrawableActivity extends AppCompatActivity {
    ImageView imageView;  String s;
    TextView textView; String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_drawable2);
        imageView=findViewById(R.id.imageView6);
        textView=findViewById(R.id.textView6);
        Intent intent=getIntent();
        s=intent.getStringExtra("text");
        flag=intent.getStringExtra("flag");
        switch (flag){
            case "fr":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.friend)); break;
            case "fa":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.father));break;
            case "br":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.brother));break;
            case "mo":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.mother));break;
            case "si":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.sister));break;
            case "va":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.valentine));break;
            case "ra":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.raksha));break;
            case "na":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.navratri));break;
            case "ma":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.makar));break;
            case "ka":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.karwa));break;
            case "ga":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ganesh));break;
            case "bh":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.bhai));break;
            case "bi":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.birth));break;
            case "he":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.health));break;
            case "jo":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.job));break;
            case "we":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.marriage));break;
            case "ei":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.eid));break;
            case "di":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.diwali));break;
            case "ne":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));break;
            case "ho":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.holi));break;
            case "ch":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.christmas));break;
            case "pr":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.job1));break;
            case "fi":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.job2));break;
            case "job":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.job3));break;
            case "sick":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.sick));break;
            case "old":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.healthy));break;
            case "baby":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.baby));break;
        }
        textView.setText(s);
    }
    public void shareDrawable(View view) {
        image();
    }

    private void image() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap =drawable.getBitmap();
        File file =new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
        Intent intent;
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TEXT,s);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        startActivity(Intent.createChooser(intent,"share image"));
    }
}