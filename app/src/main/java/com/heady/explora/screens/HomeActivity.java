package com.heady.explora.screens;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.heady.explora.R;
import com.heady.explora.base.BaseActivity;
import com.heady.explora.base.ExploraApp;
import com.heady.explora.screens.adapters.ExploreAdapter;
import com.heady.explora.screens.adapters.ProductBlockAdapter;
import com.heady.explora.screens.models.CategorisedRatings;
import com.heady.explora.screens.models.Category;
import com.heady.explora.screens.models.Product;
import com.heady.explora.screens.models.ResponseData;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.rvExplore)
    RecyclerView rvExplore;

    @BindView(R.id.rvSocial)
    RecyclerView rvSocial;

    @BindView(R.id.rvSeller)
    RecyclerView rvSeller;

    @BindView(R.id.rvMostViews)
    RecyclerView rvMostViews;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    CategorisedRatings categorisedRatings = new CategorisedRatings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DaggerHomeComponent.builder()
                .netComponent(((ExploraApp) getApplicationContext()).getNetComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);
        homePresenter.getCatalog();
    }


    @Override
    public void showCatalog(ArrayList<Category> sortedCatalog) {
        Logger.d("API callback");

        ExploreAdapter exploreAdapter = new ExploreAdapter(context, sortedCatalog);
        rvExplore.setLayoutManager(new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvExplore.setAdapter(exploreAdapter);
        exploreAdapter.listener = new ExploreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Logger.d("POSITION  :  " + position);
            }
        };

    }


    public void setUpBestSellers(ArrayList<Product> mostSold) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        ProductBlockAdapter exploreAdapter = new ProductBlockAdapter(context, mostSold);
        rvSeller.setLayoutManager(linearLayoutManager);
        rvSeller.setAdapter(exploreAdapter);
    }


    public void setUpSocialTrend(ArrayList<Product> mostShared) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        ProductBlockAdapter exploreAdapter = new ProductBlockAdapter(context, mostShared);
        rvSocial.setLayoutManager(linearLayoutManager);
        rvSocial.setAdapter(exploreAdapter);
    }


    public void setUpMostViewed(ArrayList<Product> mostViewed) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        ProductBlockAdapter exploreAdapter = new ProductBlockAdapter(context, mostViewed);
        rvMostViews.setLayoutManager(linearLayoutManager);
        rvMostViews.setAdapter(exploreAdapter);
    }

    @Override
    public void setUpCategorisedData(CategorisedRatings categorisedData) {
        this.categorisedRatings = categorisedData;
        setUpMostViewed(categorisedRatings.getMostViewed());
        setUpBestSellers(categorisedRatings.getMostViewed());
        setUpSocialTrend(categorisedRatings.getMostViewed());
    }

    @Override
    public void showError(String message) {
        Logger.d("API ERROR" + message);
    }

    @Override
    public void showComplete() {
        Logger.d("API complete");
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }
}
