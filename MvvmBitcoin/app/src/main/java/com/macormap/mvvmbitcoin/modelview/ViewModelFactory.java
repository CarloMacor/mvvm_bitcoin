package com.macormap.mvvmbitcoin.modelview;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

//  Carlo Macor  :  February 2018

/**
 *  to solve the Dagger DI as described in mainactivity
 */

public class ViewModelFactory implements ViewModelProvider.Factory {
    private StockViewModel mViewModel;

    @Inject
    public ViewModelFactory(StockViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StockViewModel.class)) {
            return (T) mViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
