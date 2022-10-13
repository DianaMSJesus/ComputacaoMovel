package com.example.paint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class PalletFragment extends Fragment {

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_red).setOnClickListener(view1 -> {
            bundle.putInt("FG_RED",255);
            bundle.putInt("FG_GREEN",0);
            bundle.putInt("FG_BLUE",0);
            getParentFragmentManager().setFragmentResult("bundle",bundle);
        });

        view.findViewById(R.id.btn_blue).setOnClickListener(view1 -> {
            bundle.putInt("FG_RED",0);
            bundle.putInt("FG_GREEN",0);
            bundle.putInt("FG_BLUE",255);

            getParentFragmentManager().setFragmentResult("bundle",bundle);
        });
    }
}