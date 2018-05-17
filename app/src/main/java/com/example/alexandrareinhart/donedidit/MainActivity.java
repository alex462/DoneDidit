package com.example.alexandrareinhart.donedidit;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alexandrareinhart.donedidit.ViewFragments.AddNewFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewAllFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewCompletedFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewIncompleteFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private ViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;
    @BindView(R.id.view_pager)
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.AddFragment(new AddNewFragment(), "NEW");
        viewPagerAdapter.AddFragment(new ViewAllFragment(), "ALL");
        viewPagerAdapter.AddFragment(new ViewIncompleteFragment(), "INCOMPLETE");
        viewPagerAdapter.AddFragment(new ViewCompletedFragment(), "COMPLETED");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_add_task);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_view_all);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_view_incomplete);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_view_completed);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
