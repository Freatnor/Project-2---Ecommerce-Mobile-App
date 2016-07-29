package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;
import com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses.ItemRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/28/16.
 */
public class ShopFragment extends Fragment {

    private ArrayList<Item> mItems;

    public interface OnAddToCartListener{
        void onAddToCart(int position);
    }

    public interface OnDetailRequestedListener {
        void detailRequested(int position);
    }

    public interface OnSearchListener{
        void onSearch(ArrayList<Item> items);
    }

    private View.OnClickListener mListener;
    private OnAddToCartListener mAddToCartListener;
    private OnDetailRequestedListener mDetailListener;
    private OnSearchListener mOnSearchListener;

    private RecyclerView mRecyclerView;
    private TextView mTotalPrice;
    private Button mGoToCartButton;

    private ItemRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mManager;
    private Context mContext;


    public static ShopFragment getInstance(View.OnClickListener listener, OnAddToCartListener addToCartListener,
                                           OnDetailRequestedListener detailListener, Context context, ArrayList<Item> items,
                                           OnSearchListener searchListener){
        ShopFragment fragment = new ShopFragment();
        fragment.mListener = listener;
        fragment.mAddToCartListener = addToCartListener;
        fragment.mDetailListener = detailListener;
        fragment.mContext = context;
        fragment.mItems = items;
        fragment.mOnSearchListener = searchListener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_shop, container, false);
        mRecyclerView = (RecyclerView) parentView.findViewById(R.id.shop_fragment_recycler_view);
        mTotalPrice = (TextView) parentView.findViewById(R.id.shop_total_price_text_view);
        mGoToCartButton = (Button) parentView.findViewById(R.id.go_to_cart_button);

        mAdapter = new ItemRecyclerViewAdapter(mAddToCartListener, mDetailListener, mOnSearchListener, mItems);
        mManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTotalPrice.setText(ShoppingCart.getInstance(this.getContext()).getTotalPrice() + "");
        mGoToCartButton.setOnClickListener(mListener);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);
    }


}
