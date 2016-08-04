package com.example.freatnor.project_2___ecommerce_mobile_app.database;

import android.os.AsyncTask;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/2/16.
 */
public class InsertItemsAsyncTask extends AsyncTask<ArrayList<Item>, Void, Void>{
    @Override
    protected Void doInBackground(ArrayList<Item>... arrayLists) {
        //db helper with null context sinice thread and also needs to have been initialized
        FantasyShopDatabaseHelper helper = FantasyShopDatabaseHelper.getInstance(null);
        helper.insertInventoryItems(arrayLists[0]);
        return null;
    }
}
