package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;

import adapters.RecyclerViewAdapter;
import entities.Movie;
import entities.ResultMovie;
import io.reactivex.subjects.PublishSubject;
import viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movies;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LiveData<ResultMovie> liveData;
    private MainViewModel model;
    private SearchView searchView;
    private PublishSubject<String> subject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setRecyclerView();
        setSearchView();
        
        model = ViewModelProviders.of(this).get(MainViewModel.class);
        updateAdapter();
    }

    private void updateAdapter() {
        liveData = model.getMovies();
        liveData.observe(this, resultMovie -> {
            this.movies.clear();
            this.movies.addAll(resultMovie.getResults());
            recyclerViewAdapter.notifyDataSetChanged();
        });
    }


    private void setRecyclerView() {
        movies = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setSearchView() {
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });
    }

    public static void log(String text) {
        Log.d("WTF", text);
    }
}
