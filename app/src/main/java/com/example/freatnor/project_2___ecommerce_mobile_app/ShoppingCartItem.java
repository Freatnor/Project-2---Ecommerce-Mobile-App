package com.example.freatnor.project_2___ecommerce_mobile_app;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

/**
 * Created by Jonathan Taylor on 7/26/16.
 */
public class ShoppingCartItem {

    private Item mItem;
    private int mNumItems;

    public ShoppingCartItem(Item item, int numItems) {
        mItem = item;
        mNumItems = numItems;
    }

    public Item getItem() {
        return mItem;
    }

    public int getNumItems() {
        return mNumItems;
    }

    public void setNumItems(int numItems) {
        mNumItems = numItems;
    }

    public void increment(){
        mNumItems++;
    }

    public void decrement(){
        mNumItems--;
    }

    public int getTotalPrice(){
        return mItem.getPrice() * mNumItems;
    }
}
