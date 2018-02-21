package com.macormap.mvvmbitcoin.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//  Carlo Macor  :  February 2018

/**
 *  Table of database
 */

@Entity(tableName = "stocks")
public class StockEntity {

    @PrimaryKey
    @NonNull
    private String symb;
    private String descrSymb;
    private String valueSymb;

    public StockEntity() {
    }

    public void setSymb(String val) {
        symb = val;
    }

    public String getSymb() {
        return symb;
    }

    public void setDescrSymb(String val) {
        descrSymb = val;
    }

    public String getDescrSymb() {
        return descrSymb;
    }

    public void setValueSymb(String val) {
        valueSymb = val;
    }

    public String getValueSymb() {
        return valueSymb;
    }

    public String getValueSymbStr() {
        Double dvalue = Double.parseDouble(valueSymb);
        if (dvalue>10) { return String.format("$ %,.0f", dvalue); }
        else { return String.format("$ %,.2f", dvalue); }
    }

}