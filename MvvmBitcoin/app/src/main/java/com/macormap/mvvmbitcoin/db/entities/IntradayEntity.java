package com.macormap.mvvmbitcoin.db.entities;

//  Carlo Macor  :  February 2018

/**
 *  used to display the Intraday graph
 */

public class IntradayEntity {

    private Integer index;

    private Double valueStock;

    public IntradayEntity() {
    }

    public void    setIndex(Integer val)     { index = val; }

    public Integer getIndex()                { return index; }

    public void   setValueStock(Double val ) { valueStock = val; }

    public Double getValueStock()            { return valueStock; }


}
