package com.example.alexandrareinhart.donedidit;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> viewAllFragment = new ArrayList<>();
    private final List<String> allTaskTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return viewAllFragment.get(position);
    }

    @Override
    public int getCount() {
        return viewAllFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return allTaskTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String title) {
        viewAllFragment.add(fragment);
        allTaskTitles.add(title);
    }
}
