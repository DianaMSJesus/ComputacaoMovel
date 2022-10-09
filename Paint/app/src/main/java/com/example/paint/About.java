package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView info = findViewById(R.id.group_Information);
        info.setText("Diana Jesus\nJosé Siopa\nRodrigo Albino");
    }
}