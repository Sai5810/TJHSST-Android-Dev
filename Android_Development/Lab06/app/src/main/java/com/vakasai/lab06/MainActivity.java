package com.vakasai.lab06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewP;
    RecyclerView.Adapter fragStateAdapater;
    int nItems = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewP = findViewById(R.id.container);
        fragStateAdapater = new fSAdapt(this);
        viewP.setAdapter(fragStateAdapater);
        viewP.setPageTransformer(new vTransformer());
    }


    private class fSAdapt extends FragmentStateAdapter {

        public fSAdapt(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(viewP, position);
        }

        @Override
        public int getItemCount() {
            return nItems;
        }
    }

    private static class vTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(@NonNull View page, float position) {
            page.setAlpha(1 - position);
            page.setTranslationX(page.getWidth() * -position);
            float MIN_SCALE = 0.7f;
            float scaleFactor = Math.abs(position) * (MIN_SCALE - 1) + 1;
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}