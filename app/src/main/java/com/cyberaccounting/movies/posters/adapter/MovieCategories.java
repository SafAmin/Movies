package com.cyberaccounting.movies.posters.adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Safa Amin on 14/06/2019.
 */
@IntDef({MovieCategories.POPULAR, MovieCategories.TOP_RATED, MovieCategories.UPCOMING})
@Retention(RetentionPolicy.SOURCE)
public @interface MovieCategories {
    int POPULAR = 0;
    int TOP_RATED = 1;
    int UPCOMING = 2;
}
