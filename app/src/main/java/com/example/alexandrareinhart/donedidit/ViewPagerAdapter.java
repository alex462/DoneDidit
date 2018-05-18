package com.example.alexandrareinhart.donedidit;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alexandrareinhart.donedidit.ViewFragments.AddNewFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewAllFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewCompletedFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewIncompleteFragment;

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
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                AddNewFragment addNew = new AddNewFragment();
                return addNew;
            case 1:
                ViewAllFragment allTab = new ViewAllFragment();
                return allTab;
            case 2:
                ViewIncompleteFragment incompleteTab = new ViewIncompleteFragment();
                return incompleteTab;
            case 3:
                ViewCompletedFragment completedTab = new ViewCompletedFragment();
                return completedTab;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
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
