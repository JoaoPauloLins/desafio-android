package com.example.jplo.cinema.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdaptor extends RecyclerView.Adapter<MoviesAdaptor.MoviesViewHolder>{

    List<Movie> movies;

    MoviesAdaptor(List<Movie> movies){
        this.movies = movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_profile, parent, false);
        MoviesViewHolder moviesViewHolder = new MoviesViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.movieName.setText(movies.get(position).getName());
        GlideApp.with(holder.itemView)
                .load(movies.get(position).getUrl())
                .override(600,200)
                .into(holder.moviePhoto);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder {
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
