package com.example.moviesapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviesapp.R;
import com.squareup.picasso.Picasso;


public class MovieDetailsFragment extends Fragment {

    TextView name, overview, releasedate, rate, language;
    ImageView poster, movieImage;
    String movieName, movieDate, movieOverview, moviImage, moviewPoster, moviewLanguage, movieRatings, movieBackdoorpath;
    String posterPath = "https://image.tmdb.org/t/p/w500/";

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieName = getArguments().getString("name");

        movieDate = getArguments().getString("date");
        movieOverview = getArguments().getString("overview");
        moviewLanguage = getArguments().getString("language");
        moviewPoster = getArguments().getString("poster");
        movieRatings = getArguments().getString("ratings");
        movieBackdoorpath = getArguments().getString("backdoorpath");
        Log.i("data", movieDate);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        name = view.findViewById(R.id.moviename);
        releasedate = view.findViewById(R.id.moviedate);
        overview = view.findViewById(R.id.movie_description);
        rate = view.findViewById(R.id.moview_rating);
        language = view.findViewById(R.id.movie_language);
        movieImage = view.findViewById(R.id.movieimage);

        name.setText(movieName);
        releasedate.setText(movieDate);
        overview.setText(movieOverview);
        rate.setText(movieRatings);
        language.setText(moviewLanguage);

        Picasso.get().load(posterPath + movieBackdoorpath).into(movieImage);


        return view;
    }
}