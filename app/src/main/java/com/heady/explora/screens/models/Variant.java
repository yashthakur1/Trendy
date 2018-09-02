package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yashthakur on 26/08/18.
 */

public class Variant implements Parcelable {

    private int id = 0;
    private String color = "";
    private int size = 0;
    private int price = 0;

    public Variant() {
    }

    public Variant(int id, String color, int size, int price) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    protected Variant(Parcel in) {
        id = in.readInt();
        color = in.readString();
        size = in.readInt();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(color);
        dest.writeInt(size);
        dest.writeInt(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Variant> CREATOR = new Creator<Variant>() {
        @Override
        public Variant createFromParcel(Parcel in) {
            return new Variant(in);
        }

        @Override
        public Variant[] newArray(int size) {
            return new Variant[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
