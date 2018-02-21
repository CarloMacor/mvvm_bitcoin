package com.macormap.mvvmbitcoin.api;

import com.macormap.mvvmbitcoin.api.response.StockIntradayResponse;
import com.macormap.mvvmbitcoin.api.response.StockLastFullResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

//  Carlo Macor  :  February 2018

/**
 *  to get the data from macormap.com
 *  used by AppModule.java to @Provides the apiservice
 *  and past as parameter by Dagger in StockRepositoryImpl
 */

public interface ApiStockService {

    @GET("/criptovalue/LastStockValues.php")
    Observable<StockLastFullResponse> getLastStocksValues();

    @GET("/criptovalue/GetDayStockValues.php")
    Observable<StockIntradayResponse> getStockValuesDay(@Query("istab") String symb);

}
