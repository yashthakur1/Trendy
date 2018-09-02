package com.heady.explora.screens.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by yashthakur on 26/08/18.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    private Context context;
    private ArrayList<Category> categories;
    public ExploreAdapter.OnItemClickListener listener;


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
    public void onBindViewHolder(final ExploreViewHolder viewHolder, int position) {

        Category category = categories.get(position);
        viewHolder.tvCategory.setText(category.getName());
        Picasso.get().load("https://picsum.photos/200/200?image=" + position + 30).fit().into(viewHolder.ivBackground);

        viewHolder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
//        return categories != null ? categories.size() : 0;
    }

    class ExploreViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory;
        CardView cvContainer;
        ImageView ivBackground;

        ExploreViewHolder(View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvCategory);
            cvContainer = itemView.findViewById(R.id.cvContainer);
            ivBackground = itemView.findViewById(R.id.ivBackground);

        }
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


}

