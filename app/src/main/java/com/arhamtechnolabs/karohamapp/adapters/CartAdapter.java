package com.arhamtechnolabs.karohamapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arhamtechnolabs.karohamapp.CartActivity;
import com.arhamtechnolabs.karohamapp.Hepers.LocalDataBaseHelper;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.SingleCoursePage;
import com.arhamtechnolabs.karohamapp.models.AllCourseModel;
import com.arhamtechnolabs.karohamapp.models.CartModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<CartModel> listItems;

    public CartAdapter(Context context, List<CartModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View mView = LayoutInflater.from(context).inflate(R.layout.row_cart, parent, false);
        return new CartAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CartModel listItem = listItems.get(position);


        holder.courseName.setText(listItem.getCourseTitle());
        String price = listItem.getCoursePrice();
        if (listItem.getCourseDiscountFlag().equals("1")) {
            price = listItem.getCourseDiscountPrice();
        }
        holder.coursePrice.setText("₹ " + price);
        if (!listItem.getThumbnail().equals("null")) {
            Glide.with(context).load(listItem.getThumbnail()).into(holder.thumbnail);
        }
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDataBaseHelper localDataBaseHelper = new LocalDataBaseHelper(context);
                localDataBaseHelper.deleteItem(listItem);
                listItems.remove(listItem);
                notifyDataSetChanged();
                CartActivity cartActivity = (CartActivity)context;
                cartActivity.updateView();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView courseName, coursePrice;
        CardView card;
        ImageView deleteImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            courseName = itemView.findViewById(R.id.courseName);
            coursePrice = itemView.findViewById(R.id.coursePrice);
            deleteImageView = itemView.findViewById(R.id.cartRemoveItem);
            card = itemView.findViewById(R.id.card);


        }
    }
}
