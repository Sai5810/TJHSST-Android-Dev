package com.vakasai.lab06;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.concurrent.ThreadLocalRandom;

public class MainFragment extends Fragment {
    ViewPager2 viewP;
    int pos;

    public static Fragment newInstance(ViewPager2 viewP, int pos) {
        MainFragment frag = new MainFragment();
        frag.viewP = viewP;
        frag.pos = pos;
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int[] sounds = {R.raw.bruh, R.raw.animal, R.raw.dontyoufeelstupid, R.raw.monke, R.raw.youstupid};
        TypedArray images = getResources().obtainTypedArray(R.array.sus);
        int choice = (int) (Math.random() * images.length());
        view.setBackgroundResource(images.getResourceId(choice, 0));
        images.recycle();
        view.setAlpha(.5F);
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewP, (tab, position) -> tab.setText("D" + (position))).attach();
        Button btn = view.findViewById(R.id.appbutton);
        btn.setText("IMPOSTOR????");
        btn.setOnClickListener(v -> {
            if(choice != 3){
                for (int i = 0; i < 3; ++i) {
                    MediaPlayer medP;
                    int temp = (int) (Math.random() * sounds.length);
                    medP = MediaPlayer.create(getActivity().getApplicationContext(), sounds[temp]);
                    medP.start();
                }
            } else {
                MediaPlayer medP;
                medP = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.rolereveal);
                medP.start();
                Intent intent = new Intent(getActivity().getApplicationContext(), SecondActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}
