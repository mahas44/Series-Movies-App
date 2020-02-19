package com.enes.moviesapp.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.enes.moviesapp.R;
import com.enes.moviesapp.adapter.CustomPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity{

    CustomPagerAdapter customPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        setupPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupPager(ViewPager viewPager) {
        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), 1);
        customPagerAdapter.addFragment(new MovieFragment(), "MOVIES");
        customPagerAdapter.addFragment(new SeriesFragment(), "SERIES");
        viewPager.setAdapter(customPagerAdapter);
    }

}
