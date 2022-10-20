package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
import java.util.Random;

public class PaintCanvas extends View implements View.OnTouchListener{

    private Paint paint = new Paint();
    private Path path = new Path();
    private int backgroundColor = Color.WHITE;
    private GestureDetector mGestureDetector;


    public PaintCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        setBackgroundColor(backgroundColor);
        initPaint();
    }

    public PaintCanvas(Context context, AttributeSet attrs, GestureDetector mGestureDetector){
        super(context,attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        setBackgroundColor(backgroundColor);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPath(path, paint);
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
                path.moveTo(eventX,eventY);
                return true;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX,eventY);
                break;

            case MotionEvent.ACTION_UP:
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
        backgroundColor = Color.argb(255,color.nextInt(256),color.nextInt(256),color.nextInt(256));
        setBackgroundColor(backgroundColor);
    }

    public void erase(){
        paint.setColor(backgroundColor);
    }

    public void initPaint(){
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }
}
