package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class updateitem extends AppCompatActivity {
    EditText editemnm;
    EditText edcat;
    EditText edprice;
    Spinner spinnerGenres;
    Button itemupdate;
    DatabaseReference addeditem;

    private String mitemid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateitem);
        editemnm = findViewById(R.id.editemnm);
        edcat = findViewById(R.id.edcat);
        edprice = findViewById(R.id.edprice);
        spinnerGenres = findViewById(R.id.spinnerGenres);
        itemupdate = findViewById(R.id.itemupdate);
        addeditem=FirebaseDatabase.getInstance().getReference().child("Items");
        mitemid=getIntent().getExtras().getString("id");
        editemnm.setText(getIntent().getExtras().getString("itemname"));
        edcat.setText(getIntent().getExtras().getString("itemcategory"));
        edprice.setText(getIntent().getExtras().getString("price"));
        String unit = getIntent().getExtras().getString("unit");
        spinnerGenres.setSelection(getIndex(spinnerGenres, unit));
        itemupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }

            private void update() {
                String eitemnm = editemnm.getText().toString();
                String ecat = edcat.getText().toString();
                String eprice = edprice.getText().toString();
                String espinnerGenres = spinnerGenres.getSelectedItem().toString();
                if (TextUtils.isEmpty(eitemnm)) {
                    Toast.makeText(updateitem.this, "Please enter item name", Toast.LENGTH_LONG).show();
                    editemnm.setError("Item name is required");
                    editemnm.requestFocus();
                } else if (TextUtils.isEmpty(ecat)) {
                    Toast.makeText(updateitem.this, "Please enter category", Toast.LENGTH_LONG).show();
                    edcat.setError("Fill the category");
                    edcat.requestFocus();
                } else if (TextUtils.isEmpty(eprice)) {
                    Toast.makeText(updateitem.this, "Please enter item price", Toast.LENGTH_LONG).show();
                    edprice.setError("Fill the category");
                    edprice.requestFocus();
                } else if (TextUtils.isEmpty(espinnerGenres)) {
                    Toast.makeText(updateitem.this, "Please enter category", Toast.LENGTH_LONG).show();
                    spinnerGenres.requestFocus();
                }
                else
                {
                    Map<String,Object> map=new HashMap<>();
                    map.put("category",edcat.getText().toString());
                    map.put("itemname",editemnm.getText().toString());
                    map.put("price",edprice.getText().toString());
                    map.put("unit",spinnerGenres.getSelectedItem().toString());
                    FirebaseDatabase.getInstance().getReference().child("Items");
                }



            }
        });

    }
    private int getIndex(Spinner spinnerGenres, String unit) {
        for (int i = 0; i < spinnerGenres.getCount(); i++) {
            if (spinnerGenres.getItemAtPosition(i).toString().equalsIgnoreCase(unit)) {
                return i;
            }

        }
        return 0;
    }
}