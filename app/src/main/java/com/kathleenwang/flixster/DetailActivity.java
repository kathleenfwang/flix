package com.kathleenwang.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kathleenwang.flixster.Models.Movie;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvDescription;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.detailTitle);
        tvDescription = findViewById(R.id.detailDescription);
        ratingBar = findViewById(R.id.detailRatingBar);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvDescription.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());
    }
}