package com.example.freatnor.project_2___ecommerce_mobile_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.freatnor.project_2___ecommerce_mobile_app.ShoppingCartItem;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/27/16.
 */
public class FantasyShopDatabaseHelper extends SQLiteOpenHelper {

    public static final String FANTASY_SHOP_RECORDS_DB = "FantasyShopRecords.db";
    public static final int DB_VERSION = 1;

    public static final String USER_INVENTORY_TABLE_NAME = "user_inventory";
    public static final String COL_INVENTORY_ITEM_ID = "item_id";
    public static final String COL_FOREIGN_USER_ID = "user_id"; //These two create a unique key id

    public static final String USER_TABLE_NAME = "user";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_CURRENT_GOLD = "gold";
    public static final String COL_HEAD = "head_id";
    public static final String COL_CHEST = "chest_id";
    public static final String COL_RIGHT_HAND = "right_id";
    public static final String COL_LEFT_HAND = "left_id";
    public static final String COL_ACCESSORY1 = "acc1_id";
    public static final String COL_ACCESSORY2 = "acc2_id";

    public static final String SHOPPING_CART_TABLE_NAME = "shopping_cart";
    public static final String COL_SHOPPING_CART_ID = "shopping_cart_id";
    public static final String COL_SHOPPING_CART_USER = "user_id";

    public static final String SHOPPING_CART_INVENTORY_TABLE_NAME = "shopping_cart_inventory";
    public static final String COL_SHOPPING_CART_ITEM_ID = "item_id";
    public static final String COL_FOREIGN_SHOPPING_CART_ID = "shopping_cart_id";
    public static final String COL_ITEM_AMOUNT = "item_amount";

    public static final String ITEMS_TABLE_NAME = "items";
    public static final String COL_ITEM_ID = "item_id";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_ITEM_NAME = "name";
    public static final String COL_ITEM_PRICE = "price";
    public static final String COL_ITEM_QUALITY = "quality";
    public static final String COL_ITEM_TYPE = "type";
    public static final String COL_ITEM_SLOT = "slot";
    public static final String COL_PHYSICAL_ATTACK = "physical_attack";
    public static final String COL_MAGIC_ATTACK = "magical_attack";
    public static final String COL_PHYSICAL_DEFENSE = "physical_defense";
    public static final String COL_MAGIC_DEFENSE = "magical_defense";
    public static final String COL_SPECIAL_ABILITY = "special_ability";
    public static final String COL_RANGE = "range";




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

    private static final String SQL_CREATE_ENTRIES_ITEMS = "CREATE TABLE " +
            ITEMS_TABLE_NAME + " (" +
            COL_ITEM_ID + " INTEGER PRIMARY KEY," +
            COL_ITEM_NAME + " TEXT," +
            COL_DESCRIPTION + " TEXT," +
            COL_ITEM_PRICE + " INTEGER," +
            COL_ITEM_QUALITY + " TEXT," +
            COL_ITEM_TYPE + " TEXT," +
            COL_ITEM_SLOT + " TEXT," +
            COL_PHYSICAL_ATTACK + " INTEGER," +
            COL_MAGIC_ATTACK + " INTEGER," +
            COL_PHYSICAL_DEFENSE + " INTEGER," +
            COL_MAGIC_DEFENSE + " INTEGER," +
            COL_RANGE + " INTEGER," +
            COL_SPECIAL_ABILITY + " TEXT" + ")";

