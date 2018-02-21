package com.macormap.mvvmbitcoin.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.macormap.mvvmbitcoin.R;
import com.macormap.mvvmbitcoin.StockApplication;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.modelview.StockViewModel;

import javax.inject.Inject;

//  Carlo Macor  :  February 2018


public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private StockViewModel stockViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activation of Dagger
        ((StockApplication) getApplication()).getAppComponent().inject(this);
        // Dagger now is activated so we can use mViewModelFactory previous annotated with @Inject

        // now We create the View model
        // we don't need to control if it is the first time of create Activity , see what is a ViewModel
        stockViewModel = ViewModelProviders.of(this, mViewModelFactory).get(StockViewModel.class);

        /**  Why use a mViewModelFactory in the .of() of ViewModelProviders ?
         *   is more simple use shortly .of(this) !
         *   Yes, but the constructor of StockViewModel is not empty !
         *   so we pass the mViewModelFactory that know how create the class
         *   and the @Inject can be used in the StockViewModel
         *   and we can use it in the ViewModelFactory too.
         */

        // We create the fragment just the first time.
        if (savedInstanceState == null) {
            StockListFragment fragment = new StockListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, StockListFragment.TAG).commit();
        }
    }


    public StockViewModel getModel() {
        return stockViewModel;
    }

    /** change the fragment with the list of all stocks to the
     * specific intraday fragment
     * @param stockEntity is used to get the data from the net from the correct table.
     * the current fragment of all stocklist go to the stack with .addToBackStack("stock")
     * so from the intraday fragment we can use the back button to come back to this frag.
     */
    public void changeFrag(StockEntity stockEntity) {
        IntradayFragment intradayFragment = IntradayFragment.newInstance(stockEntity.getSymb());
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("stock")
                .replace(R.id.fragment_container,
                        intradayFragment, null).commit();

    }
}
