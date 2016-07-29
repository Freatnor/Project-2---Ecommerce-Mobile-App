package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/24/16.
 */
public abstract class Item {

    //Enum to keep track of the items quality. Inherent to all items
    public enum ItemQuality{
        BRONZE(0), STEEL(1), MAGICAL(2);
        private int value;

        private ItemQuality(int value){
            this.value = value;
        }
        public String toString(){
            switch(value){
                case 0:
                    return "Bronze";
                case 1:
                    return "Steel";
                case 2:
                    return "Magical";
                default:
                    return "Unknown";
            }
        }
        public int getValue(){
            return value;
        }
    }


    private String mDescription;
    private String mName;
    private int mPrice;
    private ItemQuality mItemQuality;
    private String mImageId;

    public Item(String description, String name, int price, ItemQuality itemQuality, String imageId) {
        mDescription = description;
        mName = name;
        mPrice = price;
        mItemQuality = itemQuality;
        mImageId = imageId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public int getPrice() {
        return mPrice;
    }

    public ItemQuality getItemQuality() {
        return mItemQuality;
    }


    public String getImageId() {
        return mImageId;
    }

    //method for determining the optimum equipment for the user. Has one for both defense and offense
    public abstract int getItemOptimizedOffensiveWeight();

    public abstract int getItemOptimizedDefensiveWeight();

    public abstract String getSlot();

    public abstract int getPhysicalAttack();

    public abstract int getMagicalAttack();

    public abstract int getPhysicalDefense();

    public abstract int getMagicalDefense();

    public abstract String getSpecialAbility();

    public abstract String getType();

}