    private static final String SQL_CREATE_ENTRIES_USER = "CREATE TABLE " +
            USER_TABLE_NAME + " (" +
            COL_USER_ID + " INTEGER PRIMARY KEY," +
            COL_USER_NAME + " TEXT," +
            COL_CURRENT_GOLD + " INTEGER" +
            COL_HEAD + " INTEGER," +
            COL_CHEST + " INTEGER," +
            COL_RIGHT_HAND + " INTEGER," +
            COL_LEFT_HAND + " INTEGER," +
            COL_ACCESSORY1 + " INTEGER," +
            COL_ACCESSORY2 + " INTEGER" +
            "FOREIGN KEY("+ COL_HEAD +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_CHEST +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_RIGHT_HAND +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_LEFT_HAND +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_ACCESSORY1 +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_ACCESSORY2 +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )";

    private static final String SQL_CREATE_ENTRIES_SHOPPING_CART = "CREATE TABLE " +
            SHOPPING_CART_TABLE_NAME + " (" +
            COL_SHOPPING_CART_ID + " INTEGER PRIMARY KEY," +
            COL_SHOPPING_CART_USER + " INTEGER," +
            "FOREIGN KEY("+ COL_SHOPPING_CART_USER +") REFERENCES "+ USER_TABLE_NAME+"("+ COL_USER_ID +") )";

    private static final String SQL_CREATE_ENTRIES_SHOPPING_CART_INVENTORY = "CREATE TABLE " +
            SHOPPING_CART_INVENTORY_TABLE_NAME + " (" +
            COL_SHOPPING_CART_ITEM_ID + " INTEGER PRIMARY KEY," +
            COL_FOREIGN_SHOPPING_CART_ID + " INTEGER PRIMARY KEY," +
            COL_ITEM_AMOUNT + " INTEGER," +
            "FOREIGN KEY("+ COL_SHOPPING_CART_ITEM_ID +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_FOREIGN_SHOPPING_CART_ID +") REFERENCES "+ SHOPPING_CART_TABLE_NAME+"("+ COL_ITEM_ID +") )";

    private static final String SQL_CREATE_ENTRIES_USER_INVENTORY = "CREATE TABLE " +
            USER_INVENTORY_TABLE_NAME + " (" +
            COL_INVENTORY_ITEM_ID + " INTEGER PRIMARY KEY," +
            COL_FOREIGN_USER_ID + " INTEGER PRIMARY KEY," +
            "FOREIGN KEY("+ COL_SHOPPING_CART_ITEM_ID +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +") )," +
            "FOREIGN KEY("+ COL_FOREIGN_SHOPPING_CART_ID +") REFERENCES "+ SHOPPING_CART_TABLE_NAME+"("+ COL_ITEM_ID +") )";

    private static final String SQL_DELETE_ENTRIES_ITEMS = "DROP TABLE IF EXISTS " +
            ITEMS_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_USER = "DROP TABLE IF EXISTS " +
            USER_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_SHOPPING_CART = "DROP TABLE IF EXISTS " +
            SHOPPING_CART_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_SHOPPING_CART_INVENTORY = "DROP TABLE IF EXISTS " +
            SHOPPING_CART_INVENTORY_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_USER_INVENTORY = "DROP TABLE IF EXISTS " +
            USER_INVENTORY_TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_ITEMS);
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_SHOPPING_CART);
        db.execSQL(SQL_CREATE_ENTRIES_SHOPPING_CART_INVENTORY);
        db.execSQL(SQL_CREATE_ENTRIES_USER_INVENTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_ITEMS);
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_SHOPPING_CART);
        db.execSQL(SQL_DELETE_ENTRIES_SHOPPING_CART_INVENTORY);
        db.execSQL(SQL_DELETE_ENTRIES_USER_INVENTORY);
        onCreate(db);
    }

    public void insertItem(Item item, SQLiteDatabase db){
        boolean needsClose = false;
        if(db == null){
            db = getWritableDatabase();
            needsClose = true;
        }
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_ID, item.getImageId());
        values.put(COL_ITEM_NAME, item.getName());
        values.put(COL_DESCRIPTION, item.getDescription());
        values.put(COL_ITEM_PRICE, item.getPrice());
        values.put(COL_ITEM_QUALITY, item.getItemQuality().getValue());
        try{db.insertOrThrow(ITEMS_TABLE_NAME, null, values);}
        catch(Exception e){
            Log.e("Insert", "insertRow: unable to insert because of unique issue", e);
        }
        if(needsClose){
            db.close();
        }
    }

    public void insertItems(ArrayList<Item> items){
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < items.size(); i++) {
            insertItem(items.get(i), db);
        }
        close();
    }

    public ArrayList<Item> getShopItems(){

    }

    public ArrayList<Item> getInventoryItems(){

    }

    public ArrayList<ShoppingCartItem> getShoppingCartItems(){

    }

    public ArrayList<Item> getItemsByName(String name, int source){

    }

    public ArrayList<Item> getItemsByType(String type, int source){

    }

    public ArrayList<Item> getItemsByQuality(int quality, int source){

    }

    //searches for items with a price within 10 gold of the entered number
    public ArrayList<Item> getItemsByPrice(int price, int source){

    }

    public ArrayList<Item> getItemsByDescription(String description, int source){

    }
}
