package com.macormap.mvvmbitcoin.db.entities;

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
