package com.macormap.mvvmbitcoin.di;

import com.macormap.mvvmbitcoin.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = { AppModule.class } )
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
