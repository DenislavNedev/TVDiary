package com.adastra.tvdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.show_name)
    TextView showName;
    @BindView(R.id.show_cover_image)
    ImageView coverImageView;
    @BindView(R.id.show_genres)
    TextView showGenres;
    @BindView(R.id.show_summary)
    TextView showSummary;
    @BindView(R.id.show_rating)
    TextView showRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        loadCoverImage(intent.getStringExtra("showCoverImage"));
        showName.setText(intent.getStringExtra("showName"));
        setShowGenres(intent.getStringArrayListExtra("showGenres"));
        showRating.setText("Rating: " + Double.toString(intent.getDoubleExtra("showRating", 0)));
        showSummary.setText(intent.getStringExtra("showSummary"));
    }

    public void setShowGenres(ArrayList<String> genres) {
        for (int i = 0; i < genres.size(); i++) {
            if (i == genres.size() - 1) {
                showGenres.append(genres.get(i));
            } else {
                showGenres.append(genres.get(i));
                showGenres.append(", ");
            }
        }
    }

    public void loadCoverImage(String imageUrl) {
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(coverImageView);
    }
}
