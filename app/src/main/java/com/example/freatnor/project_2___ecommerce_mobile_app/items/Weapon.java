package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/24/16.
 */
public abstract class Weapon extends Item{

    protected int mPhysicalAttack;
    protected int mMagicalAttack;
    protected int mRange;
    protected String mSlot;
    //could add a "requiresTwoHands" or "onlyEquippableInDominant" booleans for loadout screen

    public Weapon(String description, String name, int price, ItemQuality itemQuality, int physicalAttack,
                  int magicalAttack, int range, String imageId) {
        super(description, name, price, itemQuality, imageId);
        mPhysicalAttack = physicalAttack;
        mMagicalAttack = magicalAttack;
        mRange = range;
        mSlot = "hand";
    }

    public Weapon(String description, String name, int price, ItemQuality itemQuality, boolean owned, int physicalAttack,
                  int magicalAttack, int range, String imageId) {
        super(description, name, price, itemQuality, imageId);
        mPhysicalAttack = physicalAttack;
        mMagicalAttack = magicalAttack;
        mRange = range;
        mSlot = "hand";
    }

    public int getPhysicalAttack() {
        return mPhysicalAttack;
    }

    public int getMagicalAttack() {
        return mMagicalAttack;
    }

    public int getRange() {
        return mRange;
    }

    public String getSlot() {
        return mSlot;
    }

    public abstract int getItemOptimizedOffensiveWeight();

    public abstract int getItemOptimizedDefensiveWeight();
}
