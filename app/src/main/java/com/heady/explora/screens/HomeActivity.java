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
import com.heady.explora.screens.models.ResponseData;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.rvExplore)
    RecyclerView rvExplore;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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
    public void showCatalog(ResponseData catalogData) {
        Logger.d("API callback");
        Logger.json(new Gson().toJson(catalogData));

        ExploreAdapter exploreAdapter = new ExploreAdapter(context, catalogData.getCategories());
        rvExplore.setLayoutManager(new GridLayoutManager(this, 2));
        rvExplore.setAdapter(exploreAdapter);
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
