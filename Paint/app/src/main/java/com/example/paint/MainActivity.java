package com.example.paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static Integer teste = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(teste.equals(1)){
            Intent intent = getIntent();
            String color = intent.getStringExtra(Settings.EXTRA_BACKGROUND);
            if(color==null){
                color = "#FF000000";
            }
            ConstraintLayout main = findViewById(R.id.main);
            main.setBackgroundColor(Color.parseColor(color));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;

            case R.id.settings:
                teste = 1;
                startActivity(new Intent(this, Settings.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}