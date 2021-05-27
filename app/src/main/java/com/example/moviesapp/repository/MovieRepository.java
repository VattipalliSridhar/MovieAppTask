package com.example.moviesapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository movieRepository;

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private MovieApi movieApi;

    public MovieRepository() {
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<MoviesResponse> getMovies(String sortBy, String key) {
        MutableLiveData<MoviesResponse> movieData = new MutableLiveData<>();
        movieApi.getMoviesList(sortBy, key).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    movieData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                movieData.setValue(null);
            }
        });
        return movieData;
    }

}
