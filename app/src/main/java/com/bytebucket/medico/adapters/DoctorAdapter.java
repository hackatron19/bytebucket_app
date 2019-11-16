package com.bytebucket.medico.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.activities.DoctorDetailsActivity;
import com.bytebucket.medico.modals.Doctor;
import com.bytebucket.medico.utilities.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    Context mContext;
    private ArrayList<Doctor> doctors = new ArrayList<>();

    public DoctorAdapter(ArrayList<Doctor> doctors, Context context) {
        this.doctors = doctors;
        mContext = context;
    }

    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.single_doctor_element, parent, false);
        return new DoctorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DoctorViewHolder holder, int position) {
        final Doctor doctor = doctors.get(position);
        final int profilePic = Constants.getRandomProfilePic();
        holder.civDoctorImage.setImageResource(profilePic);
        holder.tvDoctorName.setText(doctor.getName());
        holder.tvSpeciality.setText(doctor.getSpeciality());
        holder.tvPhone.setText(doctor.getMobile());
        String strRating = Integer.toString(doctor.getRating());
        holder.tvRating.setText(strRating);

        holder.llDoctorElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DoctorDetailsActivity.class);
                Gson gson = new Gson();
                intent.putExtra("doctor", gson.toJson(doctor));
                intent.putExtra("image",profilePic);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(holder.tvDoctorName, "DoctorNameTransition");
                pairs[1] = new Pair<View, String>(holder.civDoctorImage, "ProfileImageTransition");
                pairs[2] = new Pair<View, String>(holder.tvSpeciality, "DoctorSpecialityTransition");
                pairs[3] = new Pair<View, String>(holder.tvPhone, "DoctorPhoneTransition");

                Activity activity = (Activity) mContext;
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, pairs);
                mContext.startActivity(intent, options.toBundle());

            }
        });

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDoctorName, tvSpeciality, tvPhone, tvRating;
        public de.hdodenhof.circleimageview.CircleImageView civDoctorImage;
        LinearLayout llDoctorElement;

        public DoctorViewHolder(View v) {
            super(v);
            tvDoctorName = v.findViewById(R.id.doctor_name);
            tvSpeciality = v.findViewById(R.id.doctor_speciality);
            tvPhone = v.findViewById(R.id.doctor_phone);
            tvRating = v.findViewById(R.id.doctor_rating);
            civDoctorImage = v.findViewById(R.id.doctor_image);
            llDoctorElement = v.findViewById(R.id.doctor_element);
        }
    }
}