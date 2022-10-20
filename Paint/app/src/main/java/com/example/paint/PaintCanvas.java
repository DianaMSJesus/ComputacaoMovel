package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Random;

public class PaintCanvas extends View implements View.OnTouchListener{

    private int backgroundColor = Color.WHITE;
    private GestureDetector mGestureDetector;

    private static ArrayList<SaveDraw> listOfDraws = new ArrayList<>();
    private SaveDraw draw;

    private static int finalRed, finalGreen, finalBlue;

    public PaintCanvas(Context context, AttributeSet attrs, GestureDetector mGestureDetector){
        super(context,attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);

        draw = new SaveDraw(new Path(),initPaint());
    }

    public void setDraw(ArrayList<SaveDraw> saveDraws){
        listOfDraws = saveDraws;
    }

    public ArrayList<SaveDraw> getDraw(){
        return listOfDraws;
    }

    public int getRedValue(){
        return finalRed;
    }

    public int getGreenValue(){
        return finalGreen;
    }

    public int getBlueValue(){
        return finalBlue;
    }

    @Override
    protected void onDraw(Canvas canvas){

        for(SaveDraw sd : listOfDraws){
            canvas.drawPath(sd.path, sd.color);
        }
        canvas.drawPath(draw.path,draw.color);
    }

    @Override
    public boolean performClick(){
        return super.performClick();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                draw.path.moveTo(eventX,eventY);
                return true;

            case MotionEvent.ACTION_MOVE:
                draw.path.lineTo(eventX,eventY);
                break;

            case MotionEvent.ACTION_UP:
                listOfDraws.add(draw);
                draw = new SaveDraw(new Path(),initPaint());
                performClick();
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void changeBackground(){
        Random color = new Random();

        //setDrawColor(color.nextInt(256),color.nextInt(256),color.nextInt(256));

        backgroundColor = Color.argb(255,color.nextInt(256),color.nextInt(256),color.nextInt(256));
        setBackgroundColor(backgroundColor);
    }

    public void erase(){
        listOfDraws.clear();
    }

    public void setDrawColor(int red, int green, int blue){
        finalRed = red;
        finalGreen = green;
        finalBlue = blue;
    }

    public Paint initPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20f);
        paint.setColor(Color.argb(255,finalRed,finalGreen,finalBlue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        return paint;
    }

    public static class SaveDraw implements Serializable {
        private Path path;
        private Paint color;

        public SaveDraw(Path path, Paint color){
            this.path = path;
            this.color = color;
        }
    }
}
