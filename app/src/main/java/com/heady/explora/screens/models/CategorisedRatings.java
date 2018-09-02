package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yashthakur on 03/09/18.
 */

public class CategorisedRatings implements Parcelable {
    private ArrayList<Product> mostViewed = new ArrayList<>();
    private ArrayList<Product> mostShared = new ArrayList<>();
    private ArrayList<Product> bestSeller = new ArrayList<>();

    public CategorisedRatings() {
    }

    public CategorisedRatings(ArrayList<Product> mostViewed, ArrayList<Product> mostShared, ArrayList<Product> bestSeller) {
        this.mostViewed = mostViewed;
        this.mostShared = mostShared;
        this.bestSeller = bestSeller;
    }

    protected CategorisedRatings(Parcel in) {
        mostViewed = in.createTypedArrayList(Product.CREATOR);
        mostShared = in.createTypedArrayList(Product.CREATOR);
        bestSeller = in.createTypedArrayList(Product.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mostViewed);
        dest.writeTypedList(mostShared);
        dest.writeTypedList(bestSeller);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategorisedRatings> CREATOR = new Creator<CategorisedRatings>() {
        @Override
        public CategorisedRatings createFromParcel(Parcel in) {
            return new CategorisedRatings(in);
        }

        @Override
        public CategorisedRatings[] newArray(int size) {
            return new CategorisedRatings[size];
        }
    };

    public ArrayList<Product> getMostViewed() {
        return mostViewed;
    }

    public void setMostViewed(ArrayList<Product> mostViewed) {
        this.mostViewed = mostViewed;
    }

    public ArrayList<Product> getMostShared() {
        return mostShared;
    }

    public void setMostShared(ArrayList<Product> mostShared) {
        this.mostShared = mostShared;
    }

    public ArrayList<Product> getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(ArrayList<Product> bestSeller) {
        this.bestSeller = bestSeller;
    }
}
