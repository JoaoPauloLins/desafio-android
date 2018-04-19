package com.example.jplo.cinemamvvm.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold;
    private int currentPage;
    private boolean loading = true;
    private RecyclerView.LayoutManager layoutManager;

    EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.visibleThreshold = 4;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int totalItemCount = linearLayoutManager.getItemCount();
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public void setLoaded(){
        loading = false;
    }

    public abstract void onLoadMore(int page);
}
