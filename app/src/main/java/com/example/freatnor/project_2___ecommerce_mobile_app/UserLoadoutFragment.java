package com.example.freatnor.project_2___ecommerce_mobile_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.freatnor.project_2___ecommerce_mobile_app.items.Item;

/**
 * Created by Jonathan Taylor on 7/29/16.
 */
public class UserLoadoutFragment extends Fragment {

    private User mUser;




    private ImageView mHead;
    private ImageView mHeadButton;
    private ImageView mChest;
    private ImageView mChestButton;
    private ImageView mRight;
    private ImageView mRightButton;
    private ImageView mLeft;
    private ImageView mLeftButton;
    private ImageView mAcc1;
    private ImageView mAcc1Button;
    private ImageView mAcc2;
    private ImageView mAcc2Button;


    public interface OnSlotItemClickedListener {
        void onSlotItemClicked(String slot, Item item);
    }
    private OnSlotItemClickedListener mOnSlotItemClickedListener;

    private Context mContext;



    public static UserLoadoutFragment getInstance(User user, OnSlotItemClickedListener onSlotItemClickedListener, Context context){
        UserLoadoutFragment fragment = new UserLoadoutFragment();
        fragment.mUser = user;
        fragment.mOnSlotItemClickedListener = onSlotItemClickedListener;
        fragment.mContext = context;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_loadout_equip_screen, container, false);

        mHead = (ImageView) parentView.findViewById(R.id.head_slot_image);
        mHeadButton = (ImageView) parentView.findViewById(R.id.head_slot_button);
        mChest = (ImageView) parentView.findViewById(R.id.chest_slot_image);
        mChestButton = (ImageView) parentView.findViewById(R.id.chest_slot_button);
        mRight = (ImageView) parentView.findViewById(R.id.right_hand_slot_image);
        mRightButton = (ImageView) parentView.findViewById(R.id.right_hand_slot_button);
        mLeft = (ImageView) parentView.findViewById(R.id.left_hand_slot_image);
        mLeftButton = (ImageView) parentView.findViewById(R.id.left_hand_slot_button);
        mAcc1 = (ImageView) parentView.findViewById(R.id.accessory_1_slot_image);
        mAcc1Button = (ImageView) parentView.findViewById(R.id.accessory_1_slot_button);
        mAcc2 = (ImageView) parentView.findViewById(R.id.accessory_2_slot_image);
        mAcc2Button = (ImageView) parentView.findViewById(R.id.accessory_2_slot_button);
        //space for creating and binding new lower fragment



        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //prime the images
        try {
            mHead.setImageResource(getResources().getIdentifier(mUser.getHeadItem().getImageId(),
                    "drawable", getContext().getPackageName()));
            mChest.setImageResource(getResources().getIdentifier(mUser.getChestItem().getImageId(),
                    "drawable", getContext().getPackageName()));
            mRight.setImageResource(getResources().getIdentifier(mUser.getRightItem().getImageId(),
                    "drawable", getContext().getPackageName()));
            mLeft.setImageResource(getResources().getIdentifier(mUser.getLeftItem().getImageId(),
                    "drawable", getContext().getPackageName()));
            mAcc1.setImageResource(getResources().getIdentifier(mUser.getAccessory1Item().getImageId(),
                    "drawable", getContext().getPackageName()));
            mAcc2.setImageResource(getResources().getIdentifier(mUser.getAccessory2Item().getImageId(),
                    "drawable", getContext().getPackageName()));
        }
        catch (Exception e){
            Log.e("Equipping Items", "onViewCreated: some null objects", e);
        }


    }


    //place the new image in the slot
    public void updateSlot(String slot) {
        switch(slot){
            case "head":
                mHead.setImageResource(getResources().getIdentifier(mUser.getHeadItem().getImageId(),
                        "drawable", getContext().getPackageName()));
                break;
            case "chest":
                mChest.setImageResource(getResources().getIdentifier(mUser.getChestItem().getImageId(),
                        "drawable", getContext().getPackageName()));
                break;
            case "right":
                mRight.setImageResource(getResources().getIdentifier(mUser.getRightItem().getImageId(),
                        "drawable", getContext().getPackageName()));
                break;
            case "left":
                mLeft.setImageResource(getResources().getIdentifier(mUser.getLeftItem().getImageId(),
                        "drawable", getContext().getPackageName()));
                break;
            case "accessory1":
                mAcc1.setImageResource(getResources().getIdentifier(mUser.getAccessory1Item().getImageId(),
                        "drawable", getContext().getPackageName()));
                break;
            case "accessory2":
                mAcc2.setImageResource(getResources().getIdentifier(mUser.getAccessory2Item().getImageId(),
                        "drawable", getContext().getPackageName()));
                break;
            default:
                break;
        }
    }

}
