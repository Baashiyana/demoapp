package com.example.demoapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ReadWriteItem {
    public String id, itemname,category,price,unit;

    public ReadWriteItem(String id,String eitemnm, String ecat, String eprice, String espinnerGenres) {
        this.id=id;
        this.itemname=eitemnm;
        this.category=ecat;
        this.price=eprice;
        this.unit=espinnerGenres;
    }

    public ReadWriteItem() {
    }

    public String getId() {
        return id;
    }

    public String getItemname() {
        return itemname;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }
}
