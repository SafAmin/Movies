package com.cyberaccounting.movies.posters;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyberaccounting.movies.R;
import com.cyberaccounting.movies.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This fragment shows a vertical list of popular, top rated or upcoming movies posters.
 * and allow the user to tap a movie poster and transition to a details screen.
 * <p>
 */
public class PostersFragment extends BaseFragment implements PostersView {

    @BindView(R.id.rv_movies_posters)
    RecyclerView rvMoviesPosters;

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
        // presenter.executeGetPopularMoviesService();
    }
}
