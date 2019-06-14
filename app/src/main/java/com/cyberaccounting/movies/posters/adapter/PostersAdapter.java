package com.cyberaccounting.movies.posters.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * <p>
 * Created by Safa Amin on 13/06/2019.
 */
public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieDetails> movieDetailsList;

    PostersAdapter(Context context, List<MovieDetails> movieDetailsList) {
        this.mContext = context;
        this.movieDetailsList = movieDetailsList;
    }

    @Override
    @NonNull
    public PostersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.poster_item_view, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieDetails model = movieDetailsList.get(holder.getAdapterPosition());
        holder.bindData(model);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

        void bindData(final MovieDetails model) {
            Glide.with(mContext).load(moviePosterBaseURL +
                    model.getMoviePoster()).into(ivMoviePoster);
            tvMovieName.setText(model.getMovieName());
        }
    }

    @Override
    public int getItemCount() {
        return movieDetailsList.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
