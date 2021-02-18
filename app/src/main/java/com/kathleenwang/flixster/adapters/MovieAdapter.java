package com.kathleenwang.flixster.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kathleenwang.flixster.Models.Movie;
import com.kathleenwang.flixster.R;

import org.w3c.dom.Text;

import java.util.List;

// the MovieAdapter extends RecyclerView which takes in the ViewHolder  class we created in this class
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    // create constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // onCreateViewHoolder, inflate a layout from XML and return the new view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Movie Adapter", "onCreateViewHolder");
       View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
       ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }
    // onBindViewHolder, populate data into the item through holder, we take the position and put into the View in Viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("Movie Adapter", "onBindViewHolder");
    // get the movie at the passed in position
        Movie movie = movies.get(position);
        // bind the movie data into the view holder
        // the bind method is from the ViewHolder class (because holder is ViewHolder)
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // define the elements in the view
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivPoster;

        // each view is a new item_movie element
        // constructor to define our view elements
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("Movie Adapter", "View Holder");
            // in the constructor, we can define where the elements are coming from
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }
        // binding the individual elements with the data
        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvDescription.setText(movie.getOverview());
            // use glide library to get image
            Glide.with(context).load(movie.getPosterPath());
        }
    }
}
