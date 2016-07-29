package com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freatnor.project_2___ecommerce_mobile_app.R;
import com.example.freatnor.project_2___ecommerce_mobile_app.ShopFragment;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/27/16.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    private ArrayList<Item> items;

    private ShopFragment.OnAddToCartListener mAddToCartListener;
    private ShopFragment.OnDetailRequestedListener mDetailListener;
    private ShopFragment.OnSearchListener mSearchListener;

    public ItemRecyclerViewAdapter(ShopFragment.OnAddToCartListener addToCartListener, ShopFragment.OnDetailRequestedListener detailListener,
                                   ShopFragment.OnSearchListener searchListener, ArrayList<Item> items){
        mAddToCartListener = addToCartListener;
        mDetailListener = detailListener;
        mSearchListener = searchListener;
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recycle_inner_content,
                parent, false);
        return new ItemViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.setViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDetailListener.detailRequested(position);
            }
        });
        holder.setButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddToCartListener.onAddToCart(position);
            }
        });

        holder.setItemName(items.get(position).getName());
        holder.setItemIcon(holder.getContext().getResources().getIdentifier(items.get(position).getImageId(),
                "drawable", holder.getContext().getPackageName()));
        holder.setItemPrice(items.get(position).getPrice());
        holder.setItemPAtkValue(items.get(position).getPhysicalAttack());
        holder.setItemMAtkValue(items.get(position).getMagicalAttack());
        holder.setItemPDefValue(items.get(position).getPhysicalDefense());
        holder.setItemMDefValue(items.get(position).getMagicalDefense());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //method to reload the view
    public void refreshItemList(ArrayList<Item> newItems){
        items = newItems;
        notifyDataSetChanged();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView mItemIcon;
        private TextView mItemName;

        private TextView mItemPAtkValue;
        private TextView mItemMAtkValue;
        private TextView mItemPDefValue;
        private TextView mItemMDefValue;

        private TextView mItemPrice;
        private Button mButton;


        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemIcon = (ImageView) itemView.findViewById(R.id.item_image_container);
            mItemName = (TextView) itemView.findViewById(R.id.shop_recycler_item_name);

            mItemPAtkValue = (TextView) itemView.findViewById(R.id.physical_attack_value);
            mItemMAtkValue = (TextView) itemView.findViewById(R.id.magical_attack_value);
            mItemPDefValue = (TextView) itemView.findViewById(R.id.physical_defense_value);
            mItemMDefValue = (TextView) itemView.findViewById(R.id.magical_defense_value);

            mItemPrice = (TextView) itemView.findViewById(R.id.shop_recycler_price_text_view);

            mButton = (Button) itemView.findViewById(R.id.add_to_cart_button);
        }

        public String getItemPAtkValue() {
            return mItemPAtkValue.getText().toString();
        }

        public void setItemPAtkValue(int itemPAtkValue) {
            mItemPAtkValue.setText(itemPAtkValue + "");
        }

        public String getItemMAtkValue() {
            return mItemMAtkValue.getText().toString();
        }

        public void setItemMAtkValue(int itemMAtkValue) {
            mItemMAtkValue.setText(itemMAtkValue + "");
        }

        public String getItemPDefValue() {
            return mItemPDefValue.getText().toString();
        }

        public void setItemPDefValue(int itemPDefValue) {
            mItemPDefValue.setText(itemPDefValue + "");
        }

        public String getItemMDefValue() {
            return mItemMDefValue.getText().toString();
        }

        public void setItemMDefValue(int itemMDefValue) {
            mItemMDefValue.setText(itemMDefValue + "");
        }

        public void setItemIcon(int itemIcon) {
            mItemIcon.setImageResource(itemIcon);
        }

        public void setItemName(String itemName) {
            mItemName.setText(itemName);
        }

        public void setItemPrice(int itemPrice) {
            mItemPrice.setText(itemPrice + "");
        }

        //for setting the detail view on click listener
        public void setViewOnClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }

        //for setting the add to cart listener
        public void setButtonOnClickListener(View.OnClickListener listener){
            mButton.setOnClickListener(listener);
        }

        public Context getContext(){
            return mButton.getContext();
        }
    }
}
