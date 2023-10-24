package com.vakasai.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor pEdit;
    int[] idR, idL;

    private void updateL() {
        for (int i : idL) {
            TextView tv = ((TextView) findViewById(i));
            String tvS = (String) tv.getText();
            String x = tvS.substring(0, tvS.length() - 1) + pref.getInt(String.valueOf(i), -1);
            tv.setText(x);
        }
    }

    protected void addL(int id) {
        int nv = pref.getInt(String.valueOf(id), -1) + 1;
        pEdit.putInt(String.valueOf(id), nv);
        pEdit.apply();
        TextView tv = ((TextView) findViewById(id));
        String tvS = tv.getResources().getResourceName(tv.getId());
        String x = tvS.substring(21, tvS.length() - 1) + ": " + nv;
        tv.setText(x);
    }

    private void addR(int id) {
        TextView tv = ((TextView) findViewById(id));
        String tvS = (String) tv.getText();
        int nv = Integer.parseInt(tvS.substring(tvS.length() - 1)) + 1;
        String x = tvS.substring(0, tvS.length() - 1) + nv;
        tv.setText(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("com.vakasai.lab03.shpref", MODE_PRIVATE);
        pEdit = pref.edit();
        idL = new int[]{R.id.onCreateL, R.id.onStartL, R.id.onResumeL, R.id.onPauseL, R.id.onStopL, R.id.onRestartL, R.id.onDestroyL};
        idR = new int[]{R.id.onCreateR, R.id.onStartR, R.id.onResumeR, R.id.onPauseR, R.id.onStopR, R.id.onRestartR, R.id.onDestroyR};
        for (int i : idL) {
            if (pref.getInt(String.valueOf(i), -1) == -1) {
                pEdit.putInt(String.valueOf(i), 0);
            }
        }
        pEdit.apply();
        addL(R.id.onCreateL);
        addR(R.id.onCreateR);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addL(R.id.onStartL);
        addR(R.id.onStartR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateL();
        addL(R.id.onResumeL);
        addR(R.id.onResumeR);
    }


    @Override
    protected void onPause() {
        super.onPause();
        addL(R.id.onPauseL);
        addR(R.id.onPauseR);
    }

    @Override
    protected void onStop() {
        super.onStop();
        addL(R.id.onStopL);
        addR(R.id.onStopR);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addL(R.id.onRestartL);
        addR(R.id.onRestartR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addL(R.id.onDestroyL);
        addR(R.id.onDestroyR);
    }

    public void resL(View view) {
        for (int i : idL) {
            pEdit.putInt(String.valueOf(i), 0);
        }
        pEdit.apply();
        for (int i : idL) {
            TextView tv = ((TextView) findViewById(i));
            String tvS = (String) tv.getText();
            String x = tvS.substring(0, tvS.length() - 1) + 0;
            tv.setText(x);
        }
    }

    public void resR(View view) {
        for (int i : idR) {
            TextView tv = ((TextView) findViewById(i));
            String tvS = (String) tv.getText();
            String x = tvS.substring(0, tvS.length() - 1) + 0;
            tv.setText(x);
        }
    }
}