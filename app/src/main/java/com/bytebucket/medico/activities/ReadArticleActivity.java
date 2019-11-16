package com.bytebucket.medico.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.utilities.Constants;

public class ReadArticleActivity extends AppCompatActivity {

    ImageView ivArticleImage;
    TextView tvHeading, tvArticle, tvDocName,tvReadTime, tvDate;
    de.hdodenhof.circleimageview.CircleImageView civDocImage;

    //String customHTML = "<h2>Abstract</h2><ul><li>Background</li><li>Methods</li><li>Results</li><li>Conclusions</li></ul><p><br/></p><h3>BACKGROUND</h3><p>Difelikefalin is a peripherally restricted and selective agonist of kappa opioid receptors that are considered to be important in modulating pruritus in conditions such as chronic kidney disease.<br/></p><h3>METHODS</h3><p>In this double-blind, placebo-controlled, phase 3 trial, we randomly assigned patients undergoing hemodialysis who had moderate-to-severe pruritus to receive either intravenous difelikefalin (at a dose of 0.5 μg per kilogram of body weight) or placebo three times per week for 12 weeks. The primary outcome was the percentage of patients with an improvement (decrease) of at least 3 points from baseline at week 12 in the weekly mean score on the 24-hour Worst Itching Intensity Numerical Rating Scale (WI-NRS; scores range from 0 to 10, with higher scores indicating greater itch intensity). The secondary outcomes included the change from baseline in itch-related quality-of-life measures, the percentage of patients with an improvement of at least 4 points in the WI-NRS score at week 12, and safety.<br/></p><h3>RESULTS</h3><p>A total of 378 patients underwent randomization. A total of 82 of 158 patients (51.9%) in the difelikefalin group had a decrease of at least 3 points in the WI-NRS score (primary outcome), as compared with 51 of 165 (30.9%) in the placebo group. The imputed percentage of patients with a decrease of at least 3 points in the WI-NRS score was 49.1% in the difelikefalin group, as compared with 27.9% in the placebo group (P&lt;0.001). Difelikefalin also resulted in a significant improvement from baseline to week 12 in itch-related quality of life as measured by the 5-D itch scale and the Skindex-10 scale. The imputed percentage of patients with a decrease of at least 4 points in the WI-NRS score at week 12 was significantly greater in the difelikefalin group than in the placebo group (37.1% [observed data: 64 of 158 patients] vs. 17.9% [observed data: 35 of 165 patients], P&lt;0.001). Diarrhea, dizziness, and vomiting were more common in the difelikefalin group than in the placebo group.<br/></p><h3>CONCLUSIONS</h3><p>Patients treated with difelikefalin had a significant reduction in itch intensity and improved itch-related quality of life as compared with those who received placebo. (Funded by Cara Therapeutics; KALM-1 ClinicalTrials.gov number, <a style=\"color:#0b4f82\" href=\"http://clinicaltrials.gov/show/NCT03422653\" target=\"_blank\">NCT03422653</a>.)</p>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        Bundle extras = getIntent().getExtras();
        tvHeading.setText(extras.getString("heading"));
        getFormattedTextFromHTML(extras.getString("article"));
        tvDocName.setText(extras.getString("docname"));
        tvReadTime.setText(extras.getString("readtime"));
        tvDate.setText(extras.getString("date"));
        ivArticleImage.setImageResource(extras.getInt("image"));
        civDocImage.setImageResource(Constants.getRandomProfilePic());




    }

    private void getFormattedTextFromHTML(String rawString){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvArticle.setText(Html.fromHtml(rawString, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvArticle.setText(Html.fromHtml(rawString));
        }
    }

    private void init() {
        ivArticleImage = findViewById(R.id.read_article_image);
        tvHeading = findViewById(R.id.read_article_heading);
        tvArticle = findViewById(R.id.read_article_article);
        tvDocName = findViewById(R.id.read_article_docname);
        tvReadTime = findViewById(R.id.read_article_readtime);
        tvDate = findViewById(R.id.read_article_date);
        civDocImage = findViewById(R.id.read_article_docimage);
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





// setContentView(R.layout.activity_read_article);


//previous

    /*private WebView webView;
    String customHTML = "<h2>Abstract</h2><ul><li>Background</li><li>Methods</li><li>Results</li><li>Conclusions</li></ul><p><br/></p><h3>BACKGROUND</h3><p>Difelikefalin is a peripherally restricted and selective agonist of kappa opioid receptors that are considered to be important in modulating pruritus in conditions such as chronic kidney disease.<br/></p><h3>METHODS</h3><p>In this double-blind, placebo-controlled, phase 3 trial, we randomly assigned patients undergoing hemodialysis who had moderate-to-severe pruritus to receive either intravenous difelikefalin (at a dose of 0.5 μg per kilogram of body weight) or placebo three times per week for 12 weeks. The primary outcome was the percentage of patients with an improvement (decrease) of at least 3 points from baseline at week 12 in the weekly mean score on the 24-hour Worst Itching Intensity Numerical Rating Scale (WI-NRS; scores range from 0 to 10, with higher scores indicating greater itch intensity). The secondary outcomes included the change from baseline in itch-related quality-of-life measures, the percentage of patients with an improvement of at least 4 points in the WI-NRS score at week 12, and safety.<br/></p><h3>RESULTS</h3><p>A total of 378 patients underwent randomization. A total of 82 of 158 patients (51.9%) in the difelikefalin group had a decrease of at least 3 points in the WI-NRS score (primary outcome), as compared with 51 of 165 (30.9%) in the placebo group. The imputed percentage of patients with a decrease of at least 3 points in the WI-NRS score was 49.1% in the difelikefalin group, as compared with 27.9% in the placebo group (P&lt;0.001). Difelikefalin also resulted in a significant improvement from baseline to week 12 in itch-related quality of life as measured by the 5-D itch scale and the Skindex-10 scale. The imputed percentage of patients with a decrease of at least 4 points in the WI-NRS score at week 12 was significantly greater in the difelikefalin group than in the placebo group (37.1% [observed data: 64 of 158 patients] vs. 17.9% [observed data: 35 of 165 patients], P&lt;0.001). Diarrhea, dizziness, and vomiting were more common in the difelikefalin group than in the placebo group.<br/></p><h3>CONCLUSIONS</h3><p>Patients treated with difelikefalin had a significant reduction in itch intensity and improved itch-related quality of life as compared with those who received placebo. (Funded by Cara Therapeutics; KALM-1 ClinicalTrials.gov number, <a style=\"color:#0b4f82\" href=\"http://clinicaltrials.gov/show/NCT03422653\" target=\"_blank\">NCT03422653</a>.)</p>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        webView.loadData(customHTML, "text/html", "UTF_8");

        setContentView(webView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(webView!= null){
            webView.destroy();
        }
    }*/