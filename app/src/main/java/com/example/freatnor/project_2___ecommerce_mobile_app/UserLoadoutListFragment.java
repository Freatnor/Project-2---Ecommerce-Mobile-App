package com.example.freatnor.project_2___ecommerce_mobile_app;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;
import com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses.ItemRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/29/16.
 */
public class UserLoadoutListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Item> mItems;
    private ItemRecyclerViewAdapter mAdapter;

    private ShopFragment.OnDetailRequestedListener mDetailListener;


    public static UserLoadoutListFragment getInstance(ShopFragment.OnDetailRequestedListener detailListener,
                                                      ArrayList<Item> items) {
        UserLoadoutListFragment fragment = new UserLoadoutListFragment();
        fragment.mDetailListener = detailListener;
        fragment.mItems = items;


        return fragment;
    }



    public void refreshItemList(ArrayList<Item> newItems) {
        mAdapter.refreshItemList(newItems);
        Log.d("ShopFragment", "refreshItemList: Trying to call refresh on the adapter");
    }
}
