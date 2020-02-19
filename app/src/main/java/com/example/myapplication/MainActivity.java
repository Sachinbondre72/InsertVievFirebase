package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name, email, contact;
    Button insert;
    DatabaseReference databaseReference;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        insert = findViewById(R.id.buttonInsert);
        progressBar = findViewById(R.id.progressBar);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NAME = name.getText().toString();
                String EMAIL = email.getText().toString();
                String CONTACT = contact.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                insert.setVisibility(View.GONE);
                String id = databaseReference.push().getKey();

                Model model = new Model(id, NAME, EMAIL, CONTACT);

                databaseReference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            insert.setVisibility(View.VISIBLE);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                insert.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

    }

    public void openViewDataActivity(View view) {
        startActivity(new Intent(MainActivity.this, ViewDataActivity.class));
    }
}
