package com.example.persis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.persis.models.Rule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RuleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RuleAdapter ruleAdapter;
    ArrayList<Rule> rules;
//    Button btnAdd;

    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Peraturan");

        recyclerView = findViewById(R.id.rule_list);
        databaseReference = FirebaseDatabase.getInstance().getReference("rules");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rules =  new ArrayList<>();
        ruleAdapter = new RuleAdapter(this,rules);
        recyclerView.setAdapter(ruleAdapter);

//        btnAdd = (Button) findViewById(R.id.btnAdd);

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RuleActivity.this,CreateRuleActivity.class);
//                RuleActivity.this.startActivity(intent);;
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rules.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Rule rule = dataSnapshot.getValue(Rule.class);
                    rules.add(rule);
                }
                ruleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}