package com.bytebucket.medico.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.bytebucket.medico.R;
import com.bytebucket.medico.activities.AddReminderActivity;
import com.bytebucket.medico.adapters.PillAdapter;
import com.bytebucket.medico.models.PillReminder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PillReminderFragment extends Fragment {

    ImageView fab;
    RecyclerView recyclerView;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ArrayList<PillReminder> arrayList;

    public PillReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pill_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fab = view.findViewById(R.id.pill_reminder_fab);
        recyclerView = view.findViewById(R.id.pill_reminder_rv);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddReminderActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = prefs.edit();
        int count = prefs.getInt("count", 0);
        arrayList = new ArrayList<PillReminder>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PillAdapter adapter = new PillAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        for (int k = count; k > 0; k--) {
            if (prefs.getString("name" + k, null) != null) {
                PillReminder pillReminder = new PillReminder(
                        prefs.getString("name" + k, ""),
                        prefs.getString("dosage" + k, ""),
                        prefs.getInt("hour" + k, 0),
                        prefs.getInt("min" + k, 8),
                        prefs.getInt("id" + k, 0),
                        prefs.getInt("freq" + k,0));
                arrayList.add(pillReminder);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
