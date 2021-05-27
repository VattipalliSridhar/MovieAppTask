package com.example.moviesapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.model.Result;
import com.example.moviesapp.view.MainActivity;
import com.example.moviesapp.view.MovieDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    Context context;
    ArrayList<Result> movies;

    public MoviesAdapter(Context context, ArrayList<Result> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {


        holder.tvMovieName.setText(movies.get(position).getTitle());
        holder.tvDate.setText(movies.get(position).getReleaseDate());
        holder.tvDescription.setText(movies.get(position).getOverview());

        double rate = movies.get(position).getVoteAverage();
        String ratingsValue = Double.toString(rate);
        holder.ratings.setText(ratingsValue);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        CardView movieItem;
        TextView tvMovieName, tvDate, tvDescription, ratings;
        ImageView ivNews;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMovieName = itemView.findViewById(R.id.movie_name);
            tvDate = itemView.findViewById(R.id.movie_date);
            tvDescription = itemView.findViewById(R.id.movie_description);
            ratings = itemView.findViewById(R.id.movie_ratings);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        Result clickedItem = movies.get(pos);

                        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
                        Bundle mBundle = new Bundle();
                        mBundle.putString("name", clickedItem.getTitle());
                        mBundle.putString("date", clickedItem.getReleaseDate());

                        mBundle.putString("overview", clickedItem.getOverview());
                        mBundle.putString("ratings", String.valueOf(clickedItem.getVoteAverage()));
                        mBundle.putString("language", clickedItem.getOriginalLanguage());
                        mBundle.putString("backdoorpath", clickedItem.getBackdropPath());
                        mBundle.putString("poster", clickedItem.getPosterPath());
                        movieDetailsFragment.setArguments(mBundle);
                        switchContent(R.id.list_movies_fragment, movieDetailsFragment);

                    }

                }
            });


        }

        public void switchContent(int id, Fragment fragment) {
            if (context == null)
                return;
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;
                Fragment frag = fragment;
                mainActivity.switchContent(id, frag);
            }

        }
    }
}

