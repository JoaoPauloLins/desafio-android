package com.example.jplo.cinema.movies.view;

import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.movies.model.Movie;
import com.example.jplo.cinema.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter{

    private List<Movie> movies = new ArrayList<>();
    private int position = movies.size();

    private static final int VIEW_MOVIE = 1;
    private static final int VIEW_LOAD = 0;

    @Override
    public int getItemViewType(int position) {
        return movies.get(position) != null ? VIEW_MOVIE : VIEW_LOAD;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        View view;

        if (viewType == VIEW_MOVIE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_profile, parent, false);
            viewHolder = new MoviesViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_progress_bar, parent, false);
            viewHolder = new LoadViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MoviesViewHolder) {
            MoviesViewHolder moviesViewHolder = (MoviesViewHolder) holder;
            Movie movie = movies.get(position);
            moviesViewHolder.cardView.setTag(movie.getId());
            moviesViewHolder.movieName.setText(movie.getName());
            GlideApp.with(moviesViewHolder.itemView)
                    .load(movie.getUrl())
                    .override(600,200)
                    .into(moviesViewHolder.moviePhoto);
        }
        else {
            LoadViewHolder loadViewHolder = (LoadViewHolder) holder;
            loadViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovies(List<Movie> movies){
        position = this.movies.size();
        this.movies.addAll(movies);
        notifyItemRangeInserted(position, movies.size());
    }

    public void addLoad(){
        position = movies.size();
        if(position == 0 || movies.get(position-1) != null){
            movies.add(null);
            notifyItemRangeInserted(position, 1);
        }
    }

    public void removeLoad(){
        movies.remove(null);
        notifyDataSetChanged();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_card_view)
        CardView cardView;
        @BindView(R.id.movie_name)
        TextView movieName;
        @BindView(R.id.movie_photo)
        ImageView moviePhoto;

        MoviesViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class LoadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_load)
        ProgressBar progressBar;

        LoadViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
