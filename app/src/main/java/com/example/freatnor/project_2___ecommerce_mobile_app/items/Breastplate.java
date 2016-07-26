package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Breastplate extends Armor {

    //breastplates are body armor, so go in the "chest" slot
    public Breastplate(String description, String name, int price, ItemQuality itemQuality, int defense, int magicDefense, int imageId) {
        super(description, name, price, itemQuality, defense, magicDefense, "chest", imageId);
    }

    public Breastplate(String description, String name, int price, ItemQuality itemQuality, boolean owned, int defense, int magicDefense, int imageId) {
        super(description, name, price, itemQuality, owned, defense, magicDefense, "chest", imageId);
    }

    //not spiked so no offensive use
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return 0;
    }

    //normal defensive stats for calculating optimum equip
    @Override
    public int getItemOptimizedDefensiveWeight() {
        return mMagicDefense + mDefense;
    }
}
