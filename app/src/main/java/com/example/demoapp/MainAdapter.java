package com.example.demoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
    addedItemList mainActivity;
    String[] title;
    String[] price;
    Animation animation;

    public MainAdapter(addedItemList mainActivity, String[] title, String[] price) {
        this.mainActivity = mainActivity;
        this.title = title;
        this.price = price;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mainActivity).inflate(R.layout.order_item,viewGroup,false);
        animation= AnimationUtils.loadAnimation(mainActivity,R.anim.animation1);
        TextView textView;
        TextView Price;
        RelativeLayout llbg;
        llbg=view.findViewById(R.id.ll_bg);
        textView=view.findViewById(R.id.tvitem);
        Price=view.findViewById(R.id.tvprice);
        textView.setText(title[i]);
        Price.setText(price[i]);
        textView.setAnimation(animation);
        Price.setAnimation(animation);
        return view;
    }
}
