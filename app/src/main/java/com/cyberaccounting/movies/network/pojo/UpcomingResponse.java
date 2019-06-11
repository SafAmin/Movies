package com.cyberaccounting.movies.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UpcomingResponse implements Parcelable {

	@SerializedName("dates")
	private UpcomingMoviesDates dates;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<UpcomingResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;

	public void setDates(UpcomingMoviesDates dates){
		this.dates = dates;
	}

	public UpcomingMoviesDates getDates(){
		return dates;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<UpcomingResultsItem> results){
		this.results = results;
	}

	public List<UpcomingResultsItem> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.dates, flags);
		dest.writeInt(this.page);
		dest.writeInt(this.totalPages);
		dest.writeList(this.results);
		dest.writeInt(this.totalResults);
	}

	public UpcomingResponse() {
	}

	protected UpcomingResponse(Parcel in) {
		this.dates = in.readParcelable(UpcomingMoviesDates.class.getClassLoader());
		this.page = in.readInt();
		this.totalPages = in.readInt();
		this.results = new ArrayList<UpcomingResultsItem>();
		in.readList(this.results, UpcomingResultsItem.class.getClassLoader());
		this.totalResults = in.readInt();
	}

	public static final Parcelable.Creator<UpcomingResponse> CREATOR = new Parcelable.Creator<UpcomingResponse>() {
		@Override
		public UpcomingResponse createFromParcel(Parcel source) {
			return new UpcomingResponse(source);
		}

		@Override
		public UpcomingResponse[] newArray(int size) {
			return new UpcomingResponse[size];
		}
	};
}