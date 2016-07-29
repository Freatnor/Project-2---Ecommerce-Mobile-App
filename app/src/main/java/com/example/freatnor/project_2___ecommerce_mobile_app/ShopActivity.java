package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.freatnor.project_2___ecommerce_mobile_app.database.FantasyShopDatabaseHelper;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Breastplate;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Robe;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Shield;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Sword;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ShopFragment.OnAddToCartListener,
        ShopFragment.OnDetailRequestedListener {

    public static final String ITEM_TO_ADD = "Item_to_Add";
    private FantasyShopDatabaseHelper mHelper;

    private RelativeLayout mFragmentContainer;
    private ArrayList<Item> mItems;

    private ShopFragment mFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //grab all the shop items for display
        mHelper = FantasyShopDatabaseHelper.getInstance(this);
        mItems = mHelper.getShopItems(null, null);

        //Initialize the db
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Sword("A sword", "Bronze Sword", 12, Item.ItemQuality.BRONZE, 10, 0, 0, "icon_long_sword"));
        items.add(new Sword("A magical sword imbued with ice", "IceBrand", 400, Item.ItemQuality.MAGICAL, 30, 10, 0, "icon_magic_sword"));
        items.add(new Breastplate("A breastplate fashioned from bronze.", "Bronze Armor", 30, Item.ItemQuality.BRONZE, 10, 5, "icon_bronze_armor"));
        items.add(new Robe("The robe of a renowned cleric, imbued with magical power.", "Cleric's Robes", 200, Item.ItemQuality.STEEL, 8, 25, 15, "icon_cleric_robes"));
        items.add(new Shield("A tall steel shield, too heavy for most to carry.", "Steel Shield", 45, Item.ItemQuality.STEEL, 0, 0, 0, 20, 10, "icon_steel_shield"));
        mHelper.insertItems(items, null);

        mHelper.insertOrUpdateUser(User.getUser(), null);
        mHelper.insertShoppingCart(ShoppingCart.getInstance(this), null);

        mFragmentContainer = (RelativeLayout) findViewById(R.id.shop_fragment_container);

        //create listener for the gotocart button
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, ShoppingCartActivity.class));
            }
        };
        //set up the inner fragment
        mFragment = ShopFragment.getInstance(listener, this, this, this, mItems);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.shop_fragment_container, mFragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        processSearchIntent(getIntent());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shop, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, this.getClass());
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        searchView.setQueryRefinementEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

        processSearchIntent(intent);
    }

    private void processSearchIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ArrayList<Item> newItems = mHelper.getItemsByName(query, null);
            mFragment.refreshItemList(newItems);
        }
    }

    @Override
    public void onAddToCart(int position) {
        Intent intent = new Intent(ShopActivity.this, ShoppingCartActivity.class);
        intent.putExtra(ITEM_TO_ADD, mItems.get(position).getName());
        startActivity(intent);
    }

    //create and show detail fragment if user taps to request it
    @Override
    public void detailRequested(final int position) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("ShopFragment")
                .replace(R.id.shop_fragment_container,
                        ItemDetailFragment.getInstance(mItems.get(position), true, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ShopActivity.this, ShoppingCartActivity.class);
                                intent.putExtra(ITEM_TO_ADD, mItems.get(position).getName());
                                startActivity(intent);
                            }
                        }))
                .commit();
    }
}
