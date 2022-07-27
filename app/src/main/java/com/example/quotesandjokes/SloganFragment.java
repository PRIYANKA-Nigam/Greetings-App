package com.example.quotesandjokes;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class SloganFragment extends Fragment {
ListView listView; ArrayList<Modal> arrayList;
NewAdapter adapter;
    public SloganFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList=new ArrayList<>();
        arrayList.add(new Modal("Satyameva Jayate","Pandit Madan Mohan Malviya"));
        arrayList.add(new Modal("Feeedom is my birthright","Bal Gangadhar Tilak"));
        arrayList.add(new Modal("Vande Mataram","Bankim Chandra Chattopadhyay"));
        arrayList.add(new Modal("Quit India","Mahatma Gandhi"));
        arrayList.add(new Modal("Inquilab Zindabad","Bhagat Singh"));
        arrayList.add(new Modal("Jai Hind","Shubhas Chandra Bose"));
        arrayList.add(new Modal("Give me blood and I shall give you freedom","Shubhas Chandra Bose"));
        arrayList.add(new Modal("Jai Jawan ,Jai Kisan","Lal Bahadur Shastri"));
        arrayList.add(new Modal("Garibi Hatao","Indra Gandhi"));
        arrayList.add(new Modal("Swaraj is my birth right and i shall have it","Bal Gangadhar Tilak"));
        arrayList.add(new Modal(" Tryst with Destiny","Jawaharlal Nehru"));
        arrayList.add(new Modal("Do or die","Mahatma Gandhi"));
        arrayList.add(new Modal("Aaram haram hai","Jawaharlal Nehru"));
        arrayList.add(new Modal("Back to the Vedas","Swami Dayanand Saraswati"));
        arrayList.add(new Modal("One religion, one caste and one God for mankind","Narayan Guru"));
        arrayList.add(new Modal("Sarfaroshi ki Tamanna ab hamare dil mein hain","Ramprasad Bismil Azimabadi"));
        arrayList.add(new Modal("Sare jahan se achcha Hindustan hamara","Mohammad Iqbal"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view= (FrameLayout) inflater.inflate(R.layout.fragment_slogan, container, false);
        listView=view.findViewById(R.id.ll);
        adapter=new NewAdapter(getContext(),arrayList);
        listView.setAdapter(adapter);
        return view;
    }
}