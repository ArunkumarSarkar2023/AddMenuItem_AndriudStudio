package com.example.firestoredatabase;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
 EditText nameTv,city;
 Button submit;
 FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTv=findViewById(R.id.nameTv);
        city=findViewById(R.id.cityTv);
        submit=findViewById(R.id.submit);

        firestore=FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertdata();
            }
        });
    }

    private void insertdata() {
        Map<String,String> items =new HashMap<>();
    items.put("Name",nameTv.getText().toString().trim());
    items.put("City",city.getText().toString().trim());

    firestore.collection("Personal").add(items)
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    nameTv.setText("");
                    city.setText("");

                    Toast.makeText(MainActivity.this, "Saved Data In FireStore", Toast.LENGTH_SHORT).show();
                }
            });
    }
}