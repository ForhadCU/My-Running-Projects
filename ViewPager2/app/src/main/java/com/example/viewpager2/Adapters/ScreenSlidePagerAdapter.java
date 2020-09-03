package com.example.viewpager2.Adapters;

import com.example.viewpager2.Fragment_Mid;
import com.example.viewpager2.Fragment_right;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new Fragment_Mid();
                break;
            case 1:
                fragment = new Fragment_right();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
