package com.example.freatnor.project_2___ecommerce_mobile_app;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Singleton? If there are multiple users maybe it won't be a singleton anymore...
 * Created by Jonathan Taylor on 7/26/16.
 */
public class ShoppingCart extends RealmObject {

    private static ShoppingCart mInstance;
    private ArrayList<ShoppingCartItem> mItems;


    private ShoppingCart(){
        mItems = new ArrayList<>();
    }

    public static ShoppingCart getInstance(){
        if(mInstance == null){
            mInstance = new ShoppingCart();
        }
        return mInstance;
    }

    //adds up each individual items
    public int getTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < mItems.size(); i++) {
            totalPrice += mItems.get(i).getTotalPrice();
        }
        return totalPrice;
    }

    //creates a new shopping cart item for the shopping cart. Will generally be used with 0 for
//    num items but could be changed for loading from storage
    public void addItem(Item item, int numItems){
        mItems.add(new ShoppingCartItem(item, numItems));
    }

    public void removeItem(int position){
        mItems.remove(position);
    }

    //not sure if I need this, but it's a method to delete a cartitem based on the item passed in
    public void removeItem(Item item){
        //loop through and check if the item exits, delete it and exit loop if it does;
        for (ShoppingCartItem shoppingCartItem : mItems) {
            if(shoppingCartItem.getItem().getName().equals(item.getName())){
                mItems.remove(shoppingCartItem);
                break;
            }
        }
    }

    public void incrementItem(int position){
        mItems.get(position).increment();
    }

    public void setItemAmount(int position, int newValue){
        mItems.get(position).setNumItems(newValue);
    }

    public void decrementItem(int position){
        mItems.get(position).decrement();
    }

    public int getItemAmount(int position){
        return mItems.get(position).getNumItems();
    }

    public ArrayList<ShoppingCartItem> getItems() {
        return mItems;
    }
}
