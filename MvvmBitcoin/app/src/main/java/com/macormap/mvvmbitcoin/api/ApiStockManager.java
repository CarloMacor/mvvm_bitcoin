package com.macormap.mvvmbitcoin.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.macormap.mvvmbitcoin.api.response.StockLastFullResponse;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiStockManager {

    private final ApiStockService apiStockService;

    public ApiStockManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.macormap.com")
                .build();
        apiStockService = retrofit.create(ApiStockService.class);
    }

    public Observable<List<StockEntity>> getObListStock() {
        return apiStockService
                .getLastStocksValues()
                .map(StockLastFullResponse::listStockResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
