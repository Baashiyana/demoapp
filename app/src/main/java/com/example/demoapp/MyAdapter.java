package com.example.demoapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReadWriteItem> list;

    public MyAdapter(Context context, ArrayList<ReadWriteItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ReadWriteItem readitem= list.get(position);
            holder.textView.setText(readitem.getItemname());
            holder.Price.setText(readitem.getPrice());
            holder.category.setText(readitem.getCategory());
            holder.unit.setText(readitem.getUnit());
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),updateitem.class);
                    intent.putExtra("itemname",readitem.getItemname());
                    intent.putExtra("itemcategory",readitem.getCategory());
                    intent.putExtra("unit",readitem.getUnit());
                    intent.putExtra("price",readitem.getPrice());
                    intent.putExtra("id",readitem.getId());
                    view.getContext().startActivity(intent);
                }
            });
            holder.imgDelete.setOnClickListener(view -> {
                list.remove(position);
                notifyDataSetChanged();
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView Price;
        TextView category;
        TextView unit;
        ImageView imgEdit;
        ImageView imgDelete;
        public MyViewHolder(@NonNull View itemview){
            super(itemview);
            textView=itemview.findViewById(R.id.tvitem);
            Price=itemview.findViewById(R.id.tvprice);
            category=itemview.findViewById(R.id.tvcat);
            unit=itemview.findViewById(R.id.tvunit);
            imgEdit=itemview.findViewById(R.id.imgedit);
            imgDelete=itemview.findViewById(R.id.imgdelete);

        }
    }
}
