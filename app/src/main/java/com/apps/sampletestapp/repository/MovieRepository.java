package com.apps.sampletestapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.apps.sampletestapp.model.MovieModel;
import com.apps.sampletestapp.view.api.RetrofitApis;
import com.apps.sampletestapp.view.api.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    private final MutableLiveData<List<MovieModel.Result>> mutableLiveData;
    private final MutableLiveData<String> stringMutableLiveData;
    private final RetrofitApis retrofitApis;

    public MovieRepository() {

        mutableLiveData = new MutableLiveData<>();
        stringMutableLiveData = new MutableLiveData<>();
        retrofitApis = RetrofitApis.Factory.create();

    }

    @SuppressLint("CheckResult")
    public void getMovieModel() {

        Observable<MovieModel> call = retrofitApis.getPopularMovies(Utils.API_KEY);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<MovieModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe", "onSubscribe");
                    }

                    @Override
                    public void onNext(MovieModel value) {
                        Log.e("onNext", "onNext");
                        mutableLiveData.setValue(value.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete", "onComplete");
                    }
                });
    }


    public MutableLiveData<List<MovieModel.Result>> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<String> getStringMutableLiveData() {
        if (stringMutableLiveData == null)
            stringMutableLiveData.setValue("");
        return stringMutableLiveData;
    }
}
