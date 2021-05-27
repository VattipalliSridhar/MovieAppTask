package com.example.moviesapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.model.MoviesResponse;
import com.example.moviesapp.repository.MovieRepository;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<MoviesResponse> mutableLiveData;
    private MovieRepository movieRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getMovies("popularity.desc", "e330784346c107fd1d098ebf2c61c9fd");

    }

    public LiveData<MoviesResponse> getMoviesRepository() {
        return mutableLiveData;
    }

}
