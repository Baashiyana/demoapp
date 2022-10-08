package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class additem extends AppCompatActivity {
    EditText editemnm;
    EditText edcat;
    EditText edprice;
    Spinner spinnerGenres;
    Button itemadd;
    FirebaseAuth mAuth;
    DatabaseReference Databaseitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        Databaseitem = FirebaseDatabase.getInstance().getReference("Items");
        editemnm = findViewById(R.id.editemnm);
        edcat = findViewById(R.id.edcat);
        edprice = findViewById(R.id.edprice);
        spinnerGenres = findViewById(R.id.spinnerGenres);
        itemadd = findViewById(R.id.itemadd);
        mAuth = FirebaseAuth.getInstance();
        itemadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertitem();
            }
        });
    }
    private void insertitem() {
        Button itemadd;
        String eitemnm = editemnm.getText().toString();
        String ecat = edcat.getText().toString();
        String eprice = edprice.getText().toString();
        String espinnerGenres = spinnerGenres.getSelectedItem().toString();
        if (TextUtils.isEmpty(eitemnm)) {
            Toast.makeText(additem.this, "Please enter item name", Toast.LENGTH_LONG).show();
            editemnm.setError("Item name is required");
            editemnm.requestFocus();
        } else if (TextUtils.isEmpty(ecat)) {
            Toast.makeText(additem.this, "Please enter category", Toast.LENGTH_LONG).show();
            edcat.setError("Fill the category");
            edcat.requestFocus();
        } else if (TextUtils.isEmpty(eprice)) {
            Toast.makeText(additem.this, "Please enter item price", Toast.LENGTH_LONG).show();
            edprice.setError("Fill the category");
            edprice.requestFocus();
        } else if (TextUtils.isEmpty(espinnerGenres)) {
            Toast.makeText(additem.this, "Please enter category", Toast.LENGTH_LONG).show();
            spinnerGenres.requestFocus();
        } else {

            String id = Databaseitem.push().getKey();
            ReadWriteItem Item = new ReadWriteItem(id,eitemnm, ecat, eprice, espinnerGenres);
            Databaseitem.child(id).setValue(Item).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(additem.this, "item added Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(additem.this, dashboard.class));
                    } else {
                        Toast.makeText(additem.this, "item added Failed.please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}

