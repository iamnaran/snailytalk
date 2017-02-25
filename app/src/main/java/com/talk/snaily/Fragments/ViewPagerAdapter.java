package com.talk.snaily.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.talk.snaily.Fragments.Fragment_1;
import com.talk.snaily.Fragments.Fragment_2;
import com.talk.snaily.Fragments.Fragment_3;

/**
 * Created by Administrator on 15/09/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                Fragment_1 tab1 = new Fragment_1();

                return tab1;
            case 1:
                Fragment_2 tab2 = new Fragment_2();

                return tab2;
            case 2:
                Fragment_3 tab3 = new Fragment_3();

                return tab3;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {


        return mNumOfTabs;
    }
}
