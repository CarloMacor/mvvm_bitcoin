package com.macormap.mvvmbitcoin.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.macormap.mvvmbitcoin.R;
import com.macormap.mvvmbitcoin.db.entities.IntradayEntity;
import com.macormap.mvvmbitcoin.modelview.StockViewModel;
import com.macormap.mvvmbitcoin.utils.MySurfaceView;

import java.util.List;

import io.reactivex.Observable;

//  Carlo Macor  :  February 2018

/** the fragment that show the Graph in the Mysurface  */

public class IntradayFragment extends Fragment {

    private static final String KEY_SYMB = "key_symb";

    private String mSymb;
    private StockViewModel stockViewModel;
    private View view;
    private TextView txtloading;

    public IntradayFragment() {
    }

    /**  get the symbol selected into argument in newInstance**/
    public static IntradayFragment newInstance(String paramsymb) {
        IntradayFragment fragment = new IntradayFragment();
        Bundle args = new Bundle();
        args.putString(KEY_SYMB, paramsymb);
        fragment.setArguments(args);
        return fragment;
    }

    /**  get the symbol selected from the argument **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSymb = getArguments().getString(KEY_SYMB);
        }
    }

    /** Inflate the layout  */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_intraday, container, false);
        TextView textView = (TextView)  view.findViewById(R.id.infotxtsymb);
        textView.setText(mSymb);
        txtloading = view.findViewById(R.id.idloading);
        return view;
    }


    /** onActivityCreated is after onCreateView
     *  so after inflate we get the stockViewModel from MainActivity
     *  we have the observer to subscribe and get data for the graph
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        stockViewModel= ((MainActivity) getActivity()).getModel();

        Observable<List<IntradayEntity>> obs = stockViewModel.getIntrdaDayPrices(mSymb);
        obs.subscribe(
                s -> {
                    txtloading.setVisibility(View.INVISIBLE);
                    MySurfaceView mySurfaceView = view.findViewById(R.id.idLasurface);
                    mySurfaceView.aggiornaPath(s);
                }
                , onError -> {  Log.d("APP","Error api updateNetData "+onError.toString());  }
        );
    }
}
