package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Accessory extends Item {

    private int mMagicAttack;
    private int mPhysicalAttack;
    private int mDefense;
    private int mMagicDefense;
    private String mSpecialAbility;
    private String mSlot = "Accessory";


    public Accessory(String description, String name, int price, ItemQuality itemQuality, int magicAttack,
                     int physicalAttack, int defense, int magicDefense, String specialAbility, int imageId) {
        super(description, name, price, itemQuality, imageId);
        mMagicAttack = magicAttack;
        mPhysicalAttack = physicalAttack;
        mDefense = defense;
        mMagicDefense = magicDefense;
        mSpecialAbility = specialAbility;
    }

    public Accessory(String description, String name, int price, ItemQuality itemQuality, boolean owned,
                     int magicAttack, int physicalAttack, int defense, int magicDefense, String specialAbility, int imageId) {
        super(description, name, price, itemQuality, imageId);
        mMagicAttack = magicAttack;
        mPhysicalAttack = physicalAttack;
        mDefense = defense;
        mMagicDefense = magicDefense;
        mSpecialAbility = specialAbility;
    }

    public int getMagicalAttack() {
        return mMagicAttack;
    }

    public int getPhysicalAttack() {
        return mPhysicalAttack;
    }

    public int getPhysicalDefense() {
        return mDefense;
    }

    public int getMagicalDefense() {
        return mMagicDefense;
    }

    public String getSpecialAbility() {
        return mSpecialAbility;
    }

    @Override
    public String getType() {
        return "Accessory";
    }

    public String getSlot() {
        return mSlot;
    }

    //Neither takes into account the special ability. Could do so in the future with a "SpecialAbility"
    //class that determines its own offensive and defensive weights
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return mMagicAttack + mPhysicalAttack;
    }

    @Override
    public int getItemOptimizedDefensiveWeight() {
        return mDefense + mMagicDefense;
    }

}
