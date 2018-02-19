package com.macormap.mvvmbitcoin.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.macormap.mvvmbitcoin.R;
import com.macormap.mvvmbitcoin.StockApplication;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.modelview.StockViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private StockViewModel stockViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((StockApplication) getApplication()).getAppComponent().inject(this);

        stockViewModel = ViewModelProviders.of(this, mViewModelFactory).get(StockViewModel.class);

        if (savedInstanceState == null) {
            StockListFragment fragment = new StockListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, StockListFragment.TAG).commit();
        }
    }

    public StockViewModel getModel() {
        return stockViewModel;
    }

    public void changeFrag(StockEntity stockEntity) {
        IntradayFragment intradayFragment = IntradayFragment.newInstance(stockEntity.getSymb());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("stock")
                .replace(R.id.fragment_container,
                intradayFragment, null).commit();

    }
}
