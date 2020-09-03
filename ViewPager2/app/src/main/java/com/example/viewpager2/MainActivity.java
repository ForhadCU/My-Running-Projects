package com.example.viewpager2;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;

import com.example.viewpager2.Adapters.ScreenSlidePagerAdapter;

public class MainActivity extends FragmentActivity {
    private ViewPager2 viewPager2;
    private FragmentStateAdapter fragmentStateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorSecondary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorSecondary));
        }//End


        viewPager2 = findViewById(R.id.viewPager2Id);
        viewPager2.setPageTransformer(new DeptPageTransformer());
        fragmentStateAdapter = new ScreenSlidePagerAdapter(this);
        viewPager2.setAdapter(fragmentStateAdapter);
    }
}
