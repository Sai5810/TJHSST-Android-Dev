package com.vakasai.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    SharedPreferences pref;
    SharedPreferences.Editor pEdit;
    Button[] buttons;
    MediaPlayer medP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View fv = (findViewById(R.id.fullview));
        fv.setOnLongClickListener(this);
        buttons = new Button[]{findViewById(R.id.botRight), (Button) findViewById(R.id.botLeft),
                (Button) findViewById(R.id.topLeft), (Button) findViewById(R.id.topRight)};
        pref = getSharedPreferences("com.vakasai.lab03.shpref", MODE_PRIVATE);
        pEdit = pref.edit();
        for (Button i : buttons) {
            i.setOnClickListener(this);
            if (pref.getInt(String.valueOf(i.getId()), -1) == -1) {
                pEdit.putInt(String.valueOf(i.getId()), 0);
            }
        }
        pEdit.apply();

        SeekBar skview = findViewById(R.id.textSeek);
        skview.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prevprog;

            @Override
            public void onProgressChanged(SeekBar seekBar, int j, boolean b) {
                for (Button i : buttons) {
                    i.setTextSize(j);
                }
                medP.setVolume(j / 200f, j / 200f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                prevprog = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar sb = Snackbar.make(fv, "Size Changed to: " + buttons[0].getTextSize(), Snackbar.LENGTH_SHORT);
                sb.setAction("UNDO", view ->
                        skview.setProgress(prevprog)
                );
                sb.show();
            }
        });
        medP = MediaPlayer.create(this, R.raw.eminem);
        medP.start();
        medP.setVolume(10 / 200f, 10 / 200f);
    }

    @Override
    public void onClick(View view) {
        int val = pref.getInt(String.valueOf(view.getId()), 99) + 1;
        ((Button) view).setText(String.valueOf(val));
        pEdit.putInt(String.valueOf(view.getId()), val);
        pEdit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (Button i : buttons) {
            i.setText(String.valueOf(pref.getInt(String.valueOf(i.getId()), 313)));
        }
        medP.start();
        medP.setVolume(10 / 200f, 10 / 200f);
    }

    @Override
    public boolean onLongClick(View view) {
        for (Button i : buttons) {
            pEdit.putInt(String.valueOf(i.getId()), 0);
            i.setText(String.valueOf(0));
        }
        pEdit.apply();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        medP.stop();
        medP.release();
    }


    public void mpPause(View view) {
        if (medP.isPlaying()) {
            ((Button) view).setText(R.string.start);
            medP.pause();
        } else {
            ((Button) view).setText(R.string.pause);
            medP.start();
        }
    }
}