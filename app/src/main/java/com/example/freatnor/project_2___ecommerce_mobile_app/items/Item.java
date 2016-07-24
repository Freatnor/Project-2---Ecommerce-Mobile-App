package com.example.freatnor.project_2___ecommerce_mobile_app.items;

/**
 * Created by Jonathan Taylor on 7/24/16.
 */
public class Item {

    //Enum to keep track of the items quality. Inherent to all items
    public enum ItemQuality{
        BRONZE(0), STEEL(1), MAGICAL(2);
        private int value;

        private ItemQuality(int value){
            this.value = value;
        }
    }

    private String mDescription;
    private String mName;
    private int mPrice;
    private ItemQuality mItemQuality;
    private boolean mOwned;

    public Item(String description, String name, int price, ItemQuality itemQuality) {
        mDescription = description;
        mName = name;
        mPrice = price;
        mItemQuality = itemQuality;
        mOwned = false;
    }

    /**
     * Special Constructor for loading from storage
     */
    public Item(String description, String name, int price, ItemQuality itemQuality, boolean owned) {
        mDescription = description;
        mName = name;
        mPrice = price;
        mItemQuality = itemQuality;
        mOwned = owned;
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

    public boolean isOwned() {
        return mOwned;
    }

    public void setOwned(boolean owned) {
        mOwned = owned;
    }
}
