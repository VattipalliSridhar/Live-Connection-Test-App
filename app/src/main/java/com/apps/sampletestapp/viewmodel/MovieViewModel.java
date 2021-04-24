package com.apps.sampletestapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.apps.sampletestapp.model.MovieModel;
import com.apps.sampletestapp.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {



    private final LiveData<List<MovieModel.Result>> listLiveData;
    private final LiveData<String> messageToShow ;
    private MovieRepository MovieRepository;



    public MovieViewModel() {
        MovieRepository = new MovieRepository();
        listLiveData = MovieRepository.getMutableLiveData();
        messageToShow = MovieRepository.getStringMutableLiveData();
    }

    public void getMovieModel() {

        MovieRepository.getMovieModel();

    }

    public LiveData<List<MovieModel.Result>> getSMovieList() {
        return listLiveData;
    }

    public LiveData<String> getMessageToShow() {
        return messageToShow;
    }

}
