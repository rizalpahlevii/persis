
package com.example.persis;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ViolationActivity extends AppCompatActivity {

    private String[] rules = {
            "Tidak Mengikuti Upacara Bendera",
            "Tidak Mengikuti Apel Pagi",
            "Tidak Melengkapi Attribut",
    };
    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Daftar Pelanggaran");
        ListView listView = findViewById(R.id.list);
        data = new ArrayList<>();
        getData();
        ArrayAdapter adapter  = new ArrayAdapter<>(this,R.layout.my_list_view,data);
        listView.setAdapter(adapter);
    }

    private void getData(){
        Collections.addAll(data,rules);
    }
}