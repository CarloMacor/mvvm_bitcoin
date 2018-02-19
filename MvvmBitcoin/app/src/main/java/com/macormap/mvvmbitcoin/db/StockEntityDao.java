package com.macormap.mvvmbitcoin.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.macormap.mvvmbitcoin.db.entities.StockEntity;

import java.util.List;


@Dao
public interface StockEntityDao {

    @Query("SELECT * FROM stocks")
    LiveData<List<StockEntity>> loadAllStocksEntities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<StockEntity> products);


 //   @Query("DELETE FROM stocks")
 //   public void deleteAll();

}
