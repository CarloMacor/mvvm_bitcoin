package com.macormap.mvvmbitcoin.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.macormap.mvvmbitcoin.db.entities.StockEntity;

import java.util.List;
import java.util.concurrent.Executors;

//  Carlo Macor  :  February 2018

/**
 *  The database with just a table : Stockentity
 */

@Database(entities = {StockEntity.class},version = 1)
public abstract class StockDatabase extends RoomDatabase {

    public abstract StockEntityDao stockEntityDao();

    public void insertAllStock(List<StockEntity> stockEntitylist ) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                stockEntityDao().insertAll(stockEntitylist);
            }
        });
    }


    public LiveData<List<StockEntity>> loadAllStockers() {
        return stockEntityDao().loadAllStocksEntities();
    }




}
