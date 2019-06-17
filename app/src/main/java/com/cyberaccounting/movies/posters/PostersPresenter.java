package com.cyberaccounting.movies.posters;

import android.support.annotation.NonNull;

import com.cyberaccounting.movies.BuildConfig;
import com.cyberaccounting.movies.base.BasePresenter;
import com.cyberaccounting.movies.network.MoviesClient;
import com.cyberaccounting.movies.network.TmdbAPIs;
import com.cyberaccounting.movies.network.pojo.MovieDetails;
import com.cyberaccounting.movies.network.pojo.PopularResponse;
import com.cyberaccounting.movies.posters.adapter.MovieCategories;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Safa Amin on 14/06/2019.
 */
public class PostersPresenter extends BasePresenter<PostersView> {

    private TmdbAPIs service;
    private int pageNum;

    PostersPresenter(PostersView view) {
        super(view);
    }

    void initServiceAndLoadMovies(int movieCategory, int page) {
        pageNum = page;
        service = MoviesClient.getRetrofitInstance().create(TmdbAPIs.class);
        switch (movieCategory) {
            case MovieCategories.POPULAR:
                fetchPopularMovies();
                break;
            case MovieCategories.TOP_RATED:

                break;
            case MovieCategories.UPCOMING:

                break;
        }
    }

    public void fetchPopularMovies() {
        getView().showLoadingIndicator();
        Call<PopularResponse> call = service.getPopularMovies(getAPIParams());
        call.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(@NonNull Call<PopularResponse> call,
                                   @NonNull Response<PopularResponse> response) {
                getView().hideLoadingIndicator();
                getView().setTotalPagesNum(response.body().getTotalPages());
                mapPopularMoviesToMovieDetailModel(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PopularResponse> call, @NonNull Throwable t) {
                getView().hideLoadingIndicator();
            }
        });
    }

    private HashMap<String, String> getAPIParams() {
        HashMap<String, String> options = new HashMap<>();
        options.put("api_key", BuildConfig.MoviesDBAPIKey);
        options.put("language", "en-US");
        options.put("page", String.valueOf(pageNum));

        return options;
    }

    private void mapPopularMoviesToMovieDetailModel(PopularResponse popularMovies) {
        List<MovieDetails> movieDetailsList = new MovieDetails().generatePopularMoviesDataList(popularMovies);
        getView().invalidateViewWithPopularMovies(movieDetailsList);
    }

}
