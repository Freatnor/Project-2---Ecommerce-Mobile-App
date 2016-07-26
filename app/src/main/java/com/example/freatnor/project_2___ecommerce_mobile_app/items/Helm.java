package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Helm extends Armor {


    public Helm(String description, String name, int price, ItemQuality itemQuality, int defense, int magicDefense, int imageId) {
        super(description, name, price, itemQuality, defense, magicDefense, "head", imageId);
    }

    public Helm(String description, String name, int price, ItemQuality itemQuality, boolean owned, int defense, int magicDefense, int imageId) {
        super(description, name, price, itemQuality, owned, defense, magicDefense, "head", imageId);
    }

    //Helms don't do anything for offense..for now
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return 0;
    }

    //standard defensive
    @Override
    public int getItemOptimizedDefensiveWeight() {
        return mMagicDefense + mDefense;
    }
}
