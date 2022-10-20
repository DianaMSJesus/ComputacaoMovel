package com.example.paint;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class CanvaFragment extends Fragment {

    View canva;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        canva = inflater.inflate(R.layout.fragment_canva, container, false);

        ConstraintLayout constraintLayout = canva.findViewById(R.id.canvas);
        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(getContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);
        mGestureDetector.setOnDoubleTapListener(mGestureListener);

        PaintCanvas paintCanvas = new PaintCanvas(getContext(), null, mGestureDetector);
        mGestureListener.setCanvas(paintCanvas);

        constraintLayout.addView(paintCanvas);// adds the created view to the screen
        // Inflate the layout for this fragment
        return canva;
    }

}