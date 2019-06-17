package com.cyberaccounting.movies.posters;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyberaccounting.movies.R;
import com.cyberaccounting.movies.base.BaseFragment;
import com.cyberaccounting.movies.network.pojo.MovieDetails;
import com.cyberaccounting.movies.posters.adapter.MovieCategories;
import com.cyberaccounting.movies.posters.adapter.PaginationScrollListener;
import com.cyberaccounting.movies.posters.adapter.PostersAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This fragment shows a vertical list of popular, top rated or upcoming movies posters.
 * and allow the user to tap a movie poster and transition to a details screen.
 * <p>
 */
public class PostersFragment extends BaseFragment implements PostersView,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rv_movies_posters)
    RecyclerView rvMoviesPosters;

    private static final int PAGE_START = 1;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    private int TOTAL_PAGES;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;

    private PostersAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private PostersPresenter presenter;
    private Unbinder unbinder;

    /**
     * Use this factory method to create a new instance of this fragment.
     */
    public static PostersFragment newInstance() {
        return new PostersFragment();
    }

    /**
     * Inflate the layout for this fragment
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posters, container, false);

        unbinder = ButterKnife.bind(this, view);
        presenter = new PostersPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        swipeRefresh.setOnRefreshListener(this);
        int mNoOfColumns = calculateNoOfColumns(getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMoviesPosters.setLayoutManager(linearLayoutManager);
        rvMoviesPosters.setHasFixedSize(true);
        /* rvMoviesPosters.setNestedScrollingEnabled(false);*/
        rvMoviesPosters.setItemAnimator(new DefaultItemAnimator());
        rvMoviesPosters.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                presenter.initServiceAndLoadMovies(MovieCategories.POPULAR, currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        presenter.initServiceAndLoadMovies(MovieCategories.POPULAR, currentPage);
    }

    private int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }

    @Override
    public void setTotalPagesNum(int totalPages) {
        TOTAL_PAGES = totalPages;
    }

    @Override
    public void invalidateViewWithPopularMovies(List<MovieDetails> movieDetailsList) {
        if (adapter == null) {
            adapter = new PostersAdapter(getContext(), movieDetailsList);
        }
        rvMoviesPosters.setAdapter(adapter);
        if (currentPage != PAGE_START) adapter.removeLoading();
        adapter.addAll(movieDetailsList);
        swipeRefresh.setRefreshing(false);
        if (currentPage < TOTAL_PAGES) adapter.addLoading();
        else isLastPage = true;
        isLoading = false;
    }

    @Override
    public void onRefresh() {
        //  itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        presenter.initServiceAndLoadMovies(MovieCategories.POPULAR, currentPage);
    }
}
