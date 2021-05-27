package com.example.moviesapp.repository;

import com.example.moviesapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie")
    Call<MoviesResponse> getMoviesList(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);
}
