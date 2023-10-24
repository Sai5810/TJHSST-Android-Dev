package com.vakasai.lab04;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Map<Integer, Integer> lay;
    Map<Integer, Boolean> isSus;
    int layoutId;
    boolean sus;
    VideoView vw;

    @Override
    public void setContentView(int layoutResID) {
        this.layoutId = layoutResID;
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l14corner);
        lay = new HashMap<>();
        lay.put(R.layout.l14corner, R.layout.l2centermid);
        lay.put(R.layout.l2centermid, R.layout.l3guideround);
        lay.put(R.layout.l3guideround, R.layout.l4overlap);
        lay.put(R.layout.l4overlap, R.layout.l5scroll);
        lay.put(R.layout.l5scroll, R.layout.l6horzscroll);
        lay.put(R.layout.l6horzscroll, R.layout.l7nested);
        lay.put(R.layout.l7nested, R.layout.l14corner);
        isSus = new HashMap<>();
        isSus.put(R.id.blacksus, false);
        isSus.put(R.id.bluesus, false);
        isSus.put(R.id.greensus, false);
        isSus.put(R.id.orangesus, false);
        isSus.put(R.id.pinksus, false);
        isSus.put(R.id.whitesus, false);
        isSus.put(R.id.yellowsus, false);
        isSus.put(R.id.imposter, false);
    }

    public void next(View view) {
        int i = lay.get(layoutId);
        setContentView(i);
        if (i == R.layout.l5scroll) {
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new
                        BufferedReader(new InputStreamReader(getAssets().open("dec.txt")));
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    sb.append(mLine);
                    sb.append('\n');
                }
                ((TextView) findViewById(R.id.textView)).setText(sb);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading file!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static String title(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }


    public void sus(View view) {
        if (!isSus.get(view.getId())) {
            view.setAlpha(1);
            isSus.replace(view.getId(), true);
            String s = (String) view.getTag();
            if (!s.equals("red")) {
                Toast.makeText(this, String.format("%s was not an Impostor.\n1 Impostor remains", title(s)), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Crewmates Win!", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.drip2hard);
                vw = findViewById(R.id.videoView);
                vw.setOnPreparedListener(mp -> mp.setLooping(true));
                vw.setVisibility(View.VISIBLE);
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.drip);
                vw.setVideoURI(uri);
                vw.requestFocus();
                vw.start();
            }
        } else {
            view.setAlpha(0);
            isSus.replace(view.getId(), false);
        }
    }
}