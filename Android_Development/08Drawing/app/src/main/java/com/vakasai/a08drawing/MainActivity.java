package com.vakasai.a08drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    DrawView dv;
    int[] ids;
    boolean flag = false;
    MediaPlayer medP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dv = findViewById(R.id.drawme);
        ids = new int[]{R.id.leftEye, R.id.rightEye, R.id.leftFoot, R.id.rightFoot, R.id.leftHand, R.id.rightHand, R.id.chain, R.id.dripjacket, R.id.jermasus};
    }

    public void revY(View view) {
        if (medP != null && medP.isPlaying()) {
            medP.stop();
        }
        dv.setDy(dv.getDy() * -1);
        Intent myIntent = new Intent(MainActivity.this, DarkAct.class);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        int alp;
        if (!flag) {
            flag = true;
            alp = 1;
            medP = MediaPlayer.create(this, R.raw.ultra);
            medP.start();
        } else {
            flag = false;
            alp = 0;
        }
        for (int i : ids) {
            findViewById(i).setAlpha(alp);
        }
    }
}