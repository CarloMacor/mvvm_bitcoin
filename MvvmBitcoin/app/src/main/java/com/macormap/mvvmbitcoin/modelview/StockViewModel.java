package com.macormap.mvvmbitcoin.modelview;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macormap.mvvmbitcoin.db.entities.IntradayEntity;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.repository.StockRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class StockViewModel  extends ViewModel {

    private StockRepositoryImpl mStockRepository;

    @Inject
    public StockViewModel(StockRepositoryImpl stockRepository) {
        mStockRepository = stockRepository;
    }

    public LiveData<List<StockEntity>> getLivedataDatabase() {
        return  mStockRepository.getLivedataDatabase();
    }

    public void updateNetData() {
        mStockRepository.updateNetData();
    }


    public Observable<List<IntradayEntity>> getIntrdaDayPrices(String param) {
        return mStockRepository.getObservableIntrdaDayPrices(param);
    }


}
