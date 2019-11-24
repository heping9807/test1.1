package com.example.casper.lifeprice.data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casper.lifeprice.LifePriceMainActivity;
import com.example.casper.lifeprice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment {


    public BookListFragment() {
        private LifePriceMainActivity.theAdapter goodAdapter;
        public BookListFragment(LifePriceMainActivity.GoodAdapter goodAdapter);
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        theAdaper=new LifePriceMainActivity.GoodsArrayAdapter(this,R.layout.list_item_good,theGoods);
        listViewSuper.setAdapter(theAdaper);
        this.registerForContextMenu(listViewSuper);

        // Inflate the layout for this fragment
        return view;
    }

}
