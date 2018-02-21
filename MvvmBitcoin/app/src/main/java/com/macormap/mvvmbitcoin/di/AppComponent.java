package com.macormap.mvvmbitcoin.di;

import com.macormap.mvvmbitcoin.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

//  Carlo Macor  :  February 2018


//  Carlo Macor  :  February 2018

/** It's possible use more @Component modules; in this case : {AppModule.class , NetModule.class, OtherModule.class}
 *
 *  I'll use this only in MainActivity;
 *  so inside this interface: only  [ void inject(MainActivity mainActivity); ]
 *  if I need to Inject in other Activity or Fragment I need to write other line like :
 *  [void inject(MyFragment fragment);]
 *
 *  to start Dagger for the Dependency Injection, in MainActivity I use:
 *  ((StockApplication) getApplication()).getAppComponent().inject(this);
 *  Look at StockApplication.java relative comment.
 *  from that moment !  @Inject become active.
 *
 *  The elemnts that I can Inject, are present in AppModule.java
 *  In this case : Application, ApiStockService, StockDatabase, StockRepository, ViewModel, ViewModelProvider.Factory
 *
 *  so we can use a form like :
 *  @Inject  [a Provides available ] in MainActivity fields
 *  in this case :  @Inject ViewModelProvider.Factory mViewModelFactory;
 *  and in the constructor of other class like : StockViewModel , StockRepositoryImpl
 *  example in StockViewModel.java
 *  @Inject  public StockViewModel(StockRepositoryImpl stockRepository) {mStockRepository = stockRepository; }
 *  so I don't create a new instance of StockRepositoryImpl; but I receive it as parameter in the constructor.
 *  Dagger created it before, and eventually share the same stockRepository to more class that need it.
 *  Note the use of @Singleton in StockRepository !
 *
 *  Note the @Singleton in this unit to ensure that it's used just one time
 *  anyway  DaggerAppComponent.builder().appModule(new AppModule(this)).build();
 *  is used only in StockApplicaion.java
 */


@Singleton
@Component(modules = { AppModule.class } )
public interface AppComponent {
    void inject(MainActivity mainActivity);
    // void inject(MyFragment fragment);
}

