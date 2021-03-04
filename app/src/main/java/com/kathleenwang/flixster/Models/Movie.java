package com.kathleenwang.flixster.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Movie {
    // encapsulate idea of a movie
    String posterPath;
    String title;
    String overview;
    double rating;
    int id;

    // empty constructor for Parceler
    public Movie() {}
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
        id = jsonObject.getInt("id");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
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
    public static List<Movie> createMovies(Integer num)  {
        List<Movie> movies = new ArrayList<>();
        for (int i =0;i<num;i++) {
            try {
                movies.add(new Movie(toJson()));
            } catch (JSONException e) {
               Log.d("Create movies", String.valueOf(e));
            }
        }
        return movies;
    }

    private static JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("poster_path", "test");
        obj.put("title", "test");
        obj.put("overview", "test");
        return obj;
    }


    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
    public double getRating() {return rating;}
}
