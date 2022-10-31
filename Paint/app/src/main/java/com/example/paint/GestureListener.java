package com.example.paint;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnDoubleTapListener {

    private PaintCanvas canvas;

    public void setCanvas(PaintCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent){
        canvas.randomColorLine();
    }

    public boolean onDoubleTap(MotionEvent motionEvent){
        canvas.erase();
        return false;
    }

}
