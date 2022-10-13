package com.example.paint;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class CanvaFragment extends Fragment {

    Integer red = 0, green = 0, blue = 0;
    View canva;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        canva = inflater.inflate(R.layout.fragment_canva, container, false);
        // Inflate the layout for this fragment
        return canva;
    }

}