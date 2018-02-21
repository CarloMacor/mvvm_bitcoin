package com.macormap.mvvmbitcoin;


import android.app.Application;

import com.macormap.mvvmbitcoin.di.AppComponent;
import com.macormap.mvvmbitcoin.di.AppModule;
import com.macormap.mvvmbitcoin.di.DaggerAppComponent;

//  Carlo Macor  :  February 2018

/**   AppComponent mAppComponent;   is created using Dagger
 *    Note that we write            : DaggerAppComponent.builder().
 *    because we have a unit named  :       AppComponent
 *    if We have a unit named NetComponent We'll write DaggerNetComponent.builder().
 *
 *    Also  .appModule(new AppModule(this)) becaouse we start constructor of AppModule.java
 *    that need Application parameter (this application)
 *
 *      it's possible that we have more modules to init so we have more line like this one
 *      .netModule(new NetModule(parameter to init))
 *      .someModule(new SomeModule() )
 *
 *      so in MainActivity we can get reference to this AppComponent just created.
 *      using the form : ((StockApplication) getApplication()).getAppComponent().inject(this);
 *      and activate dagger from that moment.
 *
 *      see comments in AppComponent.java
 */


public class StockApplication extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


}
