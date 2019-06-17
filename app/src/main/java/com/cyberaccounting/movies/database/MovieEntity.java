package com.cyberaccounting.movies.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Safa Amin on 14/06/2019.
 */
@Entity(tableName = "movies")
public class MovieEntity {

    @PrimaryKey
    private int id;
    private String poster;
    private String name;
    private String releaseDate;
    private String rating;
    private String overview;

    public MovieEntity(int id, String poster, String name, String releaseDate,
                       String rating, String overview) {
        setId(id);
        setPoster(poster);
        setName(name);
        setReleaseDate(releaseDate);
        setRating(rating);
        setOverview(overview);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String movieName) {
        this.name = movieName;
    }

    public String getName() {
        return name;
    }

    public void setPoster(String moviePoster) {
        this.poster = moviePoster;
    }

    public String getPoster() {
        return poster;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }
}
