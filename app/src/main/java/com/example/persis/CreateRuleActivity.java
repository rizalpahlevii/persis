package com.example.persis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.persis.models.Rule;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateRuleActivity extends AppCompatActivity {

    Button btnSave;
    EditText editTextTitle;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Tambah Peraturan");


        databaseReference = FirebaseDatabase.getInstance().getReference("rules");

        btnSave = (Button)findViewById(R.id.btnSave);
        editTextTitle = (EditText)findViewById(R.id.title);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                if(title == ""){
                    Toast.makeText(getApplicationContext(),"Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        String id = databaseReference.push().getKey();
                        Rule rule = new Rule(id,title);
                        databaseReference.child(id).setValue(rule);
                        editTextTitle.setText("");
                        Toast.makeText(getApplicationContext(), "Berhasil menyimpan peraturan baru", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateRuleActivity.this,RuleActivity.class);
                        CreateRuleActivity.this.startActivity(intent);
                    }catch (DatabaseException e){
                        Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void save(String title){

    }
}