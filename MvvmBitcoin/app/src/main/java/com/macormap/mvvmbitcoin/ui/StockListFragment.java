package com.macormap.mvvmbitcoin.ui;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macormap.mvvmbitcoin.R;
import com.macormap.mvvmbitcoin.adapter.StockAdapter;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.modelview.StockViewModel;

//  Carlo Macor  :  February 2018

/** the fragment to display in a recycler view the list of stocks and relative values */

public class StockListFragment extends Fragment {

    public static final String TAG = "StockListFragment";

    private StockViewModel stockViewModel;
    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;
    private View viewfrag;
    private StockEntityCallback mStockEntityCallback;

    public StockListFragment() {
    }


    /** Inflate the layout  */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewfrag = inflater.inflate(R.layout.fragment_stock_list, container, false);
        setupView();
        return viewfrag;
    }


    /** onActivityCreated is after onCreateView
     *  so after inflate we get the stockViewModel from MainActivity
     *  we set the livedata to Adapter
     *  so as on as change the dbase the adapter update the GUI
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        stockViewModel= ((MainActivity) getActivity()).getModel();
        stockViewModel.getLivedataDatabase().observe(this, s -> {
            stockAdapter.updateDataList(s);
        });
        stockViewModel.updateNetData();
    }


    /** Setup the view with a recyclerView and relative Adapter  */
    private void setupView() {
        recyclerView = viewfrag.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), LinearLayoutManager.VERTICAL
        );
        recyclerView.addItemDecoration(mDividerItemDecoration);
        stockAdapter = new StockAdapter(mStockClickCallback);
        recyclerView.setAdapter(stockAdapter);
    }


    /** used in row_stock.xml in databinding
     *  called at click on the row cardview.
     */
    private final StockEntityCallback mStockClickCallback = stockEntity -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).changeFrag(stockEntity);
        }
    };


}
