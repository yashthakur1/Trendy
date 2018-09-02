package com.heady.explora.screens;

import com.heady.explora.screens.models.CategorisedRatings;
import com.heady.explora.screens.models.Category;
import com.heady.explora.screens.models.ResponseData;

import java.util.ArrayList;

/**
 * Created by yashthakur on 26/08/18.
 */

public interface HomeContract {

    interface View {

        void showCatalog(ArrayList<Category> sortedCatalog);

        void setUpCategorisedData(CategorisedRatings categorisedData);

        void showError(String message);

        void showComplete();

        void showLoader();

        void hideLoader();


    }

    interface Presenter {
        void getCatalog();
    }
}

