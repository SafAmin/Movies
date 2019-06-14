package com.cyberaccounting.movies.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UpcomingMoviesDates implements Parcelable {

    @SerializedName("maximum")
    private String maximum;

    @SerializedName("minimum")
    private String minimum;

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMinimum() {
        return minimum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.maximum);
        dest.writeString(this.minimum);
    }

    public UpcomingMoviesDates() {
    }

    protected UpcomingMoviesDates(Parcel in) {
        this.maximum = in.readString();
        this.minimum = in.readString();
    }

    public static final Parcelable.Creator<UpcomingMoviesDates> CREATOR = new Parcelable.Creator<UpcomingMoviesDates>() {
        @Override
        public UpcomingMoviesDates createFromParcel(Parcel source) {
            return new UpcomingMoviesDates(source);
        }

        @Override
        public UpcomingMoviesDates[] newArray(int size) {
            return new UpcomingMoviesDates[size];
        }
    };
}