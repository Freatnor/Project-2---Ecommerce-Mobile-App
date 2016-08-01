package com.example.freatnor.project_2___ecommerce_mobile_app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

/**
 * Created by Jonathan Taylor on 7/29/16.
 */
public class SlotSelectFragment extends Fragment {

    private UserLoadoutFragment.OnSlotItemClickedListener mListener;
    private String mSlot;
    private Item mCurrentItem;

    public static SlotSelectFragment getInstance(UserLoadoutFragment.OnSlotItemClickedListener listener, Item currentItem, String slot){
        SlotSelectFragment fragment = new SlotSelectFragment();
        fragment.mListener = listener;
        fragment.mSlot = slot;
        fragment.mCurrentItem = currentItem;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
