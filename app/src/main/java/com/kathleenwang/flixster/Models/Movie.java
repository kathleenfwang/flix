package com.kathleenwang.flixster.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    // encapsulate idea of a movie
    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject jsonObject) throws JSONException {
        // takes in a JSONObject called jsonObject - we will be parsing the info from this object
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }
}
