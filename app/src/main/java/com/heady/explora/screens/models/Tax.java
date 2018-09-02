package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yashthakur on 26/08/18.
 */

public class Tax implements Parcelable {
    private String names = "";
    private float value = 0;

    public Tax() {
    }

    public Tax(String names, float value) {
        this.names = names;
        this.value = value;
    }

    protected Tax(Parcel in) {
        names = in.readString();
        value = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(names);
        dest.writeFloat(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tax> CREATOR = new Creator<Tax>() {
        @Override
        public Tax createFromParcel(Parcel in) {
            return new Tax(in);
        }

        @Override
        public Tax[] newArray(int size) {
            return new Tax[size];
        }
    };

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
