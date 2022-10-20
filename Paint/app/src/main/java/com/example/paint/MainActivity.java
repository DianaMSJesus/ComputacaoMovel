package com.example.paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentResultListener;

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

    CanvaFragment canvaFragment;
    PalletFragment palletFragment;
    Integer red = 255, green = 255, blue = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        red = intent.getIntExtra("FINAL_RED",255);
        green = intent.getIntExtra("FINAL_GREEN",255);
        blue = intent.getIntExtra("FINAL_BLUE",255);

        if(canvaFragment == null) canvaFragment = new CanvaFragment();
        if(palletFragment == null) palletFragment = new PalletFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragmentCanvas,canvaFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragmentPallete,palletFragment).commit();

        getSupportFragmentManager().setFragmentResultListener("bundle", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                red = result.getInt("FG_RED",255);
                green = result.getInt("FG_GREEN",255);
                blue = result.getInt("FG_BLUE",255);

                canvaFragment.paintCanvas.setDrawColor(red,green,blue);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //canvaFragment.requireView().setBackgroundColor(Color.argb(255,red,green,blue));
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
                Intent intentSettings = new Intent(this,Settings.class);

                intentSettings.putExtra("FINAL_RED",red);
                intentSettings.putExtra("FINAL_GREEN",green);
                intentSettings.putExtra("FINAL_BLUE",blue);

                startActivity(intentSettings);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("RED",red);
        outState.putInt("GREEN",green);
        outState.putInt("BLUE",blue);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        red = savedInstanceState.getInt("RED",255);
        green = savedInstanceState.getInt("GREEN",255);
        blue = savedInstanceState.getInt("BLUE",255);

        //canvaFragment.requireView().setBackgroundColor(Color.argb(255,red,green,blue));
    }
}