package com.example.employeeattendance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by Surya on 12/24/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;



    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }


    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            TabToday tab1 = new TabToday();
            return tab1;
        }
        else if(position == 1)
        {
            TabWeek tab2 = new TabWeek();
            return tab2;
        }
        else
        {
            TabMonth tab3 = new TabMonth();
            return tab3;
        }


    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }


    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}