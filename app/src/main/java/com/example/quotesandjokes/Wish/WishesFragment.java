package com.example.quotesandjokes.Wish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.quotesandjokes.R;
import com.example.quotesandjokes.Wish.NationalSpecialEvents.InternationalNationalEvents.InternationalActivity;
import com.example.quotesandjokes.Wish.NationalSpecialEvents.SpecialEvents.SpecialEventsActivity;
import com.example.quotesandjokes.Wish.ReligiousMemorableEvents.MemorableEvents.MemorableDayActivity;
import com.example.quotesandjokes.Wish.ReligiousMemorableEvents.ReligiousEvents.ReligiousDayActivity;

public class WishesFragment extends Fragment {
    private ImageView i1,i2,i3,i4;
    public WishesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_wishes, container, false);
       i1=view.findViewById(R.id.i1);i1.setTooltipText("Wishes on the religious Events ");
        i2=view.findViewById(R.id.i2); i2.setTooltipText("Wishes for your mates and dear one's");
        i3=view.findViewById(R.id.i3); i3.setTooltipText("Wishes on periodic Events");
        i4=view.findViewById(R.id.i4); i4.setTooltipText("Wishes on Special moments ,career,Health");
        TextDrawable roundRect = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRoundRect("R", getResources().getColor(R.color.purple_200), 10); // radius in px
       i1.setImageDrawable(roundRect);
       i1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getContext(), ReligiousDayActivity.class));
           }
       });
        TextDrawable td = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRoundRect("M", getResources().getColor(R.color.purple_200), 10); // radius in px
        i2.setImageDrawable(td);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MemorableDayActivity.class));
            }
        });
        TextDrawable td2 = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRoundRect("N", getResources().getColor(R.color.purple_200), 10); // radius in px
        i3.setImageDrawable(td2);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InternationalActivity.class));
            }
        });
        TextDrawable td4 = TextDrawable.builder().beginConfig()
                .width(130)  // width in px
                .height(130) // height in px
                .endConfig()
                .buildRoundRect("S", getResources().getColor(R.color.purple_200), 10); // radius in px
        i4.setImageDrawable(td4);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SpecialEventsActivity.class));
            }
        });
        return view;
    }
}