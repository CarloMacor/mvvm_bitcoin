package com.macormap.mvvmbitcoin.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.macormap.mvvmbitcoin.R;
import com.macormap.mvvmbitcoin.databinding.RowStockBinding;
import com.macormap.mvvmbitcoin.db.entities.StockEntity;
import com.macormap.mvvmbitcoin.ui.StockEntityCallback;

import java.util.ArrayList;
import java.util.List;


public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewHolder>  {

    private List<StockEntity> stockEntityList;

    @Nullable
    private final StockEntityCallback mStockEntityCallback;

    public StockAdapter(@Nullable StockEntityCallback clickCallback) {
        mStockEntityCallback = clickCallback;
    }


    public void updateDataList(List<StockEntity> mstockEntityList) {
       if (stockEntityList==null) { stockEntityList = new ArrayList<StockEntity>(); }
       stockEntityList.clear();
       stockEntityList.addAll(mstockEntityList);
       notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowStockBinding binding = DataBindingUtil
                          .inflate(LayoutInflater.from(parent.getContext()),
                          R.layout.row_stock,parent,false);

        binding.setCallback(mStockEntityCallback);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mRowStockBinding.setStockdata(stockEntityList.get(position));
        holder.mRowStockBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return (stockEntityList == null ) ? 0 : stockEntityList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        final RowStockBinding mRowStockBinding;

        public MyViewHolder(RowStockBinding binding) {
            super(binding.getRoot());
            mRowStockBinding = binding;
        }
    }
}
