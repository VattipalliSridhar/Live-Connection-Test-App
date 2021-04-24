package com.apps.sampletestapp.view.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.apps.sampletestapp.R;
import com.apps.sampletestapp.databinding.ActivityMainBinding;
import com.apps.sampletestapp.model.MovieModel;
import com.apps.sampletestapp.view.classes.BaseActivity;
import com.apps.sampletestapp.view.ui.adapter.RecyclerAdapter;
import com.apps.sampletestapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    private List<MovieModel.Result> movieResultList = new ArrayList<>();
    private MovieViewModel movieViewModel;
    private RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerViewData.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerViewData.setHasFixedSize(true);
        adapter = new RecyclerAdapter(this, movieResultList);
        binding.recyclerViewData.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getSMovieList().observe(this, new Observer<List<MovieModel.Result>>() {
            @Override
            public void onChanged(List<MovieModel.Result> data) {
                movieResultList.clear();
                if (data != null && data.size() > 0) {
                    movieResultList.addAll(data);
                }
                adapter.notifyDataSetChanged();

                dismissDialog();
            }
        });

        movieViewModel.getMessageToShow().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showToastMessage(s);
                dismissDialog();
            }
        });

        if (isNetworkAvailable()) {
            getMovieList();
        } else {
            showToastMessage("Please Connect Internet............");
        }


    }

    private void getMovieList() {
        showDialog();
        movieViewModel.getMovieModel();
    }
}