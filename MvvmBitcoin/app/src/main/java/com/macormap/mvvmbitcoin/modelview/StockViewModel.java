package com.macormap.mvvmbitcoin.modelview;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macormap.mvvmbitcoin.db.entities.IntradayEntity;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.repository.StockRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

//  Carlo Macor  :  February 2018


/** as mvvm structure
 *  the UI don't manipulate data but get from a stable ViewModel
 *  so our MainActivity ( UI controller) know this ViewModel
 *  and get data from this to update the GUI
 *
 *  we prefer that this ViewModel don't touch directly the data
 *  fetching from database and the net. so we use a Repository that do it.
 *
 *  this ViewModel will give to Activity data
 *  in LiveData when arrive from an update of database
 *  the database is updated from the net
 *  at the start of the app , we use the previous data saved on dbase
 *  later that data are updated from the net.
 *
 *  so this ViewModel give to activity data as
 *  LiveData<List<StockEntity>> for the list of all stocks values
 *
 *  and the Observable when we are in the Intraday Fragment.
 *  actually the Intraday values are not saved on dbase
 *  so the fragment need to wait the net response in the subscription
 *  of the observable.
 *
 *  We have a function to update the list of all stock [updateNetData()]
 *  now it is used just one time.
 *  nice to add a call to this funtion every 5 minutes.
 *  or set it in the Observable
 */


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
