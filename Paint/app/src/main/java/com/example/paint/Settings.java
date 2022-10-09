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

    //Variavél para enviar cor
    public static final String EXTRA_BACKGROUND = "#FF000000";

    View v_Color;
    SeekBar sRed, sGreen, sBlue;
    Integer red = 0, green = 0, blue = 0, alfa = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Conectar as variáveis aos componentes do display
        v_Color = findViewById(R.id.v_color);
        sRed = findViewById(R.id.s_Red);
        sGreen = findViewById(R.id.s_Green);
        sBlue = findViewById(R.id.s_Blue);

        //Verificar alterações nas SeekBars
        sRed.setOnSeekBarChangeListener(this);
        sGreen.setOnSeekBarChangeListener(this);
        sBlue.setOnSeekBarChangeListener(this);
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

        v_Color.setBackgroundColor(Color.argb(alfa,red,green,blue));
    }

    private String HexCode(int red, int green, int blue){
        String value = "";
        String code = "#";

        //Red
        value = Integer.toHexString(red);
        if (value.length() < 2){
            code+="0" + value;
        }else{
            code+=value;
        }

        //Green
        value = Integer.toHexString(green);
        if (value.length() < 2){
            code+="0" + value;
        }else{
            code+=value;
        }

        //Blue
        value = Integer.toHexString(blue);
        if (value.length() < 2){
            code+="0" + value;
        }else{
            code+=value;
        }

        return code;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void changeColor(View view){
        Intent intent = new Intent(this, MainActivity.class);
        String color = HexCode(red,green,blue);

        intent.putExtra(EXTRA_BACKGROUND, color);

        startActivity(intent);
    }
}