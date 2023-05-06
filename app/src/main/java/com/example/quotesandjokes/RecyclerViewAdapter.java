//package com.example.quotesandjokes;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.quotesandjokes.Slogan.Modal;
//
//import java.util.List;
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
//Context context; int size;
//List<Modal> list;
//
//    public RecyclerViewAdapter(Context context, List<Modal> list,int size) {
//        this.context = context;
//        this.list = list;
//        this.size = size;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_row,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//     holder.textView.setText( list.get(position).getName());
//     holder.imageView.setImageResource(R.drawable.fav_black);
//     holder.imageView.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//           context.getSharedPreferences("fav", Context.MODE_PRIVATE)
//                     .edit().putString("names",list.get(position).getName().toString()).commit();
//           holder.imageView.setImageResource(R.drawable.fav_red);
//             Toast.makeText(context,"Saved ........",Toast.LENGTH_LONG).show();
//         }
//     });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder{
//     TextView textView;
//     ImageView imageView; CardView cardView;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textView=itemView.findViewById(R.id.tt);
//            imageView=itemView.findViewById(R.id.imageView);
//            cardView=itemView.findViewById(R.id.cd);
//        }
//    }
//}
