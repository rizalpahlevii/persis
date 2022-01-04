package com.example.persis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class ClassActivity extends AppCompatActivity {


//    Button btnAdd;
    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<String> id,title;
    ClassAdapter classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Daftar Kelas");

        recyclerView = findViewById(R.id.recyclerView);
        db = new DatabaseHelper(ClassActivity.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        displayData();

        classAdapter = new ClassAdapter(ClassActivity.this,id,title);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ClassActivity.this));




//        btnAdd = (Button) findViewById(R.id.btnAdd);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ClassActivity.this,CreateClassActivity.class);
//                ClassActivity.this.startActivity(intent);
//            }
//        });


    }


    private void displayData(){
        Cursor cursor = db.get();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
            }
        }
    }
}