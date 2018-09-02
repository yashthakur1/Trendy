package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yashthakur on 26/08/18.
 */

public class Ranking implements Parcelable{

    private String ranking = "";
    private ArrayList<Product> products = new ArrayList<>();

    public Ranking() {
    }

    public Ranking(String ranking, ArrayList<Product> products) {
        this.ranking = ranking;
        this.products = products;
    }

    protected Ranking(Parcel in) {
        ranking = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ranking);
        dest.writeTypedList(products);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ranking> CREATOR = new Creator<Ranking>() {
        @Override
        public Ranking createFromParcel(Parcel in) {
            return new Ranking(in);
        }

        @Override
        public Ranking[] newArray(int size) {
            return new Ranking[size];
        }
    };

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
