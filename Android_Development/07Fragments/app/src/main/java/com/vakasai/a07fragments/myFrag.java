package com.vakasai.a07fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class myFrag extends Fragment {
    public static myFrag newInstance(String pass) {
        myFrag mf = new myFrag();
        Bundle args = new Bundle();
        args.putString("passedin", pass);
        mf.setArguments(args);
        return mf;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String all = "0123456789ABCDEF";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++)
            sb.append(all.charAt(rnd.nextInt(all.length())));
        view.setBackgroundColor(Color.parseColor("#" + sb));
        Button ret = requireView().findViewById(R.id.retbutton);
        TextView tv = requireView().findViewById(R.id.tvRand);
        tv.setText(getTag());
        ret.setOnClickListener(v -> ((MainActivity) getActivity()).endFrag(getTag()));
        Button wwpi = requireView().findViewById(R.id.wwpi);
        wwpi.setOnClickListener(v -> wwpi.setText(getArguments().getString("passedin")));
    }
}
