package com.heady.explora.screens;

import com.heady.explora.data.ApiService;
import com.heady.explora.screens.models.ResponseData;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yashthakur on 26/08/18.
 */

public class HomePresenter implements HomeContract.Presenter {

    Retrofit retrofit;
    HomeContract.View mView;

    @Inject
    public HomePresenter(Retrofit retrofit, HomeContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }


    @Override
    public void getCatalog() {
        mView.showLoader();
        retrofit.create(ApiService.class).getCatalogData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseData>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoader();
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseData catalogData) {
                        mView.showCatalog(catalogData);
                    }
                });
    }
}
