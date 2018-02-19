package com.macormap.mvvmbitcoin;


import android.app.Application;

import com.macormap.mvvmbitcoin.di.AppComponent;
import com.macormap.mvvmbitcoin.di.AppModule;
import com.macormap.mvvmbitcoin.di.DaggerAppComponent;

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
