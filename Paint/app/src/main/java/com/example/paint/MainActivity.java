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
    Integer red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        red = intent.getIntExtra("FINAL_RED",0);
        green = intent.getIntExtra("FINAL_GREEN",0);
        blue = intent.getIntExtra("FINAL_BLUE",0);

        ConstraintLayout main = findViewById(R.id.main);
        main.setBackgroundColor(Color.argb(255,red,green,blue));
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
                Intent intentSettings = new Intent(this,Settings.class);

                intentSettings.putExtra("FINAL_RED",red);
                intentSettings.putExtra("FINAL_GREEN",green);
                intentSettings.putExtra("FINAL_BLUE",blue);

                startActivity(intentSettings);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}