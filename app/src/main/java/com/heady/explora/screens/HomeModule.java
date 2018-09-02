package com.heady.explora.screens;

import com.heady.explora.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yashthakur on 26/08/18.
 */
@Module
public class HomeModule {
    private final HomeContract.View mView;


    public HomeModule(HomeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    HomeContract.View providesHomeContractView() {
        return mView;
    }
}
