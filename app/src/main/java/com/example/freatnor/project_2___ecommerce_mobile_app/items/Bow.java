package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Bow extends Weapon {

    public Bow(String description, String name, int price, ItemQuality itemQuality, int physicalAttack, int magicalAttack, int range, String imageId) {
        super(description, name, price, itemQuality, physicalAttack, magicalAttack, range, imageId);
    }

    public Bow(String description, String name, int price, ItemQuality itemQuality, boolean owned,
               int physicalAttack, int magicalAttack, int range, String imageId) {
        super(description, name, price, itemQuality, owned, physicalAttack, magicalAttack, range, imageId);
    }

    //Value for determining optimal equipment. Bows modified by range
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return (mPhysicalAttack * (int) (0.1 * mRange)) + (int) (mMagicalAttack * 0.75) ;
    }

    @Override
    public int getItemOptimizedDefensiveWeight() {
        return 0;
    }

    @Override
    public int getPhysicalDefense() {
        return 0;
    }

    @Override
    public int getMagicalDefense() {
        return 0;
    }

    @Override
    public String getSpecialAbility() {
        return null;
    }

    @Override
    public String getType() {
        return "Bow";
    }
}
