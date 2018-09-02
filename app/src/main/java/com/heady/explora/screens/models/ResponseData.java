package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yashthakur on 26/08/18.
 */

public class ResponseData implements Parcelable {

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Ranking> rankings = new ArrayList<>();

    public ResponseData() {
    }

    public ResponseData(ArrayList<Category> categories, ArrayList<Ranking> rankings) {
        this.categories = categories;
        this.rankings = rankings;
    }

    protected ResponseData(Parcel in) {
        categories = in.createTypedArrayList(Category.CREATOR);
        rankings = in.createTypedArrayList(Ranking.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(categories);
        dest.writeTypedList(rankings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseData> CREATOR = new Creator<ResponseData>() {
        @Override
        public ResponseData createFromParcel(Parcel in) {
            return new ResponseData(in);
        }

        @Override
        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
        }
    };

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Ranking> rankings) {
        this.rankings = rankings;
    }
}
