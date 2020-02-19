package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDataActivity extends AppCompatActivity {

    ListView listView;
    List<Model> list;
    Adapter adapter;
    Model model;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        listView = findViewById(R.id.list1);
        list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Model model = snap.getValue(Model.class);
                    list.add(model);
                }

                adapter = new Adapter(ViewDataActivity.this, list);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
