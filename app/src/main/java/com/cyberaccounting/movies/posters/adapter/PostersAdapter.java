package com.cyberaccounting.movies.posters.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cyberaccounting.movies.R;
import com.cyberaccounting.movies.network.pojo.MovieDetails;
import com.cyberaccounting.movies.posters.PostersFragment;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This Adapter responsible for making a View for each item in the movie poster RecyclerView
 * within {@link PostersFragment}
 * <p>
 * Created by Safa Amin on 13/06/2019.
 */

public class PostersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_POSTERS = 1;
    private boolean isLoaderVisible = false;
    private Context mContext;
    private List<MovieDetails> movieDetailsList;

    public PostersAdapter(Context context, List<MovieDetails> movieDetailsList) {
        this.mContext = context;
        this.movieDetailsList = movieDetailsList;
    }

    @Override
    @NonNull
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_POSTERS:
                return new ViewHolder(inflater.inflate(R.layout.poster_item_view, parent, false));
            case VIEW_TYPE_LOADING:
                return new FooterHolder(inflater.inflate(R.layout.loading_item_view, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(holder.getAdapterPosition());
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == movieDetailsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_POSTERS;
        } else {
            return VIEW_TYPE_POSTERS;
        }
    }

    @Override
    public int getItemCount() {
        return movieDetailsList == null ? 0 : movieDetailsList.size();
    }

    private void add(MovieDetails movieDetails) {
        movieDetailsList.add(movieDetails);
        notifyItemInserted(movieDetailsList.size() - 1);
    }

    public void addAll(List<MovieDetails> movieDetailsList) {
        for (MovieDetails movieDetails : movieDetailsList) {
            add(movieDetails);
        }
    }

    private void remove(MovieDetails movieDetails) {
        int position = movieDetailsList.indexOf(movieDetails);
        if (position > -1) {
            movieDetailsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new MovieDetails());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = movieDetailsList.size() - 1;
        MovieDetails item = getItem(position);
        if (item != null) {
            movieDetailsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private MovieDetails getItem(int position) {
        return movieDetailsList.get(position);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_movie_poster)
        ImageView ivMoviePoster;
        @BindView(R.id.tv_movie_name)
        TextView tvMovieName;
        @BindString(R.string.movie_poster_base_url)
        String moviePosterBaseURL;

        ViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            MovieDetails item = movieDetailsList.get(position);

            Glide.with(mContext).load(moviePosterBaseURL +
                    item.getMoviePoster()).into(ivMoviePoster);
            tvMovieName.setText(item.getMovieName());
        }
    }

    public class FooterHolder extends BaseViewHolder {

        @BindView(R.id.progressbar_posters)
        ProgressBar progressBarPosters;

        FooterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }
}
