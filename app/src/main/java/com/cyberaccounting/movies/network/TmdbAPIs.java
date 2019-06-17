package com.cyberaccounting.movies.network;

import com.cyberaccounting.movies.network.pojo.PopularResponse;
import com.cyberaccounting.movies.network.pojo.TopRatedResponse;
import com.cyberaccounting.movies.network.pojo.UpcomingResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Safa Amin on 11/06/2019.
 */
public interface TmdbAPIs {

    @GET("movie/popular")
    Call<PopularResponse> getPopularMovies(@QueryMap Map<String, String> options);

    @GET("movie/top_rated")
    Call<TopRatedResponse> getTopRatedMovies(@Query("api_key") String key);

    @GET("movie/upcoming")
    Call<UpcomingResponse> getUpcomingMovies(@Query("api_key") String key);
}
