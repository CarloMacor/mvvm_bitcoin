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

//  Carlo Macor  :  February 2018


/** this repository is know by ViewModel
 *  with dagger we not create new mApiService and mStockDatabase
 */

@Singleton
public class StockRepositoryImpl implements StockRepository {

    private ApiStockService mApiService;

    private StockDatabase mStockDatabase;

    /**
     * the parameter arrive from Dagger as DI
     * @param apiStockService
     * @param stockDatabase
     */
    @Inject
    public StockRepositoryImpl(ApiStockService  apiStockService, StockDatabase stockDatabase) {
        mApiService = apiStockService;
        mStockDatabase = stockDatabase;
    }

    /** we get the list of values of the stocks
     *  and as on as they cahnge by the follow function updateNetData()
     *  the information is trasmitted to the fragment that use it.
     */
    public LiveData<List<StockEntity>> getLivedataDatabase() {
        return  mStockDatabase.loadAllStockers();
    }

    /** update data use the Apiservice to call getLastStocksValues()
     *  that give Observable<StockLastFullResponse>
     *  we map it to transform in List<StockEntity>
     *  we subscribe it and when we receive the data we store in the database
     *  as stored (the dbase know that are LiveData ) the GUI will be updated.
     */
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


    /**
     * this Observable is subsribed from Fragment Intraday
     * param (example BTC is used to build a string "STOCK_BTC_DAY" that is the name of the table
     * on the server , and the php will give back the Json list of items.
     * @return the list of Intraday to design the graph by the Intraday Fragment
     */
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
