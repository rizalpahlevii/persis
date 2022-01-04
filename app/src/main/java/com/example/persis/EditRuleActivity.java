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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditRuleActivity extends AppCompatActivity {

    EditText editTextTitle;
    Button btnSave, btnDelete;
    DatabaseReference databaseReference;
    String title,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Edit Peraturan");



        editTextTitle = (EditText) findViewById(R.id.title);
        getInputExtra();



        databaseReference = FirebaseDatabase.getInstance().getReference("rules");
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editTextTitle = (EditText)findViewById(R.id.title);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = getIntent().getStringExtra("id");
                    databaseReference.child(id).removeValue();
                    Toast.makeText(getApplicationContext(), "Berhasil menghapus peraturan", Toast.LENGTH_SHORT).show();
                    editTextTitle.setText("");
                    Intent intent = new Intent(EditRuleActivity.this,RuleActivity.class);
                    EditRuleActivity.this.startActivity(intent);
                }catch(DatabaseException e){
                    Toast.makeText(getApplicationContext(),"Error "+e,Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                if(title == ""){
                    Toast.makeText(getApplicationContext(),"Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        String id = getIntent().getStringExtra("id");
                        databaseReference.child(id).setValue(new Rule(id,editTextTitle.getText().toString()));
                        Toast.makeText(getApplicationContext(), "Berhasil menyimpan peraturan", Toast.LENGTH_SHORT).show();
                        editTextTitle.setText("");
                        Intent intent = new Intent(EditRuleActivity.this,RuleActivity.class);
                        EditRuleActivity.this.startActivity(intent);
                    }catch (DatabaseException e){
                        Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getInputExtra()
    {
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        editTextTitle.setText(title);
    }
}