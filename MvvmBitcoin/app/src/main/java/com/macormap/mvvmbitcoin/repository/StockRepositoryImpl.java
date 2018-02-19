package com.macormap.mvvmbitcoin.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.macormap.mvvmbitcoin.api.ApiStockService;
import com.macormap.mvvmbitcoin.api.response.StockIntradayResponse;
import com.macormap.mvvmbitcoin.api.response.StockLastFullResponse;
import com.macormap.mvvmbitcoin.db.StockDatabase;
import com.macormap.mvvmbitcoin.db.entities.IntradayEntity;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class StockRepositoryImpl implements StockRepository {

    private ApiStockService mApiService;

    private StockDatabase mStockDatabase;

    @Inject
    public StockRepositoryImpl(ApiStockService  apiStockService, StockDatabase stockDatabase) {
        mApiService = apiStockService;
        mStockDatabase = stockDatabase;
    }

    public LiveData<List<StockEntity>> getLivedataDatabase() {
        return  mStockDatabase.loadAllStockers();
    }

    public void updateNetData() {
        mApiService
        .getLastStocksValues()
        .map(StockLastFullResponse::listStockResponse)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe( s -> { mStockDatabase.insertAllStock(s); }
        , onError -> {  Log.d("APP","Error api updateNetData");  }
       );
    }

    public void testNetData(String param) {
        String newParam = "STOCK_"+param+"_DAY";
        mApiService
                .getStockValuesDay(newParam)
                .map(StockIntradayResponse:: listIntraDayResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( s -> {
                    for (int i=0; i<s.size(); i++) {
                        IntradayEntity stockIntradayEntity = s.get(i);
                    } } , onError -> {  Log.d("APP","Error api updateNetData");  }
                );
    }


    public Observable<List<IntradayEntity>> getObservableIntrdaDayPrices(String param) {
        String newParam = "STOCK_"+param+"_DAY";
        return
        mApiService
                .getStockValuesDay(newParam)
                .map(StockIntradayResponse:: listIntraDayResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
