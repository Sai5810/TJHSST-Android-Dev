package com.vakasai.Lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity {
    int i = 0;
    boolean flag = false;
    MediaPlayer medP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.vid).setVisibility(View.GONE);
    }

    public void insertText(View view) {
        EditText edit = findViewById(R.id.nameinput);
        TextView tv = findViewById(R.id.name);
        String s = edit.getText().toString();
        String[] values = {"sus", "amogus", "among us", "imposter"};
        if (Arrays.asList(values).contains(s)) {
            if (flag) {
                System.exit(0);
            } else {
                flag = true;
                findViewById(R.id.vid).setVisibility(View.VISIBLE);
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nosus);
                VideoView vw = findViewById(R.id.vid);
                vw.setVideoURI(uri);
                vw.requestFocus();
                vw.start();
            }
        } else {
            tv.setText(getString(R.string.reoutput, s));
        }
        Log.i("Pos:", String.valueOf(medP.getCurrentPosition()));
    }

    public void cycleText(View view) {
        String[] cycle = getResources().getStringArray(R.array.cycleArr);
        i = (i + 1) % cycle.length;
        ((TextView) findViewById(R.id.cycleTV)).setText(cycle[i]);
    }

    public void setColor(View view) {
        String all = "0123456789ABCDEF";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++)
            sb.append(all.charAt(rnd.nextInt(all.length())));
        view.setBackgroundColor(Color.parseColor("#" + sb));
        findViewById(R.id.cycleButton).setBackgroundColor(Color.parseColor("#" + sb));
        findViewById(R.id.inpButton).setBackgroundColor(Color.parseColor("#" + sb));
    }

    public void stop(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        if (medP.isPlaying()) {
            intent.putExtra("pos", medP.getCurrentPosition());
            startActivity(intent);
        }
        else {
            medP = MediaPlayer.create(this, R.raw.musicstopclean);
            medP.seekTo(ThreadLocalRandom.current().nextInt(0, medP.getDuration()));
            medP.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        medP.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        medP = MediaPlayer.create(this, R.raw.musicstopclean);
        medP.seekTo(ThreadLocalRandom.current().nextInt(0, medP.getDuration()));
        medP.start();
    }
}