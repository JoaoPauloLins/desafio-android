package com.example.jplo.cinema.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.model.Movie;
import com.example.jplo.cinema.module.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private List<Movie> movies = new ArrayList<>();

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_profile, parent, false);
        MoviesViewHolder moviesViewHolder = new MoviesViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.cardView.setTag(movie.getId());
        holder.movieName.setText(movie.getName());
        GlideApp.with(holder.itemView)
                .load(movie.getUrl())
                .override(600,200)
                .into(holder.moviePhoto);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> moreMovies){
        int position = movies.size();
        movies.addAll(moreMovies);
        notifyItemRangeInserted(position, moreMovies.size());
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
}
