package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ShopFragment.OnAddToCartListener,
        ShopFragment.OnDetailRequestedListener {

    private static final String ITEM_TO_ADD = "Item_to_Add";
    private FantasyShopDatabaseHelper mHelper;

    private RelativeLayout mFragmentContainer;
    private ArrayList<Item> mItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //grab all the shop items for display
        mHelper = FantasyShopDatabaseHelper.getInstance(this);
        mItems = mHelper.getShopItems(null, null);

        mFragmentContainer = (RelativeLayout) findViewById(R.id.shop_fragment_container);

        //create listener for the gotocart button
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, ShoppingCartActivity.class));
            }
        };
        //set up the inner fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.shop_fragment_container,
                        ShopFragment.getInstance(listener, this, this, this, mItems))
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
    public void onAddToCart(int position) {
        Intent intent = new Intent(ShopActivity.this, ShoppingCart.class);
        intent.putExtra(ITEM_TO_ADD, position);
        startActivity(intent);
    }

    //create and show detail fragment if user taps to request it
    @Override
    public void detailRequested(final int position) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("ShopFragment")
                .add(R.id.shop_fragment_container,
                        ItemDetailFragment.getInstance(mItems.get(position), true, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ShopActivity.this, ShoppingCart.class);
                                intent.putExtra(ITEM_TO_ADD, position);
                                startActivity(intent);
                            }
                        }))
                .commit();
    }
}
