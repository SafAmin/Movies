package com.cyberaccounting.movies.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails implements Parcelable {

    private int movieId;
    private String moviePoster;
    private String movieName;
    private String movieReleaseDate;
    private String movieRating;
    private String movieOverview;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movieId);
        dest.writeString(this.moviePoster);
        dest.writeString(this.movieName);
        dest.writeString(this.movieReleaseDate);
        dest.writeString(this.movieRating);
        dest.writeString(this.movieOverview);
    }

    public MovieDetails() {
    }

    protected MovieDetails(Parcel in) {
        this.movieId = in.readInt();
        this.moviePoster = in.readString();
        this.movieName = in.readString();
        this.movieReleaseDate = in.readString();
        this.movieRating = in.readString();
        this.movieOverview = in.readString();
    }

    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel source) {
            return new MovieDetails(source);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };

    /**
     * This method responsible for map API response to view model of type {@link MovieDetails} which used to
     * invalidate views with data.
     *
     * @param popularMovies API response
     */
    public List<MovieDetails> generatePopularMoviesDataList(PopularResponse popularMovies) {
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        MovieDetails movieDetails;
        PopularResultsItem popularResultsItem;

        for (int i = 0; i < popularMovies.getResults().size(); i++) {
            popularResultsItem = popularMovies.getResults().get(i);
            movieDetails = new MovieDetails();
            movieDetails.setMovieId(popularResultsItem.getId());
            movieDetails.setMoviePoster(popularResultsItem.getPosterPath());
            movieDetails.setMovieName(popularResultsItem.getOriginalTitle());
            movieDetails.setMovieReleaseDate(popularResultsItem.getReleaseDate());
            movieDetails.setMovieRating(String.valueOf(popularResultsItem.getVoteAverage()));
            movieDetails.setMovieOverview(popularResultsItem.getOverview());
            movieDetailsList.add(i, movieDetails);
        }

        return movieDetailsList;
    }
}
