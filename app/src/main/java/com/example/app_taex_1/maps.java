package com.example.app_taex_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class maps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapsFragment mapFragment = new mapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activityyyyyy, mapFragment).commit();

    }
}
