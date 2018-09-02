package com.heady.explora.screens;

import com.heady.explora.data.ApiService;
import com.heady.explora.screens.models.CategorisedRatings;
import com.heady.explora.screens.models.Category;
import com.heady.explora.screens.models.Product;
import com.heady.explora.screens.models.ResponseData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

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
                        sortTheCatalog(catalogData, mView);
                        categoriseProductsToRatings(catalogData, mView);
                    }
                });
    }


    private void sortTheCatalog(ResponseData catalogData, HomeContract.View mView) {
        ArrayList<Category> categoriesWithChild;
        ArrayList<Integer> childCategories = new ArrayList<>();

        categoriesWithChild = findObjectsWithChild(catalogData.getCategories(), childCategories);

        for (int j = 0; j < categoriesWithChild.size(); j++) {
            for (int i = 0; i < catalogData.getCategories().size(); i++) {
                Category currentCategory = catalogData.getCategories().get(i);
                for (int k = 0; k < categoriesWithChild.get(j).getChild_categories().size(); k++) {
                    if (categoriesWithChild.get(j).getChild_categories().get(k) == currentCategory.getId()) {
                        // yes categories.get(i) is  a child element
                        categoriesWithChild.get(j).getCustom_categories().add(new Category(currentCategory.getId(), currentCategory.getName(), currentCategory.getProducts(), null, currentCategory.getCustom_categories()));
                        break;
                    }
                }
            }
        }


        for (int i = 0; i < categoriesWithChild.size(); i++) {
            Category currentCategory = categoriesWithChild.get(i);
            for (int j = 0; j < childCategories.size(); j++) {
                if (childCategories.get(j) == categoriesWithChild.get(i).getId()) {
                    categoriesWithChild.get(i).getCustom_categories().add(new Category(currentCategory.getId(), currentCategory.getName(), currentCategory.getProducts(), null, currentCategory.getCustom_categories()));
                    break;
                }
            }
        }

        mView.showCatalog(finalCategories(childCategories, categoriesWithChild));
    }

    private ArrayList<Category> findObjectsWithChild(ArrayList<Category> categories, ArrayList<Integer> childCategories) {

        ArrayList<Category> categoriesWithChild = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getChild_categories().size() > 0) {
                categoriesWithChild.add(categories.get(i));
                childCategories.addAll(categories.get(i).getChild_categories());
            }
        }
        return categoriesWithChild;
    }

    private ArrayList<Category> finalCategories(ArrayList<Integer> childCategories, ArrayList<Category> customCategories) {
        Iterator<Category> it = customCategories.iterator();
        while (it.hasNext()) {
            if (childCategories.contains(it.next().getId())) {
                it.remove();
            }
        }
        return customCategories;
    }

    private void categoriseProductsToRatings(ResponseData catalogData, HomeContract.View mView) {

        ArrayList<Product> productsList = new ArrayList<>();
        ArrayList<Product> sortedMostViewed = new ArrayList<>();
        ArrayList<Product> sortedMostShared = new ArrayList<>();
        ArrayList<Product> sortedMostSold = new ArrayList<>();
        ArrayList<Product> mostViewed;
        ArrayList<Product> mostShared;
        ArrayList<Product> bestSeller;

        for (int i = 0; i < catalogData.getCategories().size(); i++) {
            if (catalogData.getCategories().get(i).getProducts().size() > 0) {
                productsList.addAll(catalogData.getCategories().get(i).getProducts());
            }
        }

        mostViewed = catalogData.getRankings().get(0).getProducts();
        mostShared = catalogData.getRankings().get(1).getProducts();
        bestSeller = catalogData.getRankings().get(2).getProducts();
        Collections.sort(mostViewed);
        Collections.sort(mostShared, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getShares() - o1.getShares();
            }
        });
        Collections.sort(bestSeller, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getOrder_count() - o1.getOrder_count();
            }
        });


        for (int j = 0; j < mostViewed.size(); j++) {
            for (int i = 0; i < productsList.size(); i++) {
                if (mostViewed.get(j).getId() == productsList.get(i).getId()) {
                    sortedMostViewed.add(productsList.get(i));
                }
            }
        }


        for (int j = 0; j < bestSeller.size(); j++) {
            for (int i = 0; i < productsList.size(); i++) {
                if (bestSeller.get(j).getId() == productsList.get(i).getId()) {
                    sortedMostSold.add(productsList.get(i));
                }
            }
        }

        for (int j = 0; j < mostShared.size(); j++) {
            for (int i = 0; i < productsList.size(); i++) {
                if (mostShared.get(j).getId() == productsList.get(i).getId()) {
                    sortedMostShared.add(productsList.get(i));
                }
            }
        }


        CategorisedRatings categorisedRatings = new CategorisedRatings();
        categorisedRatings.setMostViewed(sortedMostViewed);
        categorisedRatings.setMostShared(sortedMostShared);
        categorisedRatings.setBestSeller(sortedMostSold);

        mView.setUpCategorisedData(categorisedRatings);
    }
}
