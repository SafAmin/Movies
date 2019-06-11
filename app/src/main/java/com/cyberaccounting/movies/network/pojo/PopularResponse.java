package com.cyberaccounting.movies.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PopularResponse implements Parcelable {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<PopularResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;

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

	public void setResults(List<PopularResultsItem> results){
		this.results = results;
	}

	public List<PopularResultsItem> getResults(){
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
		dest.writeInt(this.page);
		dest.writeInt(this.totalPages);
		dest.writeList(this.results);
		dest.writeInt(this.totalResults);
	}

	public PopularResponse() {
	}

	protected PopularResponse(Parcel in) {
		this.page = in.readInt();
		this.totalPages = in.readInt();
		this.results = new ArrayList<PopularResultsItem>();
		in.readList(this.results, PopularResultsItem.class.getClassLoader());
		this.totalResults = in.readInt();
	}

	public static final Parcelable.Creator<PopularResponse> CREATOR = new Parcelable.Creator<PopularResponse>() {
		@Override
		public PopularResponse createFromParcel(Parcel source) {
			return new PopularResponse(source);
		}

		@Override
		public PopularResponse[] newArray(int size) {
			return new PopularResponse[size];
		}
	};
}