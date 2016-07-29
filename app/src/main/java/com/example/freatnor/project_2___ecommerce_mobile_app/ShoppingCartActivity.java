package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freatnor.project_2___ecommerce_mobile_app.database.FantasyShopDatabaseHelper;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;
import com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses.ItemRecyclerViewAdapter;
import com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses.ShoppingCartItemRecyclerViewAdapter;

public class ShoppingCartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ShoppingCartItemRecyclerViewAdapter.ShoppingCartTotalChangeListener {

    private ShoppingCart mCart;

    private RecyclerView mRecyclerView;
    private TextView mTotalPrice;
    private Button mPurchaseButton;

    private ShoppingCartItemRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCart = ShoppingCart.getInstance(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.shopping_cart_recycler_view);
        mTotalPrice = (TextView) findViewById(R.id.shopping_cart_total_price_text_view);
        mPurchaseButton = (Button) findViewById(R.id.purchase_button);

        mAdapter = new ShoppingCartItemRecyclerViewAdapter(this, this);
        mManager = new LinearLayoutManager(ShoppingCartActivity.this, LinearLayoutManager.VERTICAL, false);

        //if there's any intent data passed then add that to the cart
        Intent intent = getIntent();
        if(intent.hasExtra(ShopActivity.ITEM_TO_ADD)){
            Item item = FantasyShopDatabaseHelper.getInstance(this).getItemsByName(
                    intent.getStringExtra(ShopActivity.ITEM_TO_ADD), null).get(0);
            //check that the item doesn't already exist in the cart and if it does just increment it
            int position = mCart.getIndex(item);
            if(position >= 0){
                mCart.incrementItem(position);
            }
            else {
                mCart.addItem(item, 1);
            }
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);

        mTotalPrice.setText(mCart.getTotalPrice() + "");

        //create on click for dialog to prompt user about buying the cart
        mPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                String message = "Are you sure you wish to purchase these items?";
                String title = "Purchase Items";
                builder.setMessage(message)
                        .setTitle(title)
                        .setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(User.getUser(FantasyShopDatabaseHelper.getInstance(ShoppingCartActivity.this)).getGoldAmt() < mCart.getTotalPrice()){
                                    Toast.makeText(ShoppingCartActivity.this, "Insufficient Funds! You only have " + User.getUser(FantasyShopDatabaseHelper.getInstance(ShoppingCartActivity.this)).getGoldAmt() + " Gold.", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    User.getUser(FantasyShopDatabaseHelper.getInstance(ShoppingCartActivity.this)).spendGold(mCart.getTotalPrice());
                                    mCart.purchaseItems();
                                    startActivity(new Intent(ShoppingCartActivity.this, UserLoadoutActivity.class));
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null);
                builder.create();
                builder.show();
            }
        });

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
        getMenuInflater().inflate(R.menu.shopping_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_all) {
            mCart.clearCart();

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
            startActivity(new Intent(ShoppingCartActivity.this, UserLoadoutActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_shop) {
            startActivity(new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class));
        } else if (id == R.id.nav_shopping_cart) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTotalChange() {
        mTotalPrice.setText(mCart.getTotalPrice() + "");
    }
}
