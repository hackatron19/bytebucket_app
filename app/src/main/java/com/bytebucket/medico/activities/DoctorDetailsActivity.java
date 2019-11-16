package com.bytebucket.medico.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.adapters.ArticleAdapter;
import com.bytebucket.medico.modals.Article;
import com.bytebucket.medico.modals.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DoctorDetailsActivity extends AppCompatActivity {

    Button bCreateAppointment;
    TextView tvName, tvNumber, tvSpecility;
    de.hdodenhof.circleimageview.CircleImageView civProfileImage;
    RecyclerView rvFeatured;
    ArrayList<Article> featuredList = new ArrayList();
    RecyclerView.LayoutManager layoutManager;
    ArticleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        init();
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("doctor");
        final Doctor doctor = gson.fromJson(strObj, Doctor.class);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle(doctor.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvName.setText(doctor.getName());
        tvNumber.setText(doctor.getMobile());
        tvSpecility.setText(doctor.getSpeciality());
        civProfileImage.setImageResource(bundle.getInt("image"));

        bCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAppointment = new Intent(DoctorDetailsActivity.this, CreateAppointmentActivity.class);
                createAppointment.putExtra("dfuid", doctor.getFuid());
                startActivity(createAppointment);
            }
        });

        rvFeatured.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvFeatured.setLayoutManager(layoutManager);

        mAdapter = new ArticleAdapter(this, featuredList);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        rvFeatured.addItemDecoration(itemDecorator);
        rvFeatured.setAdapter(mAdapter);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("articles");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //progressBar.setVisibility(View.GONE);
                featuredList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Article article = ds.getValue(Article.class);
                    if(article.getDfuid().equalsIgnoreCase(doctor.getFuid()))
                    featuredList.add(article);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void init() {
        bCreateAppointment = findViewById(R.id.doctor_details_take_appointment);
        tvName = findViewById(R.id.doctor_details_name);
        tvNumber =findViewById(R.id.doctor_details_phone);
        tvSpecility = findViewById(R.id.doctor_details_speciality);
        civProfileImage = findViewById(R.id.doctor_details_profile_image);
        rvFeatured = findViewById(R.id.doctor_details_doctors_featured);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
