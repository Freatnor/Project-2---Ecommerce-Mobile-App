package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/24/16.
 */
public class Weapon extends Item{

    private int mPhysicalAttack;
    private int mMagicalAttack;
    private int mRange;
    private String mSlot;

    public Weapon(String description, String name, int price, ItemQuality itemQuality, int physicalAttack,
                  int magicalAttack, int range, String slot) {
        super(description, name, price, itemQuality);
        mPhysicalAttack = physicalAttack;
        mMagicalAttack = magicalAttack;
        mRange = range;
        mSlot = slot;
    }

    public Weapon(String description, String name, int price, ItemQuality itemQuality, boolean owned, int physicalAttack,
                  int magicalAttack, int range, String slot) {
        super(description, name, price, itemQuality, owned);
        mPhysicalAttack = physicalAttack;
        mMagicalAttack = magicalAttack;
        mRange = range;
        mSlot = slot;
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
}
