package com.bytebucket.medico.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.activities.ReadArticleActivity;
import com.bytebucket.medico.modals.Article;
import com.bytebucket.medico.utilities.Constants;
import com.google.firebase.database.core.utilities.Utilities;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    Context context;
    ArrayList<Article> articles;

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_article_layout, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleViewHolder holder, int position) {
        final Article article = articles.get(position);
        holder.tvDocName.setText(article.getAuthor());
        holder.tvHeading.setText(article.getHeading());
        holder.tvReadTime.setText(getReadTime(article.getDescription()));
        holder.tvDate.setText(article.getPostdate());
        final int image = Constants.getRandomArticlePic();
        holder.ivImageContent.setImageResource(image);

        holder.rlFullLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readArticleIntent = new Intent(context, ReadArticleActivity.class);
                readArticleIntent.putExtra("heading",article.getHeading());
                readArticleIntent.putExtra("article",article.getDescription());
                readArticleIntent.putExtra("docname",article.getAuthor());
                readArticleIntent.putExtra("readtime", getReadTime(article.getDescription()));
                readArticleIntent.putExtra("date",article.getPostdate());
                readArticleIntent.putExtra("dfuid",article.getDfuid());
                readArticleIntent.putExtra("image",image);
                context.startActivity(readArticleIntent);

            }
        });

    }

    private String getReadTime(String description) {
        String withoutWhitespace = description.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        String withoutTags = withoutWhitespace.replaceAll("<[^>]*>", "");

        int wordCount;
        String[] words = withoutTags.split("\\s+");
        wordCount = words.length;

        double timeToread = wordCount/ Constants.WORDS_PER_MINUTE;
        double half = 0.5;
        double one = 1;
        if(timeToread < half)
            return "Less than a minute read";
        else if(timeToread <= one)
            return "1 minute read";
        else{
            int timeToRead = (int)Math.ceil(timeToread);
            return ""+ timeToRead + " minute read";
        }


    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeading, tvDocName, tvReadTime,tvDate;
        ImageView ivImageContent;
        RelativeLayout rlFullLayout;
        public ArticleViewHolder(View v) {
            super(v);
            tvDocName = v.findViewById(R.id.single_article_docname);
            tvHeading = v.findViewById(R.id.single_article_heading);
            tvReadTime = v.findViewById(R.id.single_article_readtime);
            ivImageContent = v.findViewById(R.id.single_article_image);
            tvDate = v.findViewById(R.id.single_article_date);
            rlFullLayout = v.findViewById(R.id.single_article_complete_layout);
        }
    }
}
