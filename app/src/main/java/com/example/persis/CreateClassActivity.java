package com.example.persis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.persis.models.Rule;
import com.google.firebase.database.DatabaseException;

public class CreateClassActivity extends AppCompatActivity {

    Button btnSave;
    EditText editTextTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Tambah Kelas");

        btnSave = (Button)findViewById(R.id.btnSave);
        editTextTitle = (EditText)findViewById(R.id.title);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getApplicationContext(),"Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        DatabaseHelper db = new DatabaseHelper(CreateClassActivity.this);
                        long result = db.create(title);
                        if(result == -1){
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Berhasil menyimpan kelas baru", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateClassActivity.this,ClassActivity.class);
                            CreateClassActivity.this.startActivity(intent);
                        }
                    }catch (DatabaseException e){
                        Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}