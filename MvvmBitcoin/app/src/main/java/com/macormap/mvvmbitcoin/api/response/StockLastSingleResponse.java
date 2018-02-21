package com.macormap.mvvmbitcoin.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//  Carlo Macor  :  February 2018


/**
 *  single item of LastFullResponse
 */


public class StockLastSingleResponse {

    @SerializedName("SYMB")
    @Expose
    private String Symb;

    @SerializedName("PRICE")
    @Expose
    private Double Price;

    @SerializedName("VOLUME")
    @Expose
    private Double Volume;

    @SerializedName("SCAMBIO")
    @Expose
    private Double Scambio;

    @SerializedName("LASTTIME")
    @Expose
    private String LastTime;


    public String getSymb() { return Symb; }

    public void setSymb(String val) {Symb = val;}


    public String getStrPrice() { return Double.toString(Price); }

    public Double getPrice() {return Price; }

    public void setPrice(Double val) {Price = val;}


}
