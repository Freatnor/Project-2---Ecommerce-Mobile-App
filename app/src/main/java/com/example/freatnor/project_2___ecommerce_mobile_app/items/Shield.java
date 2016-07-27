package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Shield extends Weapon {

    private int mDefense;
    private int mMagicDefense;

    public Shield(String description, String name, int price, ItemQuality itemQuality, int physicalAttack,
                  int magicalAttack, int range, int defense, int magicDefense, int imageId) {
        super(description, name, price, itemQuality, physicalAttack, magicalAttack, range, imageId);
        mDefense = defense;
        mMagicDefense = magicDefense;
    }

    public Shield(String description, String name, int price, ItemQuality itemQuality, boolean owned,
                  int physicalAttack, int magicalAttack, int range, int defense, int magicDefense, int imageId) {
        super(description, name, price, itemQuality, owned, physicalAttack, magicalAttack, range, imageId);
        mDefense = defense;
        mMagicDefense = magicDefense;
    }

    public int getPhysicalDefense() {
        return mDefense;
    }

    public int getMagicalDefense() {
        return mMagicDefense;
    }

    @Override
    public String getSpecialAbility() {
        return null;
    }

    //heavily emphasizes defensive stats so these stats aren't important
    @Override
    public int getItemOptimizedOffensiveWeight() {
        return ((int) 0.25 * (mMagicalAttack + mPhysicalAttack));
    }

    //shields love defense so add together the defenzive values
    @Override
    public int getItemOptimizedDefensiveWeight() {
        return mDefense + mMagicDefense;
    }
}
