package com.arhamtechnolabs.karohamapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arhamtechnolabs.karohamapp.Hepers.LocalWishListHelper;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.adapters.WishListAdapter;
import com.arhamtechnolabs.karohamapp.models.CartModel;

import java.util.List;

public class NotificationsFragment extends Fragment {

    //Wishlist Page

    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = root.findViewById(R.id.wishListRecyler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        LocalWishListHelper localWishListHelper = new LocalWishListHelper(getContext());
        List<CartModel> wishList = localWishListHelper.fetchWishListData();

        WishListAdapter wishListAdapter = new WishListAdapter(getContext(), wishList);
        recyclerView.setAdapter(wishListAdapter);

        return root;
    }
}
