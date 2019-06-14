package com.cyberaccounting.movies.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TopRatedResponse implements Parcelable {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<TopRatedResultsItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setResults(List<TopRatedResultsItem> results) {
        this.results = results;
    }

    public List<TopRatedResultsItem> getResults() {
        return results;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalPages);
        dest.writeList(this.results);
        dest.writeInt(this.totalResults);
    }

    public TopRatedResponse() {
    }

    protected TopRatedResponse(Parcel in) {
        this.page = in.readInt();
        this.totalPages = in.readInt();
        this.results = new ArrayList<TopRatedResultsItem>();
        in.readList(this.results, TopRatedResultsItem.class.getClassLoader());
        this.totalResults = in.readInt();
    }

    public static final Parcelable.Creator<TopRatedResponse> CREATOR = new Parcelable.Creator<TopRatedResponse>() {
        @Override
        public TopRatedResponse createFromParcel(Parcel source) {
            return new TopRatedResponse(source);
        }

        @Override
        public TopRatedResponse[] newArray(int size) {
            return new TopRatedResponse[size];
        }
    };
}