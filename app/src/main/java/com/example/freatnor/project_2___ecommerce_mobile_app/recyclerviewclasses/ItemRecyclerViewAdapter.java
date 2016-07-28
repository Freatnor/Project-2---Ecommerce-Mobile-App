package com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses;

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
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/27/16.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private ArrayList<Item> items;

    private View.OnClickListener mAddToCartListener;
    private View.OnClickListener mDetailListener;

    public ItemRecyclerViewAdapter(View.OnClickListener addToCartListener, View.OnClickListener detailListener){
        mAddToCartListener = addToCartListener;
        mDetailListener = detailListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recycle_inner_content,
                parent, false);
        return new ItemViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setViewOnClickListener(mDetailListener);
        holder
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder{

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
            mItemName = (TextView) itemView.findViewById(R.id.item_detail_name);

            mItemPAtkValue = (TextView) itemView.findViewById(R.id.physical_attack_value);
            mItemMAtkValue = (TextView) itemView.findViewById(R.id.magical_attack_value);
            mItemPDefValue = (TextView) itemView.findViewById(R.id.physical_defense_value);
            mItemMDefValue = (TextView) itemView.findViewById(R.id.magical_defense_value);

            mItemPrice = (TextView) itemView.findViewById(R.id.price_text_view);

            mButton = (Button) itemView.findViewById(R.id.add_to_cart_or_equip_button);
        }

        public String getItemPAtkValue() {
            return mItemPAtkValue.getText().toString();
        }

        public void setItemPAtkValue(int itemPAtkValue) {
            mItemPAtkValue.setText(itemPAtkValue);
        }

        public String getItemMAtkValue() {
            return mItemMAtkValue.getText().toString();
        }

        public void setItemMAtkValue(int itemMAtkValue) {
            mItemMAtkValue.setText(itemMAtkValue);
        }

        public String getItemPDefValue() {
            return mItemPDefValue.getText().toString();
        }

        public void setItemPDefValue(int itemPDefValue) {
            mItemPDefValue.setText(itemPDefValue);
        }

        public String getItemMDefValue() {
            return mItemMDefValue.getText().toString();
        }

        public void setItemMDefValue(int itemMDefValue) {
            mItemMDefValue.setText(itemMDefValue);
        }

        public void setItemIcon(int itemIcon) {
            mItemIcon.setImageResource(itemIcon);
        }

        public void setItemName(String itemName) {
            mItemName.setText(itemName);
        }

        public void setItemPrice(int itemPrice) {
            mItemPrice.setText(itemPrice);
        }

        //for setting the detail view on click listener
        public void setViewOnClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }

        //for setting the add to cart listener
        public void setButtonOnClickListener(View.OnClickListener listener){
            mButton.setOnClickListener(listener);
        }
    }
}
