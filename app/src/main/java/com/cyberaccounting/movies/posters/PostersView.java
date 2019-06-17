package com.cyberaccounting.movies.posters;

import com.cyberaccounting.movies.base.BaseView;
import com.cyberaccounting.movies.network.pojo.MovieDetails;

import java.util.List;

/**
 * Created by Safa Amin on 14/06/2019.
 */
public interface PostersView extends BaseView {

    void setTotalPagesNum(int totalPages);

    void invalidateViewWithMovies(List<MovieDetails> movieDetailsList);

    void invalidateViewWithMoreMovies(List<MovieDetails> movieDetailsList);

}
