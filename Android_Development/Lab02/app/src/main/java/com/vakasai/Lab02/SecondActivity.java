package com.vakasai.Lab02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysecond);
        Intent mIntent = getIntent();
        int pos = mIntent.getIntExtra("pos", 0);
        int[] stop = {80000, 82000, 202000, 204000, 154000, 156000, 181000, 183000, 195000, 197000, 226000, 228000, 267000, 269000};
        boolean flag = false;
        for (int i = 0; i < stop.length; i += 2) {
            if (stop[i] <= pos && pos <= stop[i + 1]) {
                flag = true;
                (findViewById(R.id.jwin)).setAlpha(1);
            }
        }
        if (!flag) {
           (findViewById(R.id.jlose)).setAlpha(1);
        }
    }

    public void restart(View v) {
        finish();
    }
}
