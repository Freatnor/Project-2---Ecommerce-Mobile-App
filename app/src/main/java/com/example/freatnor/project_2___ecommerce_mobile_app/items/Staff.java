package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Staff extends Weapon {

    private String mSpecialAbility;

    public Staff(String description, String name, int price, ItemQuality itemQuality, int physicalAttack,
                 int magicalAttack, int range, String specialAbility, int imageId) {
        super(description, name, price, itemQuality, physicalAttack, magicalAttack, range, imageId);
        mSpecialAbility = specialAbility;
    }

    public Staff(String description, String name, int price, ItemQuality itemQuality, boolean owned,
                 int physicalAttack, int magicalAttack, int range, String specialAbility, int imageId) {
        super(description, name, price, itemQuality, owned, physicalAttack, magicalAttack, range, imageId);
        mSpecialAbility = specialAbility;
    }

    public String getSpecialAbility() {
        return mSpecialAbility;
    }

    //Staff optimal weight, heavily emphasizes magic
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return ((int) 0.5 * mPhysicalAttack) + (mMagicalAttack * 2);
    }

    @Override
    public int getItemOptimizedDefensiveWeight() {
        return 0;
    }
}
