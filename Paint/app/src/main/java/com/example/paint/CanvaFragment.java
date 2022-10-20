package com.example.paint;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;

public class CanvaFragment extends Fragment {

    View canva;
    PaintCanvas paintCanvas;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        canva = inflater.inflate(R.layout.fragment_canva, container, false);

        ConstraintLayout constraintLayout = canva.findViewById(R.id.canvas);
        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(getContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);
        mGestureDetector.setOnDoubleTapListener(mGestureListener);

        paintCanvas = new PaintCanvas(getContext(), null, mGestureDetector);
        mGestureListener.setCanvas(paintCanvas);


        bundle = new Bundle();
        if(savedInstanceState != null){
            bundle = savedInstanceState.getBundle("BUNDLE");
            paintCanvas.setDrawColor(bundle.getInt("COLOR_RED"),bundle.getInt("COLOR_GREEN"),bundle.getInt("COLOR_BLUE"));
            paintCanvas.setDraw((ArrayList<PaintCanvas.SaveDraw>) bundle.getSerializable("DRAW"));

        }

        constraintLayout.addView(paintCanvas);// adds the created view to the screen

        return canva;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        bundle.putSerializable("DRAW", paintCanvas.getDraw());
        bundle.putInt("COLOR_RED", paintCanvas.getRedValue());
        bundle.putInt("COLOR_GREEN", paintCanvas.getGreenValue());
        bundle.putInt("COLOR_BLUE", paintCanvas.getBlueValue());
        outState.putBundle("BUNDLE",bundle);
    }


}