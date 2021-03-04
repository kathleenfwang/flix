package com.kathleenwang.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kathleenwang.flixster.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {
    public static final String YOUTUBE_API_KEY = "AIzaSyBVC4rmPGeV8rI_ZlTA80P5VLQ-5zkzVv4";
    public static final String VIDEO_URL  = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";
    TextView tvTitle;
    TextView tvDescription;
    RatingBar ratingBar;
    int id;
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
        id = movie.getId();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEO_URL, id), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() == 0) { return; }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailActivity", youtubeKey);
                    initializeYoutube(youtubeKey);
                } catch (JSONException e) {
                    Log.e("DetailActivity", e.toString());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
            Log.d("DetailActivity", "Unable to get videos");
            }
        });
    }

    private void initializeYoutube(String youtubeKey) {
        youtubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "success");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "failure");
            }
        });
}
}