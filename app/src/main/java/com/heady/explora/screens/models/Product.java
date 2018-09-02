package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yashthakur on 26/08/18.
 */

public class Product implements Parcelable {

    private int id = 0;
    private int order_count = 0;
    private int view_count = 0;
    private int shares = 0;
    private String name = "";
    private String date_added = "";
    private ArrayList<Product> variants = new ArrayList<>();
    private Tax tax = new Tax();

    public Product() {
    }

    public Product(int id, int order_count, int view_count, int shares, String name, String date_added, ArrayList<Product> variants, Tax tax) {
        this.id = id;
        this.order_count = order_count;
        this.view_count = view_count;
        this.shares = shares;
        this.name = name;
        this.date_added = date_added;
        this.variants = variants;
        this.tax = tax;
    }


    protected Product(Parcel in) {
        id = in.readInt();
        order_count = in.readInt();
        view_count = in.readInt();
        shares = in.readInt();
        name = in.readString();
        date_added = in.readString();
        variants = in.createTypedArrayList(Product.CREATOR);
        tax = in.readParcelable(Tax.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(order_count);
        dest.writeInt(view_count);
        dest.writeInt(shares);
        dest.writeString(name);
        dest.writeString(date_added);
        dest.writeTypedList(variants);
        dest.writeParcelable(tax, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public ArrayList<Product> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Product> variants) {
        this.variants = variants;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
