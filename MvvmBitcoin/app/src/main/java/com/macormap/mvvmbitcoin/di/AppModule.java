package com.macormap.mvvmbitcoin.di;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.macormap.mvvmbitcoin.api.ApiStockService;
import com.macormap.mvvmbitcoin.db.StockDatabase;
import com.macormap.mvvmbitcoin.modelview.StockViewModel;
import com.macormap.mvvmbitcoin.modelview.ViewModelFactory;
import com.macormap.mvvmbitcoin.repository.StockRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//  Carlo Macor  :  February 2018


/** this module is called from AppComponent
 * @Component(modules = { AppModule.class } )
 * for this reason it start with @Module
 *
 * so all the @Provides that are present in this unit are available
 * to inject. : Application, ApiStockService, StockDatabase, StockRepository, ViewModel, ViewModelProvider.Factory
 *
 * in this case I need to create a Room database , and to build it I need Application reference.
 * for this reason there is a constructor with Application parameter used in Room constructor of database
 *
 * of course in the place where this Module is initialized, i need the Application info
 * infact we use  ....  .appModule(new AppModule(this))  ....   in StockApplication.java and this is the Application.
 */


@Module
public class AppModule {

    private Application mApplication;
    private StockDatabase stockDatabase;

    public AppModule(Application application) {
        mApplication = application;
        stockDatabase = Room.databaseBuilder(mApplication, StockDatabase.class, "demo-db").build();
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    ApiStockService provideApiStockService() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.macormap.com")
                .build()
                .create(ApiStockService.class);
    }

    @Singleton
    @Provides
    StockDatabase provideStockDatabase() {
        return stockDatabase;
    }

    @Provides
    @Singleton
    StockRepository provideStockRepository(StockRepository repository) {
        return repository;
    }

    @Provides
    ViewModel provideStockViewModel(StockViewModel viewModel) {
        return viewModel;
    }

    @Provides
    ViewModelProvider.Factory provideStockViewModelFactory(ViewModelFactory factory ) {
        return factory;
    }


}
