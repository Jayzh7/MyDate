package com.example.jay.calendarview.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.jay.calendarview.fragments.MemorialDayFragment;
import com.example.jay.calendarview.fragments.DeadLineFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jay on 2016/12/22.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    private Map<Integer, String > mFragmentTags;
    private FragmentManager mFragmentManager;

    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);

        if(obj instanceof Fragment){
            Fragment f = (Fragment)obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return super.instantiateItem(container, position);
    }

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
        mFragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new DeadLineFragment();
            case 1:
                return new MemorialDayFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public Fragment getFragment(int position){
        String tag = mFragmentTags.get(position);
        if(tag == null) {
            Log.d("Adapter", "null tag");
            return null;
        }
        return mFragmentManager.findFragmentByTag(tag);
    }

    public void update(int position){
        Object fragment = getFragment(position);
        Log.d("Adapter", "fragment instance of fragment?");
        if(fragment == null) {
            Log.d("Adapter", "fragment null");
            return;
        }
        if(fragment instanceof DeadLineFragment) {
            ((DeadLineFragment) fragment).update();
            Log.d("Adapter", "update in adapter");
        }
    }
}
