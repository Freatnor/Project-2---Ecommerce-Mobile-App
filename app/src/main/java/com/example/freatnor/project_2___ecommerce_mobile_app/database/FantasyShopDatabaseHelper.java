package com.example.freatnor.project_2___ecommerce_mobile_app.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonathan Taylor on 7/27/16.
 */
public class FantasyShopDatabaseHelper extends SQLiteOpenHelper {

    public static final String FANTASY_SHOP_RECORDS_DB = "FantasyShopRecords.db";
    public static final int DB_VERSION = 1;

    public static final String USER_INVENTORY_TABLE_NAME = "user_inventory";
    public static final String COL_ITEM_ID = "item_id";
    public static final String COL_FOREIGN_USER_ID = "user_id"; //These two create a unique key id
    public static final String COL_HEAD = "head_id";
    public static final String COL_CHEST = "chest_id";
    public static final String COL_RIGHT_HAND = "right_id";
    public static final String COL_LEFT_HAND = "left_id";
    public static final String COL_ACCESSORY1 = "acc1_id";
    public static final String COL_ACCESSORY2 = "acc2_id";

    public static final String USER_TABLE_NAME = "user";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_CURRENT_GOLD = "gold";

    public static final String SHOPPING_CART_TABLE_NAME = "shopping_cart";
    public static final String COL_SHOPPING_CART_ID = "shopping_cart_id";
    public static final String COL_SHOPPING_CART_USER = "user_id";

    public static final String SHOPPING_CART_INVENTORY_TABLE_NAME = "shopping_cart_inventory";
    public static final String COL_SHOPPING_CART_ITEM_ID = "item_id";
    public static final String COL_FOREIGN_SHOPPING_CART_ID = "shopping_cart_id";
    public static final String COL_ITEM_AMOUNT = "item_amount";

    public static final String ITEMS_TABLE_NAME = "items";
    public static final String COL_



    //constants for saying whether for shop or inventory sorting
    public static final int SELECT_FROM_SHOP = 1;
    public static final int SELECT_FROM_INVENTORY = 2;

    private static FantasyShopDatabaseHelper mInstance;

    private FantasyShopDatabaseHelper(Context context) {
        super(context, FANTASY_SHOP_RECORDS_DB, null, DB_VERSION);
    }

    public static FantasyShopDatabaseHelper getInstance(Context context) {
        if(mInstance == null){
            mInstance = new FantasyShopDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getShopItems(){

    }

    public Cursor getInventoryItems(){

    }

    public Cursor getItemsByName(String name, int source){

    }

    public Cursor getItemsByType(String type, int source){

    }

    public Cursor getItemsByQuality(int quality, int source){

    }

    //searches for items with a price within 10 gold of the entered number
    public Cursor getItemsByPrice(int price, int source){

    }

    public Cursor getItemsByDescription(String description, int source){

    }
}
