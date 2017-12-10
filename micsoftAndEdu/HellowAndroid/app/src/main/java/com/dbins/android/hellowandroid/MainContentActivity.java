package com.dbins.android.hellowandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.dbins.android.hellowandroid.fregment.Page1Fragment;
import com.dbins.android.hellowandroid.fregment.Page2Fragment;
import com.dbins.android.hellowandroid.fregment.Page3Fragment;

public class MainContentActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mPager = findViewById(R.id.mainPager);
        mPager.setOffscreenPageLimit(5);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }
    public static class MyPagerAdapter extends FragmentStatePagerAdapter{
        FragmentManager mFm;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            this.mFm = fm;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Page1Fragment();
                case 1:
                    return new Page2Fragment();
                case 2:
                    return new Page3Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
