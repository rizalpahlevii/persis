package com.example.persis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.persis.models.Rule;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private String[] rules = {
            "Siswa sekolah 5 hari kerja Senin sampai dengan Jumat",
            "Siswa wajib hadir pukul 06.30 WIB di sekolah pulang pukul 14.20 kecuali jumat pukul 15.00",
            "Berpakaian bersih, rapih, sesuai dengan ketentuan",
            "Memakai sepatu warna hitam bertali putih dan kaos kaki warna putih",
            "Bagi siswa berjilbab memakai kerudung warna putih",
            "Dilarang membawa kendaraan bermotor",
            "Dilarang masuk genk/organisasi selain OSIS",
            "Dilarang melakukan BULLYING fisik maupun nonfisik"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.danger)));
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        db.deleteAll();
        db.create("X");
        db.create("XI");
        db.create("XII");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        },2500);
    }



}