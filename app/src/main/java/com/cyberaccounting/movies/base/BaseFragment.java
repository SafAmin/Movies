package com.cyberaccounting.movies.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cyberaccounting.movies.R;

/**
 * A Parent class for all fragments.
 */
public class BaseFragment extends Fragment implements BaseView {

    private ProgressDialog loadingIndicator;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }

    @Override
    public void showLoadingIndicator() {
        loadingIndicator = new ProgressDialog(getActivity());
        loadingIndicator.setMessage(getString(R.string.progress_dialog_message));
        loadingIndicator.show();
        loadingIndicator.setCancelable(false);
        loadingIndicator.setCanceledOnTouchOutside(false);
    }

    @Override
    public void hideLoadingIndicator() {
        if (loadingIndicator != null) {
            loadingIndicator.dismiss();
        }
    }

    @Override
    public void handleOfflineError() {

    }
}
