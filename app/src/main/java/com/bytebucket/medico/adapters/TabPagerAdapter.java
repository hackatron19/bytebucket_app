package com.bytebucket.medico.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bytebucket.medico.fragments.FutureAppointmentFragment;
import com.bytebucket.medico.fragments.PastAppointmentFragment;
import com.bytebucket.medico.fragments.TodayAppointmentFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0)
            return new PastAppointmentFragment();
        if (i == 1)
            return new TodayAppointmentFragment();
        if (i == 2)
            return new FutureAppointmentFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
