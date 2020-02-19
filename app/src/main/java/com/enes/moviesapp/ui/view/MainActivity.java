package com.enes.moviesapp.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.enes.moviesapp.R;
import com.enes.moviesapp.adapter.CustomPagerAdapter;
import com.enes.moviesapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setOwner(this);

        setupPager(binding.viewPager);

        binding.tabs.setupWithViewPager(binding.viewPager);
    }

    private void setupPager(ViewPager viewPager) {
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), 1);
        customPagerAdapter.addFragment(new MovieFragment(), "MOVIES");
        customPagerAdapter.addFragment(new SeriesFragment(), "SERIES");
        viewPager.setAdapter(customPagerAdapter);
    }

}
