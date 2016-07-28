package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public abstract class Armor extends Item {

    protected int mDefense;
    protected int mMagicDefense;
    //slots can be different for armors so this is important in the constructor
    protected String mSlot;

    public Armor(String description, String name, int price, ItemQuality itemQuality, int defense,
                 int magicDefense, String slot, int imageId) {
        super(description, name, price, itemQuality, imageId);
        mDefense = defense;
        mMagicDefense = magicDefense;
        this.mSlot = slot;
    }

    public Armor(String description, String name, int price, ItemQuality itemQuality, boolean owned,
                 int defense, int magicDefense, String slot, int imageId) {
        super(description, name, price, itemQuality, imageId);
        mDefense = defense;
        mMagicDefense = magicDefense;
        this.mSlot = slot;
    }

    public int getPhysicalDefense() {
        return mDefense;
    }

    public int getMagicalDefense() {
        return mMagicDefense;
    }

    public String getSlot() {
        return mSlot;
    }

    public abstract int getItemOptimizedOffensiveWeight();

    public abstract int getItemOptimizedDefensiveWeight();

}
