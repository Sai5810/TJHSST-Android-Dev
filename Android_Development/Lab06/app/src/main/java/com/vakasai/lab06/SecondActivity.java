package com.vakasai.lab06;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {
    MediaPlayer medP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        medP = MediaPlayer.create(this, R.raw.amongsong);
        medP.start();
    }

    public void restart(View v) {
        medP.stop();
        finish();
    }

}

