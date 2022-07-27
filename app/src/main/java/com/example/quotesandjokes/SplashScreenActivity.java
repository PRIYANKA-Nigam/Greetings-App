package com.example.quotesandjokes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    TextView textView; Animation animation;
    ImageView i1,i2,i4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView=findViewById(R.id.th);
        i4=findViewById(R.id.imageView2);
        i1=findViewById(R.id.imageView3);
        i2=findViewById(R.id.imageView4);


        animation   = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        textView.startAnimation(animation);
        i1.startAnimation(animation);i2.startAnimation(animation);i4.startAnimation(animation);
        final Intent intent=new Intent(this,MainActivity.class);
        Thread timer=new Thread(){
            public void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }


}