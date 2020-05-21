package com.sachin.movieapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.sachin.movieapp.db.FavoriteMovie;
import com.sachin.movieapp.db.MovieDb;

import java.util.List;


public class MyViewModel extends AndroidViewModel {

    private static final String TAG = MyViewModel.class.getSimpleName();

    private LiveData<List<FavoriteMovie>> movies;

    public MyViewModel(Application application) {
        super(application);
        MovieDb database = MovieDb.getInstance(this.getApplication());
        movies = database.movieDao().loadAllMovies();
    }

    public LiveData<List<FavoriteMovie>> getMovies() {
        return movies;
    }
}
