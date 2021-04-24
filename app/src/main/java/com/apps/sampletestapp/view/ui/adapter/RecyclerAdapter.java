package com.apps.sampletestapp.view.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.sampletestapp.R;
import com.apps.sampletestapp.databinding.AdapterMovieLayoutBinding;
import com.apps.sampletestapp.model.MovieModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolders> {

    private Context context;
    private LayoutInflater inflater;
    private List<MovieModel.Result> list;

    public RecyclerAdapter(Context mainActivity, List<MovieModel.Result> movieResultList) {

        context = mainActivity;
        this.inflater = LayoutInflater.from(context);
        list = movieResultList;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterMovieLayoutBinding view = AdapterMovieLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        String poster = "https://image.tmdb.org/t/p/w500" + list.get(position).getPosterPath();
        Glide.with(context)
                .load(poster)
                .placeholder(R.drawable.load)
                .into(holder.adapterBinding.thumbnail);
        holder.adapterBinding.title.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder {
        private AdapterMovieLayoutBinding adapterBinding;
        public ViewHolders(AdapterMovieLayoutBinding itemView) {
            super(itemView.getRoot());
            this.adapterBinding = itemView;
        }
    }
}
