package com.vakasai.lab01;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button incrementButton;
    TextView greetDisplay;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incrementButton = findViewById(R.id.incButton);
        greetDisplay = findViewById(R.id.greet_textview);
        incrementButton.setOnClickListener(view -> {
            System.out.println("incrementing: " + ++count);
            Log.i("incrementing", ""+count);
            greetDisplay.setText(""+count);
        });
    }

    public void decrement(View view) {
        System.out.println("decrementing: " + --count);
        greetDisplay.setText(""+count);
    }



    public void setColor(View view) {
        String all = "0123456789ABCDEF";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(6);
        for(int i = 0; i < 6; i++)
            sb.append(all.charAt(rnd.nextInt(all.length())));
        view.setBackgroundColor(Color.parseColor("#" + sb));
        findViewById(R.id.incButton).setBackgroundColor(Color.parseColor("#" + sb));
        findViewById(R.id.decButton).setBackgroundColor(Color.parseColor("#" + sb));
    }
} 