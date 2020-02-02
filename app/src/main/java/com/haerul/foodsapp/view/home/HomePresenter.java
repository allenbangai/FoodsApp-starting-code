/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.model.Categories;
import com.haerul.foodsapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter {

    private HomeView view;

    // TODO 15 **Done** Create the constructor (View) **Done**
    public HomePresenter(HomeView view) {
        this.view = view;
    }

    void getMeals() {
        // TODO 16 **Done** do loading before making a request to the server **Done**
        view.showLoading();
        // TODO 17 **Done** with the line you have made a request
        Call<Meals> mealsCall = Utils.getApi().getMeal();
        // TODO 19 **Done** waiting for Callback
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                // TODO 20 **Done** Close loading when you have received a response from the server
                view.hideLoading();

                // TODO 21 **Done** Non-empty results check & Non-empty results check
                if (response.isSuccessful() && response.body() != null) {
                    /*
                     * TODO 22 **Done** Receive the result
                     */
                    view.setMeal(response.body().getMeals());
                }else {
                    // TODO 23 **Done** Show an error message if the conditions are not met **Done**
                    view.onErroLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                /*
                 * Failure will be thrown here
                 * for this you must do
                 * 1. closes loading
                 * 2. displays an error message
                 */
                // TODO 24 **Done** Close loading
                view.hideLoading();
                // TODO 25 **Done** Show an error message
                view.onErroLoading(t.getLocalizedMessage());
            }
        });
    }


    void getCategories() {
        // TODO 26 **Done** do loading before making a request to the server
        view.showLoading();
        // TODO 27 **Done** create Call<Categories> categoriesCall = ...
        Call<Categories> categoriesCall = Utils.getApi().getCategory();

        // TODO 28 **Done** waiting for enqueue Callback
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {
                // TODO 29 **Done** Non-empty results check & Non-empty results check
                if (response.isSuccessful() && response.body() != null) {
                    /*
                     * TODO 30 **Done** Receive the result
                     */
                    view.setCategory(response.body().getCategories());
                }
                else {
                    // TODO 31 **Done** Show an error message if the conditions are not met
                    view.onErroLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                /*
                 * Failure will be thrown here
                 * for this you must do
                 * 1. closes loading
                 * 2. displays an error message
                 */
                // TODO 32 **Done** Close loading
                view.hideLoading();
                // TODO 33 **Done** Show an error message
                view.onErroLoading(t.getLocalizedMessage());
            }
        });
    }
}
