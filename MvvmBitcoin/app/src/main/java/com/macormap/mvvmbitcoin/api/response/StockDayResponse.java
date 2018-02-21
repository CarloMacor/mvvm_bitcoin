package com.macormap.mvvmbitcoin.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//  Carlo Macor  :  February 2018


/**
 *  single item of Intraday response
 */

public class StockDayResponse {

    public StockDayResponse(double val) {
        price = val;
    }

    @SerializedName("PRICE")
    @Expose
    private Double price;

    public Double getPrice() { return price; }




}
