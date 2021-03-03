package com.kathleenwang.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kathleenwang.flixster.Models.Movie;

import org.parceler.Parcels;

public class DetailActivity extends YouTubeBaseActivity {
    public static final String YOUTUBE_API_KEY = "AIzaSyBVC4rmPGeV8rI_ZlTA80P5VLQ-5zkzVv4";

    TextView tvTitle;
    TextView tvDescription;
    RatingBar ratingBar;
    YouTubePlayerView youtubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.detailTitle);
        tvDescription = findViewById(R.id.detailDescription);
        ratingBar = findViewById(R.id.detailRatingBar);
        youtubePlayerView = findViewById(R.id.player);
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvDescription.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());

        youtubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "success");
                youTubePlayer.cueVideo("5xVh-7ywKpE");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "failure");
            }
        });
    }
}