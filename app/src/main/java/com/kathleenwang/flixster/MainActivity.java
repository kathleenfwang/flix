package com.kathleenwang.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.kathleenwang.flixster.Models.Movie;
import com.kathleenwang.flixster.adapters.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public static final String TAG = "MainActivity";
    // turn movies into a new ArrayList so we can populate with data we get back
    List<Movie> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
//        movies = Movie.createMovies(20);
        // Create the adapter
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        //set adapter to recycler view
        rvMovies.setAdapter(movieAdapter);
        // set layout manager on recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    // we dont want movies to be creating new lists each time, so we add all the data into our movies list
                    movies.addAll(Movie.fromJsonArray(results));
                    // notivfy the data set changed after setting movies to equal the results
                    movieAdapter.notifyDataSetChanged();
                    Log.d(TAG, movies.toString());
                } catch (JSONException e) {
                    Log.d(TAG, "JSON exception",e);
                }
            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}