package com.kathleenwang.flixster.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kathleenwang.flixster.R;

import org.w3c.dom.Text;

public class MovieAdapter {
    public class ViewHolder extends RecyclerView.ViewHolder {
        // define the elements in the view
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivPoster;

        // each view is a new item_movie element
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // in the constructor, we can define where the elements are coming from
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }
    }
}
