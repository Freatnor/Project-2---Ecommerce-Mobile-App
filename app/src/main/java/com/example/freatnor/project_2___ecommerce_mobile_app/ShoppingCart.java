package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.Context;

import com.example.freatnor.project_2___ecommerce_mobile_app.database.FantasyShopDatabaseHelper;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;
import com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses.ShoppingCartItemRecyclerViewAdapter;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Singleton? If there are multiple users maybe it won't be a singleton anymore...
 * Created by Jonathan Taylor on 7/26/16.
 */
public class ShoppingCart {

    private static ShoppingCart mInstance;
    private ArrayList<ShoppingCartItem> mItems;
    private int mShoppingCartId;
    private Context mContext;
    private FantasyShopDatabaseHelper mHelper;

    private ShoppingCartChangeListener mListener;


    public interface ShoppingCartChangeListener{
        void notifyCartItemDeleted(int position);
        void notifyCartItemChanged(int position);
        void notifyCartItemAdded(int position);
        void notifyCartCleared();
    }


    //id set to 1 since only 1 shopping cart in use
    private ShoppingCart(Context context){
        mItems = mHelper.getShoppingCartItems(this);
        mShoppingCartId = 1;
        mContext = context;
        mHelper = FantasyShopDatabaseHelper.getInstance(mContext);
    }

    public static ShoppingCart getInstance(Context context){
        if(mInstance == null){
            mInstance = new ShoppingCart(context.getApplicationContext());
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
        ShoppingCartItem newItem = new ShoppingCartItem(item, numItems);
        mItems.add(newItem);
        mHelper.insertOrUpdateShoppingCartItem(new ShoppingCartItem(item, numItems), null);
        mListener.notifyCartItemAdded(mItems.size() - 1);
    }

    public ShoppingCartItem getItem(int position){
        return mItems.get(position);
    }

    public int getIndex(Item item){
        for (ShoppingCartItem shoppingCartItem : mItems) {
            if (shoppingCartItem.getItem().getName().equals(item.getName())) {
                return mItems.indexOf(shoppingCartItem);
            }
        }
        return -1;
    }

    public void removeItem(int position){
        mHelper.removeShoppingCartItem(mItems.get(position));
        mItems.remove(position);
        mListener.notifyCartItemDeleted(position);
    }

    //not sure if I need this, but it's a method to delete a cartitem based on the item passed in
    public void removeItem(Item item){
        //loop through and check if the item exits, delete it and exit loop if it does;
        for (ShoppingCartItem shoppingCartItem : mItems) {
            if(shoppingCartItem.getItem().getName().equals(item.getName())){
                mHelper.removeShoppingCartItem(shoppingCartItem);
                mItems.remove(shoppingCartItem);
                break;
            }
        }
    }

    public void incrementItem(int position){
        mItems.get(position).increment();
        mHelper.insertOrUpdateShoppingCartItem(mItems.get(position), null);
        mListener.notifyCartItemChanged(position);
    }

    public void setItemAmount(int position, int newValue){
        mItems.get(position).setNumItems(newValue);
        if(mItems.get(position).getNumItems() <= 0){
            removeItem(position);
            mListener.notifyCartItemDeleted(position);
        }
        else {
            mHelper.insertOrUpdateShoppingCartItem(mItems.get(position), null);
            mListener.notifyCartItemChanged(position);
        }
    }

    public void decrementItem(int position){
        mItems.get(position).decrement();
        if(mItems.get(position).getNumItems() <= 0){
            removeItem(position);
            mListener.notifyCartItemDeleted(position);
        }
        else {
            mHelper.insertOrUpdateShoppingCartItem(mItems.get(position), null);
            mListener.notifyCartItemChanged(position);
        }
    }

    public void clearCart(){
        mItems = new ArrayList<>();
        mHelper.removeAllShoppingCartItems();
        mListener.notifyCartCleared();
    }


    public void purchaseItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            items.add(mItems.get(i).getItem());
        }
        mHelper.insertInventoryItems(items);
        clearCart();
    }

    public int getItemAmount(int position){
        return mItems.get(position).getNumItems();
    }

    public ArrayList<ShoppingCartItem> getItems() {
        return mItems;
    }

    public int getShoppingCartId() {
        return mShoppingCartId;
    }

    public void setListener(ShoppingCartChangeListener listener){
        mListener = listener;
    }

    public int size(){
        return mItems.size();
    }
}
