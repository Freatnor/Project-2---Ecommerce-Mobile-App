package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/25/16.
 */
public class Hat extends Armor {

    private int mMagicAttack;
    private String mSpecialAbility;

    //like Helm the two constructors pass a pre-defined slot of "head"
    public Hat(String description, String name, int price, ItemQuality itemQuality, int defense, int magicDefense, int magicAttack, String specialAbility, String imageId) {
        super(description, name, price, itemQuality, defense, magicDefense, "head", imageId);
        mMagicAttack = magicAttack;
        mSpecialAbility = specialAbility;
    }

    public Hat(String description, String name, int price, ItemQuality itemQuality, boolean owned,
               int defense, int magicDefense, int magicAttack, String specialAbility, String imageId) {
        super(description, name, price, itemQuality, owned, defense, magicDefense, "head", imageId);
        mMagicAttack = magicAttack;
        mSpecialAbility = specialAbility;
    }

    public int getMagicalAttack() {
        return mMagicAttack;
    }

    public String getSpecialAbility() {
        return mSpecialAbility;
    }

    @Override
    public String getType() {
        return "Hat";
    }

    //hats actually grant magic attack
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
}
