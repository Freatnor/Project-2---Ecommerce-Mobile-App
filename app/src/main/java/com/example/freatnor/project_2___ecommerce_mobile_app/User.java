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
    private int mUserId;
    private ArrayList<Item> mInventory;
    private HashMap<String, Item> mEquippedItems;
    private int mGoldAmt;

    private static User mInstance;

    private User(ArrayList<Item> inventory, HashMap<String, Item> equippedItems, int goldAmt, int userId) {
        mInventory = inventory;
        mEquippedItems = equippedItems;
        mGoldAmt = goldAmt;
        mUserName = "Default";
        mUserId = userId;
    }

    public static User getUser(){
        if(mInstance == null){
            mInstance = new User(new ArrayList<Item>(), new HashMap<String, Item>(), 500, 1);
        }
        return mInstance;
    }

    //method for loading from storage. will require a check of the user object returned to see if it worked well
    public static User getUser(ArrayList<Item> inventory, int goldAmt){
        if(mInstance == null){
            mInstance = new User(inventory, new HashMap<String, Item>(), goldAmt, 1);
        }
        return mInstance;
    }

    public int getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public int getGoldAmt() {
        return mGoldAmt;
    }

    public void addGold(){
        mGoldAmt += 5000;
    }

    public void spendGold(int goldAmt){
        mGoldAmt -= goldAmt;
    }

    //methods for altering the slots for user equipment
    public Item getHeadItem(){
        return mEquippedItems.get("head");
    }
    
    public void putHeadItem(Item item){
        mEquippedItems.put("head", item);
    }

    public Item getChestItem(){
        return mEquippedItems.get("chest");
    }

    public void putChestItem(Item item){
        mEquippedItems.put("chest", item);
    }

    public Item getRightItem(){
        return mEquippedItems.get("right");
    }

    public void putRightItem(Item item){
        mEquippedItems.put("right", item);
    }

    public Item getLeftItem(){
        return mEquippedItems.get("left");
    }

    public void putLeftItem(Item item){
        mEquippedItems.put("left", item);
    }

    public Item getAccessory1Item(){
        return mEquippedItems.get("accessory1");
    }

    public void putAccessory1Item(Item item){
        mEquippedItems.put("accessory1", item);
    }

    public Item getAccessory2Item(){
        return mEquippedItems.get("accessory2");
    }

    public void putAccessory2Item(Item item){
        mEquippedItems.put("accessory2", item);
    }
}
