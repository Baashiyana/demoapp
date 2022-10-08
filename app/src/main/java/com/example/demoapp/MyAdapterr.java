package com.example.demoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterr extends RecyclerView.Adapter<MyAdapterr.MyViewHolder> {
    Context context;
    ArrayList<ReadWriteItem> list;

    public MyAdapterr(Context context, ArrayList<ReadWriteItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_iteminvoice,parent,false);
        return new MyAdapterr.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapterr.MyViewHolder holder, int position) {
        ReadWriteItem readitem = list.get(position);
        holder.textView.setText(readitem.getItemname());
        holder.Price.setText(readitem.getPrice());
        holder.category.setText(readitem.getCategory());
        holder.unit.setText(readitem.getUnit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView Price;
        TextView category;
        TextView unit;


        public MyViewHolder(@NonNull View itemview) {
            super(itemview);
            textView = itemview.findViewById(R.id.tvitem);
            Price = itemview.findViewById(R.id.tvprice);
            category = itemview.findViewById(R.id.tvcat);
            unit = itemview.findViewById(R.id.tvunit);


        }
    }
}
