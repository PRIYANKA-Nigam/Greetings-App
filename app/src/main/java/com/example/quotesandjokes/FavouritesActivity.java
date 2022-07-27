package com.example.quotesandjokes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FavouritesActivity extends AppCompatActivity {
private ImageView i1,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        i1=findViewById(R.id.circle); i1.setTooltipText("See All your Favourites Quotes");
        i2=findViewById(R.id.circle2); i2.setTooltipText("See All your Favourites Jokes");
//        TextDrawable tileImg = TextDrawable.builder()
//                .beginConfig() .width(130) .height(130) .endConfig()  .buildRect("G", getResources().getColor(R.color.purple_200));
//        i1.setImageDrawable(tileImg);
//        TextDrawable roundRect = TextDrawable.builder().beginConfig()
//                .width(130)  // width in px
//                .height(130) // height in px
//                .endConfig()
//                .buildRoundRect("G", getResources().getColor(R.color.purple_200), 10); // radius in px
//       i2.setImageDrawable(roundRect);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QuotesFavActivity.class));
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),JokesFavActivity.class));
            }
        });
    }
}