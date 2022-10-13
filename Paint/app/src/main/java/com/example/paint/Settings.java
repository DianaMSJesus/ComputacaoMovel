package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    View v_Color;
    SeekBar sRed, sGreen, sBlue;
    Integer red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        //Conectar as variáveis aos componentes do display
        v_Color = findViewById(R.id.v_color);
        sRed = findViewById(R.id.s_Red);
        sGreen = findViewById(R.id.s_Green);
        sBlue = findViewById(R.id.s_Blue);

        red = intent.getIntExtra("FINAL_RED",0);
        green = intent.getIntExtra("FINAL_GREEN",0);
        blue = intent.getIntExtra("FINAL_BLUE",0);

        sRed.setProgress(red);
        sGreen.setProgress(green);
        sBlue.setProgress(blue);

        //Verificar alterações nas SeekBars
        sRed.setOnSeekBarChangeListener(this);
        sGreen.setOnSeekBarChangeListener(this);
        sBlue.setOnSeekBarChangeListener(this);

        v_Color.setBackgroundColor(Color.argb(255,red,green,blue));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        switch (seekBar.getId()){
            case R.id.s_Red:
                red = i;
                break;
            case R.id.s_Green:
                green = i;
                break;
            case R.id.s_Blue:
                blue = i;
                break;
        }

        v_Color.setBackgroundColor(Color.argb(255,red,green,blue));
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void changeColor(View view){
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("FINAL_RED",red);
        intent.putExtra("FINAL_GREEN",green);
        intent.putExtra("FINAL_BLUE",blue);

        startActivity(intent);
    }
}