package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Robe extends Armor {

    private int mMagicAttack;

    //Robes are only for bodies, not heads!
    public Robe(String description, String name, int price, ItemQuality itemQuality, int defense, int magicDefense, int magicAttack, String imageId) {
        super(description, name, price, itemQuality, defense, magicDefense, "chest", imageId);
        mMagicAttack = magicAttack;
    }

    //robes like hats have a magic attack modifier so they have some offensive value
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return mMagicAttack;
    }

    @Override
    public int getItemOptimizedDefensiveWeight() {
        return mMagicDefense + mDefense;
    }

    @Override
    public int getPhysicalAttack() {
        return 0;
    }

    @Override
    public int getMagicalAttack() {
        return mMagicAttack;
    }

    @Override
    public String getSpecialAbility() {
        return null;
    }

    @Override
    public String getType() {
        return "Robe";
    }
}
