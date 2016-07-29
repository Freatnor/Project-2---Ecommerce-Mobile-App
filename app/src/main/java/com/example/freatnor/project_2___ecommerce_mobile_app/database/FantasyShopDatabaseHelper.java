package com.example.freatnor.project_2___ecommerce_mobile_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.freatnor.project_2___ecommerce_mobile_app.R;
import com.example.freatnor.project_2___ecommerce_mobile_app.ShoppingCart;
import com.example.freatnor.project_2___ecommerce_mobile_app.ShoppingCartItem;
import com.example.freatnor.project_2___ecommerce_mobile_app.User;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Accessory;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Bow;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Breastplate;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Hat;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Helm;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Robe;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Shield;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Staff;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Sword;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

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
    public static final String COL_IMAGE_NAME = "image_name";




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
            COL_ITEM_NAME + " TEXT PRIMARY KEY," +
            COL_DESCRIPTION + " TEXT," +
            COL_ITEM_PRICE + " INTEGER," +
            COL_ITEM_QUALITY + " INTEGER," +
            COL_ITEM_TYPE + " TEXT," +
            COL_ITEM_SLOT + " TEXT," +
            COL_PHYSICAL_ATTACK + " INTEGER," +
            COL_MAGIC_ATTACK + " INTEGER," +
            COL_PHYSICAL_DEFENSE + " INTEGER," +
            COL_MAGIC_DEFENSE + " INTEGER," +
            COL_RANGE + " INTEGER," +
            COL_SPECIAL_ABILITY + " TEXT, " +
            COL_IMAGE_NAME + " TEXT " + ")";

    private static final String SQL_CREATE_ENTRIES_USER = "CREATE TABLE " +
            USER_TABLE_NAME + " (" +
            COL_USER_ID + " INTEGER PRIMARY KEY," +
            COL_USER_NAME + " TEXT," +
            COL_CURRENT_GOLD + " INTEGER," +
            COL_HEAD + " TEXT," +
            COL_CHEST + " TEXT," +
            COL_RIGHT_HAND + " TEXT," +
            COL_LEFT_HAND + " TEXT," +
            COL_ACCESSORY1 + " TEXT," +
            COL_ACCESSORY2 + " TEXT," +
            "FOREIGN KEY("+ COL_HEAD +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_ITEM_NAME +")," +
            "FOREIGN KEY("+ COL_CHEST +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_ITEM_NAME +")," +
            "FOREIGN KEY("+ COL_RIGHT_HAND +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_ITEM_NAME +")," +
            "FOREIGN KEY("+ COL_LEFT_HAND +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_ITEM_NAME +")," +
            "FOREIGN KEY("+ COL_ACCESSORY1 +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_ITEM_NAME +")," +
            "FOREIGN KEY("+ COL_ACCESSORY2 +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_ITEM_NAME +") )";

    private static final String SQL_CREATE_ENTRIES_SHOPPING_CART = "CREATE TABLE " +
            SHOPPING_CART_TABLE_NAME + " (" +
            COL_SHOPPING_CART_ID + " INTEGER PRIMARY KEY," +
            COL_SHOPPING_CART_USER + " INTEGER," +
            "FOREIGN KEY("+ COL_SHOPPING_CART_USER +") REFERENCES "+ USER_TABLE_NAME+"("+ COL_USER_ID +") )";

    private static final String SQL_CREATE_ENTRIES_SHOPPING_CART_INVENTORY = "CREATE TABLE " +
            SHOPPING_CART_INVENTORY_TABLE_NAME + " (" +
            COL_SHOPPING_CART_ITEM_ID + " TEXT," +
            COL_FOREIGN_SHOPPING_CART_ID + " INTEGER," +
            COL_ITEM_AMOUNT + " INTEGER," +
            "PRIMARY KEY (" + COL_SHOPPING_CART_ITEM_ID + "," + COL_FOREIGN_SHOPPING_CART_ID + "), " +
            "FOREIGN KEY("+ COL_SHOPPING_CART_ITEM_ID +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +")," +
            "FOREIGN KEY("+ COL_FOREIGN_SHOPPING_CART_ID +") REFERENCES "+ SHOPPING_CART_TABLE_NAME+"("+ COL_ITEM_NAME +") )";

    private static final String SQL_CREATE_ENTRIES_USER_INVENTORY = "CREATE TABLE " +
            USER_INVENTORY_TABLE_NAME + " (" +
            COL_INVENTORY_ITEM_ID + " TEXT," +
            COL_FOREIGN_USER_ID + " INTEGER," +
            "PRIMARY KEY (" + COL_INVENTORY_ITEM_ID + "," + COL_FOREIGN_USER_ID + "), " +
            "FOREIGN KEY("+ COL_INVENTORY_ITEM_ID +") REFERENCES "+ ITEMS_TABLE_NAME+"("+ COL_USER_ID +")," +
            "FOREIGN KEY("+ COL_FOREIGN_USER_ID +") REFERENCES "+ SHOPPING_CART_TABLE_NAME+"("+ COL_ITEM_NAME +") )";

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
        values.put(COL_ITEM_NAME, item.getName());
        values.put(COL_DESCRIPTION, item.getDescription());
        values.put(COL_ITEM_PRICE, item.getPrice());
        values.put(COL_ITEM_QUALITY, item.getItemQuality().getValue());
        values.put(COL_ITEM_SLOT, item.getSlot());
        values.put(COL_ITEM_TYPE, item.getType());
        values.put(COL_PHYSICAL_ATTACK, item.getPhysicalAttack());
        values.put(COL_MAGIC_ATTACK, item.getMagicalAttack());
        values.put(COL_MAGIC_DEFENSE, item.getMagicalDefense());
        values.put(COL_PHYSICAL_DEFENSE, item.getPhysicalDefense());
        values.put(COL_SPECIAL_ABILITY, item.getSpecialAbility());
        values.put(COL_IMAGE_NAME, item.getImageId());
        if(item instanceof Weapon){
            values.put(COL_RANGE, ((Weapon) item).getRange());
        }
        try{db.insertOrThrow(ITEMS_TABLE_NAME, null, values);}
        catch(Exception e){
            Log.e("Insert", "insertRow: unable to insert because of unique issue", e);
        }
        if(needsClose){
            db.close();
        }

    }

    public void insertItems(ArrayList<Item> items, SQLiteDatabase db){
        boolean needsClose = false;
        if(db == null){
            db = getWritableDatabase();
            needsClose = true;
        }
        for (int i = 0; i < items.size(); i++) {
            insertItem(items.get(i), db);
        }
        if(needsClose){
            db.close();
        }
    }

    //methods for inserting shopping cart items to the shopping cart inventory table
    //will automatically update if the item already exists in the table.
    //Not sure if this isn't a good implementation
    public void insertOrUpdateShoppingCartItem(ShoppingCartItem item, SQLiteDatabase db){
        boolean needsClose = false;
        if(db == null){
            db = getWritableDatabase();
            needsClose = true;
        }
        ContentValues values = new ContentValues();
        values.put(COL_SHOPPING_CART_ITEM_ID, item.getItem().getName());
        values.put(COL_FOREIGN_SHOPPING_CART_ID, 1);
        values.put(COL_ITEM_AMOUNT, item.getNumItems());
        try{db.insertWithOnConflict(SHOPPING_CART_INVENTORY_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);}
        catch(Exception e){
            Log.e("Insert", "insertRow: unable to insert because of unique issue", e);
        }
        if(needsClose){
            db.close();
        }
    }

    public void insertShoppingCartItems(ArrayList<ShoppingCartItem> items){
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < items.size(); i++) {
            insertOrUpdateShoppingCartItem(items.get(i), db);
        }
        db.close();
    }

    public void insertInventoryItem(Item item, SQLiteDatabase db){
        boolean needsClose = false;
        if(db == null){
            db = getWritableDatabase();
            needsClose = true;
        }
        ContentValues values = new ContentValues();
        values.put(COL_INVENTORY_ITEM_ID, item.getName());
        values.put(COL_FOREIGN_USER_ID, 1);
        try{db.insertOrThrow(USER_INVENTORY_TABLE_NAME, null, values);}
        catch(Exception e){
            Log.e("Insert", "insertRow: unable to insert because of unique issue", e);
        }
        if(needsClose){
            db.close();
        }
    }

    public void insertInventoryItems(ArrayList<Item> items){
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < items.size(); i++) {
            insertInventoryItem(items.get(i), db);
        }
        db.close();
    }

    //insert or update user data, any item slot will insert null if not present
    public void insertOrUpdateUser(User user, SQLiteDatabase db){
        boolean needsClose = false;
        if(db == null){
            db = getWritableDatabase();
            needsClose = true;
        }
        ContentValues values = new ContentValues();
        values.put(COL_USER_ID, user.getUserId());
        values.put(COL_USER_NAME, user.getUserName());
        values.put(COL_CURRENT_GOLD, user.getGoldAmt());
        if(user.getHeadItem() != null) {values.put(COL_HEAD, (user.getHeadItem().getName()));}
        if(user.getChestItem() != null){values.put(COL_CHEST, user.getChestItem().getName());}
        if(user.getRightItem() != null){values.put(COL_RIGHT_HAND, user.getRightItem().getName());}
        if(user.getLeftItem() != null){values.put(COL_LEFT_HAND, user.getLeftItem().getName());}
        if(user.getAccessory1Item() != null){values.put(COL_ACCESSORY1, user.getAccessory1Item().getName());}
        if(user.getAccessory2Item() != null){values.put(COL_ACCESSORY2, user.getAccessory2Item().getName());}
        try{db.insertWithOnConflict(USER_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);}
        catch(Exception e){
            Log.e("Insert", "insertRow: unable to insert because of unique issue", e);
        }
        if(needsClose){
            db.close();
        }
    }

    public void insertShoppingCart(ShoppingCart shoppingCart, SQLiteDatabase db){
        boolean needsClose = false;
        if(db == null){
            db = getWritableDatabase();
            needsClose = true;
        }
        ContentValues values = new ContentValues();
        values.put(COL_SHOPPING_CART_ID, shoppingCart.getShoppingCartId());
        //set to 1 because only 1 user and cart at the moment
        values.put(COL_SHOPPING_CART_USER, 1);
        try{db.insertOrThrow(SHOPPING_CART_TABLE_NAME, null, values);}
        catch(Exception e){
            Log.e("Insert", "insertRow: unable to insert because of unique issue", e);
        }
        if(needsClose){
            db.close();
        }
    }

    //methods for removing items from the cart
    public void removeShoppingCartItem(ShoppingCartItem item){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM + " + SHOPPING_CART_INVENTORY_TABLE_NAME +
                " WHERE " + COL_SHOPPING_CART_ITEM_ID + " = " + item.getItem().getName());
        db.close();
    }

    public void removeAllShoppingCartItems(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("TRUNCATE TABLE " + SHOPPING_CART_INVENTORY_TABLE_NAME);
        db.close();
    }


    //gets all items in the Items table and processes them into the correct items
    public ArrayList<Item> getShopItems(String extraSelection, String[] newArg){
        ArrayList<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.query(ITEMS_TABLE_NAME, null, extraSelection, newArg, null, null, null);

        if(cursor.moveToFirst()){
            //should probably do this with setters...
            while(!cursor.isAfterLast()){
                try {
                    items.add(processItem(cursor));
                }
                catch (Exception e){
                    Log.e("DBHELPER", "getShopItems: ", e);
                }

                cursor.moveToNext();
            }
        }

        cursor.close();
        return items;

    }

    //same as above but joined to the user inventory
    public ArrayList<Item> getInventoryItems(User user, String extraSelection, String[] newArg){
        ArrayList<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();


        //String[] projection = {};
        SQLiteQueryBuilder qb2 = new SQLiteQueryBuilder();
        String selection = USER_INVENTORY_TABLE_NAME+"."+COL_FOREIGN_USER_ID+" = ?";
        if(extraSelection != null){
            selection += ", " + extraSelection;
        }
        String[] selectionArgs = {user.getUserId() + ""};
        if(newArg.length > 0){
            String[] result = Arrays.copyOf(selectionArgs, selectionArgs.length + newArg.length);
            System.arraycopy(newArg, 0, result, selectionArgs.length, newArg.length);
            selectionArgs = result;
        }
        qb2.setTables(ITEMS_TABLE_NAME + " INNER JOIN " + USER_INVENTORY_TABLE_NAME + " ON " +
                ITEMS_TABLE_NAME + "." + COL_ITEM_NAME + " = " + USER_INVENTORY_TABLE_NAME + "." +
                COL_INVENTORY_ITEM_ID);

        Cursor cursor = qb2.query(db, null, selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst()){
            //should probably do this with setters...
            while(!cursor.isAfterLast()){
                try {
                    items.add(processItem(cursor));
                }
                catch (Exception e){
                    Log.e("DBHELPER", "getShopItems: ", e);
                }

                cursor.moveToNext();
            }
        }

        cursor.close();
        return items;
    }

    public ArrayList<ShoppingCartItem> getShoppingCartItems(ShoppingCart shoppingCart){
        ArrayList<ShoppingCartItem> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();


        //String[] projection = {};
        SQLiteQueryBuilder qb2 = new SQLiteQueryBuilder();
        String selection = SHOPPING_CART_INVENTORY_TABLE_NAME+"."+COL_FOREIGN_SHOPPING_CART_ID+" = ?";
        String[] selectionArgs = {shoppingCart.getShoppingCartId() + ""};
        qb2.setTables(ITEMS_TABLE_NAME + " INNER JOIN " + SHOPPING_CART_INVENTORY_TABLE_NAME + " ON " +
                ITEMS_TABLE_NAME + "." + COL_ITEM_NAME + " = " + SHOPPING_CART_INVENTORY_TABLE_NAME + "." +
                COL_SHOPPING_CART_ITEM_ID);

        Cursor cursor = qb2.query(db, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            //should probably do this with setters...
            while(!cursor.isAfterLast()){
                try {
                    ShoppingCartItem item = new ShoppingCartItem(processItem(cursor),
                            cursor.getInt(cursor.getColumnIndex(COL_ITEM_AMOUNT)));
                    items.add(item);
                }
                catch (Exception e){
                    Log.e("DBHELPER", "getShopItems: ", e);
                }

                cursor.moveToNext();
            }
        }

        cursor.close();
        return items;
    }

    //a null user pulls from the shop, otherwise from inventory of that specific user
    public ArrayList<Item> getItemsByName(String name, User user){
        String selection = COL_ITEM_NAME+" = ?";
        String[] args = {name};
        if(user == null){
            return getShopItems(selection, args);
        }
        else{
            return getInventoryItems(user, selection, args);
        }
    }

    public ArrayList<Item> getItemsByType(String type, User user){
        String selection = COL_ITEM_TYPE+" = ?";
        String[] args = {type};
        if(user == null){
            return getShopItems(selection, args);
        }
        else{
            return getInventoryItems(user, selection, args);
        }
    }

    public ArrayList<Item> getItemsByQuality(int quality, User user){
        String selection = COL_ITEM_QUALITY+" = ?";
        String[] args = {quality + ""};
        if(user == null){
            return getShopItems(selection, args);
        }
        else{
            return getInventoryItems(user, selection, args);
        }
    }

    //searches for items with a price within 10 gold of the entered number
    public ArrayList<Item> getItemsByPrice(int price, User user){
        String selection = COL_ITEM_PRICE+" = ?";
        String[] args = {price + ""};
        if(user == null){
            return getShopItems(selection, args);
        }
        else{
            return getInventoryItems(user, selection, args);
        }
    }

    public ArrayList<Item> getItemsByDescription(String description, User user){
        String selection = COL_DESCRIPTION+" = ?";
        String[] args = {description};
        if(user == null){
            return getShopItems(selection, args);
        }
        else{
            return getInventoryItems(user, selection, args);
        }
    }


    //method to take the cursor and return an Item object of the correct subclass
    private Item processItem(Cursor cursor) throws Exception{
        Item item;
        String imageId = cursor.getString(cursor.getColumnIndex(COL_IMAGE_NAME));
        String name = cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME));
        String descrtiption = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
        String type = cursor.getString(cursor.getColumnIndex(COL_ITEM_TYPE));
        String special = cursor.getString(cursor.getColumnIndex(COL_SPECIAL_ABILITY));
        int price = cursor.getInt(cursor.getColumnIndex(COL_ITEM_PRICE));
        int itemQuality = cursor.getInt(cursor.getColumnIndex(COL_ITEM_QUALITY));
        Item.ItemQuality quality;
        switch(itemQuality){
            case 2:
                quality = Item.ItemQuality.MAGICAL;
                break;
            case 1:
                quality = Item.ItemQuality.STEEL;
                break;
            default:
                quality = Item.ItemQuality.BRONZE;
        }
        int physicalAttack = cursor.getInt(cursor.getColumnIndex(COL_PHYSICAL_ATTACK));
        int magicalAttack = cursor.getInt(cursor.getColumnIndex(COL_MAGIC_ATTACK));
        int physicalDefense = cursor.getInt(cursor.getColumnIndex(COL_PHYSICAL_DEFENSE));
        int magicalDefense = cursor.getInt(cursor.getColumnIndex(COL_MAGIC_DEFENSE));
        int range = 0;
        if(type.equals("Bow")){range = cursor.getInt(cursor.getColumnIndex(COL_RANGE));}

        switch(type){
            case "Accessory":
                item = new Accessory(descrtiption, name, price, quality, magicalAttack,
                        physicalAttack, physicalDefense, magicalDefense, special, imageId);
                break;
            case "Bow":
                item = new Bow(descrtiption, name, price, quality, physicalAttack, magicalAttack, range, imageId);
                break;
            case "Breastplate":
                item = new Breastplate(descrtiption, name, price, quality, physicalDefense, magicalDefense, imageId);
                break;
            case "Hat":
                item = new Hat(descrtiption, name, price, quality, physicalDefense, magicalDefense, magicalAttack, special, imageId);
                break;
            case "Helm":
                item = new Helm(descrtiption, name, price, quality, physicalDefense, magicalDefense, imageId);
                break;
            case "Robe":
                item = new Robe(descrtiption, name, price, quality, physicalDefense, magicalDefense, magicalAttack, imageId);
                break;
            case "Shield":
                item = new Shield(descrtiption, name, price, quality, physicalAttack, magicalAttack, range, physicalDefense, magicalDefense, imageId);
                break;
            case "Staff":
                item = new Staff(descrtiption, name, price, quality, physicalAttack, magicalAttack, range, special, imageId);
                break;
            case "Sword":
                item = new Sword(descrtiption, name, price, quality, physicalAttack, magicalAttack, range, imageId);
                break;
            default:
                throw new Exception("Item has incorrect type of " + type);
        }
        return item;
    }
}
