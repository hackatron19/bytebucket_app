package com.bytebucket.medico.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bytebucket.medico.R;
import com.bytebucket.medico.activities.DoctorsActivity;
import com.bytebucket.medico.activities.ReadArticleActivity;
import com.bytebucket.medico.adapters.ArticleAdapter;
import com.bytebucket.medico.adapters.DoctorAdapter;
import com.bytebucket.medico.modals.Article;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    public NewsFragment() {
        // Required empty public constructor
    }


    RecyclerView rvArticles;
    ArrayList<Article> articles = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ArticleAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvArticles = view.findViewById(R.id.article_recycler);
        layoutManager = new LinearLayoutManager(getActivity());

        rvArticles.setHasFixedSize(true);
        rvArticles.setLayoutManager(layoutManager);

        mAdapter = new ArticleAdapter(getActivity(), articles);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
        rvArticles.addItemDecoration(itemDecorator);
        rvArticles.setAdapter(mAdapter);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("articles");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                articles.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Article article = ds.getValue(Article.class);
                    articles.add(article);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
