package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Sword extends Weapon {


    public Sword(String description, String name, int price, ItemQuality itemQuality, int physicalAttack, int magicalAttack, int range, int imageId) {
        super(description, name, price, itemQuality, physicalAttack, magicalAttack, range, imageId);
    }

    public Sword(String description, String name, int price, ItemQuality itemQuality, boolean owned, int physicalAttack, int magicalAttack, int range, int imageId) {
        super(description, name, price, itemQuality, owned, physicalAttack, magicalAttack, range, imageId);
    }

    //no inherent defensive properties of the sword
    @Override
    public int getItemOptimizedDefensiveWeight() {
        return 0;
    }

    //sword weight for optimized loadout that slightly favors physical attack
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return mPhysicalAttack + (int) (mMagicalAttack * 0.75);
    }
}
