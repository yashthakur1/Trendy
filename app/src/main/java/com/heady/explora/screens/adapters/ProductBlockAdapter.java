package com.heady.explora.screens.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heady.explora.R;
import com.heady.explora.screens.models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yashthakur on 02/09/18.
 */

public class ProductBlockAdapter extends RecyclerView.Adapter<ProductBlockAdapter.ExploreViewHolder> {

    Context context;
    private ArrayList<Category> categories;

    public ProductBlockAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ProductBlockAdapter.ExploreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_slider, parent, false);
        return new ProductBlockAdapter.ExploreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductBlockAdapter.ExploreViewHolder viewHolder, int position) {

        Category category = categories.get(position);
        viewHolder.tvCategory.setText(category.getName());
        Picasso.get().load("https://picsum.photos/400/300?image=" + position + 20).fit().into(viewHolder.ivBackground);

    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    class ExploreViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory;
        ImageView ivBackground;

        ExploreViewHolder(View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvCategory);
            ivBackground = itemView.findViewById(R.id.ivBackground);

        }
    }

}