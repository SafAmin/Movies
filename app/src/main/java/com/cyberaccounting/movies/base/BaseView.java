package com.cyberaccounting.movies.base;

/**
 * Created by Safa Amin on 13/06/2019.
 */
public interface BaseView {

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void handleOfflineError();
}
