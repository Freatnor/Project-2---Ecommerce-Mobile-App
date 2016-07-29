package com.example.freatnor.project_2___ecommerce_mobile_app.recyclerviewclasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freatnor.project_2___ecommerce_mobile_app.R;
import com.example.freatnor.project_2___ecommerce_mobile_app.ShopFragment;
import com.example.freatnor.project_2___ecommerce_mobile_app.ShoppingCart;
import com.example.freatnor.project_2___ecommerce_mobile_app.ShoppingCartItem;
import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 7/27/16.
 */
public class ShoppingCartItemRecyclerViewAdapter extends Adapter<ShoppingCartItemRecyclerViewAdapter.CartItemViewHolder>
implements ShoppingCart.ShoppingCartChangeListener {

    private ShoppingCart mCart;

    public interface ShoppingCartTotalChangeListener{
        void onTotalChange();
    }

    private ShoppingCartTotalChangeListener mListener;

    public ShoppingCartItemRecyclerViewAdapter(Context context, ShoppingCartTotalChangeListener listener){
        mCart = ShoppingCart.getInstance(context);
        mCart.setListener(this);
        mListener = listener;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_recycle_inner_content,
                parent, false);
        return new CartItemViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, final int position) {
        holder.setDecrementOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCart.decrementItem(position);
                mListener.onTotalChange();
            }
        });
        holder.setIncrementOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCart.incrementItem(position);
                mListener.onTotalChange();
            }
        });
        holder.setButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCart.removeItem(position);
                mListener.onTotalChange();
            }
        });

        holder.setItemName(mCart.getItem(position).getItem().getName());
        holder.setItemIcon(holder.getContext().getResources().getIdentifier(mCart.getItem(position).
                getItem().getImageId(), "drawable", holder.getContext().getPackageName()));
        holder.setItemTotalPrice(mCart.getItem(position).getTotalPrice());
        holder.setItemNumber(mCart.getItem(position).getNumItems());
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }


    //interface methods, possibly need to update the totalPrice at bottom separately
    @Override
    public void notifyCartItemDeleted(int position) {
        notifyItemRemoved(position);
    }

    @Override
    public void notifyCartItemAdded(int position) {
        notifyItemInserted(position);
    }

    @Override
    public void notifyCartCleared() {
        notifyDataSetChanged();
    }

    @Override
    public void notifyCartItemChanged(int position){
        notifyItemChanged(position);
    }



    public class CartItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView mItemIcon;
        private TextView mItemName;

        private ImageButton mDecrement;
        private ImageButton mIncrement;
        private TextView mItemNumber;

        private TextView mItemTotalPrice;
        private Button mButton;


        public CartItemViewHolder(View itemView) {
            super(itemView);
            mItemIcon = (ImageView) itemView.findViewById(R.id.item_image_container);
            mItemName = (TextView) itemView.findViewById(R.id.cart_item_recycler_item_name);

            mDecrement = (ImageButton) itemView.findViewById((R.id.cart_decrement));
            mIncrement = (ImageButton) itemView.findViewById((R.id.cart_increment));
            mItemNumber = (TextView) itemView.findViewById(R.id.cart_item_amount);

            mItemTotalPrice = (TextView) itemView.findViewById(R.id.cart_item_recycler_price_text_view);

            mButton = (Button) itemView.findViewById(R.id.remove_from_cart_button);
        }

        public void setItemIcon(int itemIcon) {
            mItemIcon.setImageResource(itemIcon);
        }

        public void setItemName(String itemName) {
            mItemName.setText(itemName);
        }

        public void setItemTotalPrice(int itemPrice) {
            mItemTotalPrice.setText(itemPrice + "");
        }

        public void setItemNumber(int itemNumber){
            mItemNumber.setText(itemNumber + "");
        }

        //for setting the increment/decrement view on click listeners
        public void setDecrementOnClickListener(View.OnClickListener listener){
            mDecrement.setOnClickListener(listener);
        }

        public void setIncrementOnClickListener(View.OnClickListener listener){
            mIncrement.setOnClickListener(listener);
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
