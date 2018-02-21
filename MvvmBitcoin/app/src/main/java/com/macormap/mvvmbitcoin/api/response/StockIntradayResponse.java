package com.macormap.mvvmbitcoin.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.macormap.mvvmbitcoin.db.entities.IntradayEntity;

import java.util.ArrayList;
import java.util.List;

//  Carlo Macor  :  February 2018


/** ApiServise has :   Observable<StockIntradayResponse> getStockValuesDay(@Query("istab") String symb);
 *  so we have the function to map data in  List<IntradayEntity>  ready to design the Mysurface
 */


public class StockIntradayResponse {

    @SerializedName("items")
    @Expose
    private List<StockDayResponse> listaIntraday;

    public  List<StockDayResponse> getListaIntraday() {
        return listaIntraday;
    }

    public List<IntradayEntity> listIntraDayResponse() {
        IntradayEntity stockIntradayEntity;
        StockDayResponse stockDayResponse;

        List<IntradayEntity> resList = new ArrayList<IntradayEntity>();

        for (int i=0; i<listaIntraday.size(); i++) {
            stockDayResponse = listaIntraday.get(i);
            stockIntradayEntity = new IntradayEntity();
            stockIntradayEntity.setIndex(i);
            stockIntradayEntity.setValueStock(stockDayResponse.getPrice());
            resList.add(stockIntradayEntity);
        }
        return resList;
    }


}
