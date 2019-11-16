package com.bytebucket.medico.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebucket.medico.R;
import com.bytebucket.medico.activities.DoctorsActivity;
import com.bytebucket.medico.adapters.TabPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentsFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fabNewAppointment;

    public AppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String tabNames[]={"PAST","TODAY","FUTURE"};
        tabLayout = view.findViewById(R.id.menu_tabs);
        viewPager = view.findViewById(R.id.tab_viewpager);
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0;i<3;i++)
        {
            tabLayout.getTabAt(i).setText(tabNames[i]);
        }
        viewPager.setCurrentItem(1);

        fabNewAppointment = view.findViewById(R.id.appointments_add_appointment);
        fabNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doctorIntent = new Intent(getActivity(), DoctorsActivity.class);
                startActivity(doctorIntent);
            }
        });

    }
}
