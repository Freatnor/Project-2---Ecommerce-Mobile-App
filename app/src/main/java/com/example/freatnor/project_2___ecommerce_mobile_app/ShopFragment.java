package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses.ItemRecyclerViewAdapter;

/**
 * Created by Jonathan Taylor on 7/28/16.
 */
public class ShopFragment extends Fragment {

    private View.OnClickListener mListener;
    private View.OnClickListener mAddToCartListener;
    private View.OnClickListener mDetailListener;

    private RecyclerView mRecyclerView;
    private TextView mTotalPrice;
    private Button mGoToCartButton;

    private ItemRecyclerViewAdapter mAdapter;

    private ShoppingCart mCart;

    public ShopFragment getInstance(View.OnClickListener listener, View.OnClickListener addToCartListener,
                                    View.OnClickListener detailListener){
        ShopFragment fragment = new ShopFragment();
        fragment.mListener = listener;
        fragment.mAddToCartListener = addToCartListener;
        fragment.mDetailListener = detailListener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_shop, container, false);
        mRecyclerView = (RecyclerView) parentView.findViewById(R.id.shop_fragment_recycler_view);
        mTotalPrice = (TextView) parentView.findViewById(R.id.shop_total_price_text_view);
        mGoToCartButton = (Button) parentView.findViewById(R.id.add_to_cart_or_equip_button);

        mAdapter = new ItemRecyclerViewAdapter(mAddToCartListener, mDetailListener);

        mCart = ShoppingCart.getInstance();

        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTotalPrice.setText(mCart.getTotalPrice());
        mGoToCartButton.setOnClickListener(mListener);

        mRecyclerView.setAdapter(mAdapter);
    }
}
