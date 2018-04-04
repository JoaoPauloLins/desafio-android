package com.example.jplo.cinema.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold;
    private int currentPage;
    private int previousTotalItemCount;
    private boolean loading = true;
    private int startingPageIndex;

    private RecyclerView.LayoutManager layoutManager;

    EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager,
                                      int startingPageIndex,
                                      int visibleThreshold) {
        this.layoutManager = layoutManager;
        this.startingPageIndex = startingPageIndex;
        this.visibleThreshold = visibleThreshold;
    }

    EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.startingPageIndex = 0;
        this.visibleThreshold = 5;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition;
        int totalItemCount = layoutManager.getItemCount();

        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            loading = true;
        }
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = lastVisibleItemPositions[0];
        for (int lvip : lastVisibleItemPositions) {
            if (lvip > maxSize) {
                maxSize = lvip;
            }
        }
        return maxSize;
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
