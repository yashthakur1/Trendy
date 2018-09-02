package com.heady.explora.screens.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yashthakur on 26/08/18.
 */

public class Category implements Parcelable {

    private int id = 0;
    private String name = "";
    private ArrayList<Product> products = new ArrayList<>();
    private List<Integer> child_categories = new ArrayList<Integer>();

    public Category() {
    }

    public Category(int id, String name, ArrayList<Product> products, ArrayList<Integer> child_categories) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.child_categories = child_categories;
    }

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
        child_categories = new ArrayList<>();
        in.readList(child_categories, Integer.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(products);
        dest.writeList(child_categories);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Integer> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(List<Integer> child_categories) {
        this.child_categories = child_categories;
    }
}
