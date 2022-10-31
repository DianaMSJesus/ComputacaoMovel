package com.example.paint;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;

public class Sensors implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor shake;
    private float accelerationCurrentValue;
    private float accelerationPreviousValue;
    private double force;
    private PaintCanvas canvas;

    public Sensors(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        shake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        force = 10f;
        accelerationCurrentValue = SensorManager.GRAVITY_EARTH;
        accelerationPreviousValue = SensorManager.GRAVITY_EARTH;
    }

    public void setCanvas(PaintCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent!=null){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelerationPreviousValue = accelerationCurrentValue;

            accelerationCurrentValue = (float) Math.sqrt((x * x + y * y + z * z));
            float delta = accelerationCurrentValue - accelerationPreviousValue;
            force = force * 0.9f + delta;

            if(force > 12 || force < -3){
                canvas.erase();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onResume(){
        sensorManager.registerListener(this,shake,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(){
        sensorManager.unregisterListener(this);
    }

}
