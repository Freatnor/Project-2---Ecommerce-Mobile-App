package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.freatnor.project_2___ecommerce_mobile_app.database.FantasyShopDatabaseHelper;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

public class UserLoadoutActivity extends AppCompatActivity {

    private User mUser;

    private UserLoadoutFragment mFragment;
    private RelativeLayout mFragmentContainer;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_user_loadout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUser = FantasyShopDatabaseHelper.getInstance(this).getUser();



        mFragmentContainer = (RelativeLayout) findViewById(R.id.loadout_activity_fragment_container);

        //Space for setting up the slot buttons






        //set up the inner fragment
        mFragment = UserLoadoutFragment.getInstance(listener, this, this, this, mItems);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.loadout_activity_fragment_container, mFragment)
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shop, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();

        ComponentName componentName = new ComponentName(this, this.getClass());
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        mSearchView.setQueryRefinementEnabled(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s != null) {
                    if (s.isEmpty()) {
                        ArrayList<Item> newItems = mHelper.getShopItems(null, null);
                        mFragment.refreshItemList(newItems);
                    } else {
                        ArrayList<Item> newItems = mHelper.getItemsByName(s, null);
                        mFragment.refreshItemList(newItems);
                    }
                }
                return true;
            }
        });
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

        if (id == R.id.nav_loadout) {

        } else if (id == R.id.nav_shop) {
            startActivity(new Intent(UserLoadoutActivity.this, ShopActivity.class));
        } else if (id == R.id.nav_shopping_cart) {
            startActivity(new Intent(UserLoadoutActivity.this, ShoppingCartActivity.class));
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
