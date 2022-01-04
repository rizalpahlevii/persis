package com.example.persis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.persis.models.Rule;
import com.google.firebase.database.DatabaseException;

public class EditClassActivity extends AppCompatActivity {

    Button btnSave,btnDelete;
    TextView  editTextTitle;
    String title,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        getSupportActionBar().setTitle("Edit kelas");

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editTextTitle = (EditText) findViewById(R.id.title);
        getInputExtra();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = getIntent().getStringExtra("id");
                    DatabaseHelper db = new DatabaseHelper(EditClassActivity.this);
                    db.delete(id);
                    Toast.makeText(getApplicationContext(), "Berhasil menghapus kelas", Toast.LENGTH_SHORT).show();
                    editTextTitle.setText("");
                    Intent intent = new Intent(EditClassActivity.this,ClassActivity.class);
                    EditClassActivity.this.startActivity(intent);
                }catch (DatabaseException e){
                    Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getApplicationContext(),"Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        String id = getIntent().getStringExtra("id");
                        DatabaseHelper db = new DatabaseHelper(EditClassActivity.this);
                        db.update(id,title);
                        Toast.makeText(getApplicationContext(), "Berhasil menyimpan kelas", Toast.LENGTH_SHORT).show();
                        editTextTitle.setText("");
                        Intent intent = new Intent(EditClassActivity.this,ClassActivity.class);
                        EditClassActivity.this.startActivity(intent);
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