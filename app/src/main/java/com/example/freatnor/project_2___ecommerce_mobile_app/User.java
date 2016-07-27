package com.example.freatnor.project_2___ecommerce_mobile_app;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.RealmObject;

/**
 * like Shopping Cart it is for now a singleton
 * Created by Jonathan Taylor on 7/25/16.
 */
public class User {

    private String mUserName;
    private ArrayList<Item> mInventory;
    private HashMap<String, Item> mEquippedItems;
    private int mGoldAmt;
    private ShoppingCart mShoppingCart;

    private static User mInstance;

    private User(ArrayList<Item> inventory, HashMap<String, Item> equippedItems, int goldAmt, ShoppingCart shoppingCart) {
        mInventory = inventory;
        mEquippedItems = equippedItems;
        mGoldAmt = goldAmt;
        mShoppingCart = shoppingCart;
        mUserName = "Default";
    }

    public static User getUser(){
        if(mInstance == null){
            mInstance = new User(new ArrayList<Item>(), new HashMap<String, Item>(), 0, ShoppingCart.getInstance());
        }
        return mInstance;
    }

    //method for loading from storage. will require a check of the user object returned to see if it worked well
    public static User getUser(ArrayList<Item> inventory, HashMap<String, Item> equippedItems, int goldAmt){
        if(mInstance == null){
            mInstance = new User(inventory, equippedItems, goldAmt, ShoppingCart.getInstance());
        }
        return mInstance;
    }
}
