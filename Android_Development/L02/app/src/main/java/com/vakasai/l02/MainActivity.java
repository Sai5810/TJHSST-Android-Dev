package com.vakasai.l02;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playSong(View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wefoundlove);
        mp.start();
    }
}