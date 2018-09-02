package com.heady.explora.screens;

import com.heady.explora.screens.models.ResponseData;

/**
 * Created by yashthakur on 26/08/18.
 */

public interface HomeContract {

    interface View {
        void showCatalog(ResponseData catalogData);

        void setUpBestSellers(ResponseData catalogData);

        void setUpSocialTrend(ResponseData catalogData);

        void setUpMostViewed(ResponseData catalogData);

        void showError(String message);

        void showComplete();

        void showLoader();

        void hideLoader();


    }

    interface Presenter {
        void getCatalog();
    }
}

