package com.kathleenwang.flixster.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    // encapsulate idea of a movie
    String posterPath;
    String title;
    String overview;

    @Override
    public String toString() {
        return "Movie:" +
                "posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'';
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        // takes in a JSONObject called jsonObject - we will be parsing the info from this object
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }
    // function called fromJsonArray returns a List<Movie> and takes in a JSONArray called movieJsonArray
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i =0;i<movieJsonArray.length();i++) {
            // add a new Movie for each element in the array and add to movies list
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }
    public static List<Movie> createMovies(Integer nums)  {
        List<Movie> movies = new ArrayList<>();
        for (int i =0;i<nums;i++) {
            try {
                movies.add(new Movie(toJson()));
            } catch (JSONException e) {
               Log.d("Create movies", String.valueOf(e));
            }

        }
        return movies;
    }

    private static JSONObject toJson() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("poster_path", "test");
        jo.put("title", "test");
        jo.put("overview", "test");

        return jo;
    }


    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
