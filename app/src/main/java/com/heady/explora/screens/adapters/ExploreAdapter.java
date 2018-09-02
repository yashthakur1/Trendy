package com.heady.explora.screens.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heady.explora.R;
import com.heady.explora.screens.models.Category;

import java.util.ArrayList;

/**
 * Created by yashthakur on 26/08/18.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    Context context;
    private ArrayList<Category> categories;

    public ExploreAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ExploreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new ExploreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExploreViewHolder viewHolder, int position) {

        Category category = categories.get(position);
        viewHolder.tvCategory.setText(category.getName());

    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    class ExploreViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory;

        ExploreViewHolder(View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvCategory);

        }
    }

}

