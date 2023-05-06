package com.example.quotesandjokes.Wish;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quotesandjokes.R;

import java.util.ArrayList;
import java.util.HashSet;

public class WishAdapter extends ArrayAdapter<String> {
    public WishAdapter(@NonNull Context context, ArrayList<String> resource) {
        super(context, 0,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String s=getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wishes_row,parent,false);
        }
        TextView tv1 = convertView.findViewById(R.id.textView2);
        ImageView imageView=convertView.findViewById(R.id.img);
        tv1.setText(s);
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              SharedPreferences sh=getContext().getSharedPreferences("wish",Context.MODE_PRIVATE);
              HashSet<String> set=new HashSet<>();
              set.add(s);
              sh.edit().putStringSet("wished",set).commit();
              Toast.makeText(getContext(),"Wish Moved to Favourites",Toast.LENGTH_LONG).show();
          }
      });
        return convertView;

    }
}
