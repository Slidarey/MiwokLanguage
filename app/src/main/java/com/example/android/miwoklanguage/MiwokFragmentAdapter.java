package com.example.android.miwoklanguage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Zahir on 12/09/2017.
 */

public class MiwokFragmentAdapter extends FragmentPagerAdapter {
    Context context;

    public MiwokFragmentAdapter(FragmentManager fm, Context nContext) {
        super(fm);
        this.context = nContext;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else if (position == 3) {
            return new PhrasesFragment();
        } else {
            return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_numbers);
        } else if (position == 1) {
            return context.getString(R.string.category_family);
        } else if (position == 2) {
            return context.getString(R.string.category_colors);
        } else if (position == 3) {
            return context.getString(R.string.category_phrases);
        } else {
            return null;
        }
    }


}
