package com.example.quotesandjokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NewAdapter extends ArrayAdapter<Modal> {
    public NewAdapter(@NonNull Context context, ArrayList<Modal> modals) {
        super(context, 0,modals);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Modal modal =getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.table_row,parent,false);
        }
        TextView tv1 = convertView.findViewById(R.id.textView2);
        TextView tv2 = convertView.findViewById(R.id.textView3);
        tv1.setText(modal.getName());
        tv2.setText(modal.getName2());
        return convertView;
    }
}
