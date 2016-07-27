package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

/**
 * Created by Jonathan Taylor on 7/26/16.
 */
public class ItemDetailFragment extends Fragment {

    private boolean fromShop;
    private Item mItem;

    private static final String SPECIAL_PREFIX = "Special: ";
    private static final String DESCRIPTION_PREFIX = "Description: ";

    private View.OnClickListener mListener;

    private ImageView mItemIcon;
    private TextView mItemName;
    private TextView mItemSlot;
    private TextView mItemQuality;

    private TextView mItemPAtkValue;
    private TextView mItemMAtkValue;
    private TextView mItemPDefValue;
    private TextView mItemMDefValue;

    private TextView mItemSpecial;
    private TextView mItemDescription;

    private TextView mItemPrice;

    private LinearLayout mPriceContainer;
    private Button mButton;

    //factory method for fragment, takes in the item to display and a boolean for where it came from
    //if true displays price data and button for adding to cart instead
    public ItemDetailFragment getInstance(Item item, boolean fromShop, View.OnClickListener listener){
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.mItem = item;
        fragment.fromShop = fromShop;
        fragment.mListener = listener;
        return fragment;
    }


    //Inflate the view and grab references to all the important items
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        mItemIcon = (ImageView) parentView.findViewById(R.id.item_image_container);
        mItemName = (TextView) parentView.findViewById(R.id.item_detail_name);
        mItemSlot = (TextView) parentView.findViewById(R.id.item_detail_slot);
        mItemQuality = (TextView) parentView.findViewById(R.id.item_detail_quality);

        mItemPAtkValue = (TextView) parentView.findViewById(R.id.physical_attack_value);
        mItemMAtkValue = (TextView) parentView.findViewById(R.id.magical_attack_value);
        mItemPDefValue = (TextView) parentView.findViewById(R.id.physical_defense_value);
        mItemMDefValue = (TextView) parentView.findViewById(R.id.magical_defense_value);

        mItemSpecial = (TextView) parentView.findViewById(R.id.item_detail_special);
        mItemDescription = (TextView) parentView.findViewById(R.id.item_detail_description);

        mItemPrice = (TextView) parentView.findViewById(R.id.price_text_view);

        mPriceContainer = (LinearLayout) parentView.findViewById(R.id.price_container);
        mButton = (Button) parentView.findViewById(R.id.add_to_cart_or_equip_button);

        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //sets the image to the resource id saved in the image object
        mItemIcon.setImageResource(mItem.getImageId());

        mItemName.setText(mItem.getName());
        mItemSlot.setText(mItem.getSlot());
        mItemQuality.setText(mItem.getItemQuality().toString());

        mItemPAtkValue.setText(mItem.getPhysicalAttack()+"");
        mItemMAtkValue.setText(mItem.getMagicalAttack());
        mItemPDefValue.setText(mItem.getPhysicalDefense());
        mItemMDefValue.setText(mItem.getMagicalDefense());

        mItemSpecial.setText(SPECIAL_PREFIX + mItem.getSpecialAbility());
        mItemDescription.setText(DESCRIPTION_PREFIX + mItem.getDescription());

        mItemPrice.setText(mItem.getPrice());

        if(!fromShop){
            mPriceContainer.setVisibility(View.INVISIBLE);
        }

        mButton.setOnClickListener(mListener);

    }
}
