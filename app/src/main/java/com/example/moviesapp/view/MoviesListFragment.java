package com.example.moviesapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesapp.R;
import com.example.moviesapp.model.Result;
import com.example.moviesapp.view.adapter.MoviesAdapter;
import com.example.moviesapp.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesListFragment extends Fragment {

    ArrayList<Result> moviesArrayList = new ArrayList<>();
    MoviesAdapter moviesAdapter;

    MoviesViewModel moviesViewModel;
    RecyclerView recyclerViewMovies;

    private View fragmentView;

    public MoviesListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (fragmentView != null) {
            return fragmentView;
        }

        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        recyclerViewMovies = view.findViewById(R.id.rvMovies);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMovies.setLayoutManager(linearLayoutManager);
        recyclerViewMovies.setHasFixedSize(true);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();
        moviesViewModel.getMoviesRepository().observe(getActivity(), newsResponse -> {
            List<Result> moviesResult = newsResponse.getResults();
            if (moviesResult != null) {
                moviesArrayList.addAll(moviesResult);
                moviesAdapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();

        fragmentView = view;

        return view;
    }

    private void setupRecyclerView() {

        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(getActivity(), moviesArrayList);
            recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewMovies.setAdapter(moviesAdapter);
            recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
            recyclerViewMovies.setNestedScrollingEnabled(true);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (fragmentView!=null){
            fragmentView = null;
        }
    }
}