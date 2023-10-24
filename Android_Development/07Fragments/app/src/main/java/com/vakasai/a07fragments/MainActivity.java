package com.vakasai.a07fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    FragmentTransaction ft;
    FragmentManager fm;
    ArrayList<myFrag> frags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frags = new ArrayList<>();
    }

    public void createFrag(View view) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        Random rnd = new Random();
        String all = "1234567890qwertyuiopasdfghjklzxcvbnnm";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++)
            sb.append(all.charAt(rnd.nextInt(all.length())));
        myFrag mf = myFrag.newInstance(sb.toString());
        if (rnd.nextBoolean()) {
            ft.add(R.id.placeholder, mf, "added!");
        } else {
            ft.replace(R.id.placeholder, mf, "replaced!");
        }
        ft.commit();
    }


    public void endFrag(String tag) {
        ft = fm.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        myFrag mf = (myFrag) fm.findFragmentByTag(tag);
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            ft.remove(mf);
            Toast.makeText(this, "Fragment: \"" + tag + "\" was removed!", Toast.LENGTH_SHORT).show();
        } else {
            ft.hide(mf);
            frags.add(mf);
            Toast.makeText(this, "Fragment: \"" + tag + "\" was hidden!", Toast.LENGTH_SHORT).show();
        }
        ft.commit();
    }

    public void showall(View view) {
        ft = fm.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        for(Fragment i: frags){
            ft.show(i);
        }
        ft.commit();
    }
}

