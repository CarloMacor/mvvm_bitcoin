package com.macormap.mvvmbitcoin.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.utils.CoderStocks;

import java.util.ArrayList;
import java.util.List;

//  Carlo Macor  :  February 2018

/** ApiServise has :  Observable<StockLastFullResponse> getLastStocksValues();
 *  so we have the function to map data in  List<StockEntity>  ready to update database
 */

public class StockLastFullResponse {

    @SerializedName("items")
    @Expose
    private List<StockLastSingleResponse> listaStockValueRisposta;

    public List<StockLastSingleResponse> getListaStockValueRisposta() {
        return listaStockValueRisposta;
    }

    /** used to map data */

    public List<StockEntity> listStockResponse() {
        StockEntity stockEntity;
        StockLastSingleResponse lastSingleResponse;
        List<StockEntity> resList = new ArrayList<StockEntity>();
        for (int i=0; i<listaStockValueRisposta.size(); i++) {
            lastSingleResponse = listaStockValueRisposta.get(i);
            stockEntity = new StockEntity();
            stockEntity.setSymb(lastSingleResponse.getSymb());
            stockEntity.setDescrSymb(CoderStocks.descStockFromSym(lastSingleResponse.getSymb()));
            stockEntity.setValueSymb(lastSingleResponse.getStrPrice());
            resList.add(stockEntity);
        }
        return resList;
    }




}
