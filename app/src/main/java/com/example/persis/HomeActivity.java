package com.example.persis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnRule,btnViolation, btnClass, btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));

        btnClass = (Button) findViewById(R.id.btnClass);
        btnViolation = (Button) findViewById(R.id.btnViolation);
        btnRule = (Button) findViewById(R.id.btnRule);
        btnProfile = (Button) findViewById(R.id.btnProfile);

        btnRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RuleActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });

        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ClassActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });

        btnViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ViolationActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });
    }
}