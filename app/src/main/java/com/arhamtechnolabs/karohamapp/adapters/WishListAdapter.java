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
import com.arhamtechnolabs.karohamapp.Hepers.LocalWishListHelper;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.SingleCoursePage;
import com.arhamtechnolabs.karohamapp.models.CartModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private Context context;
    private List<CartModel> listItems;

    public WishListAdapter(Context context, List<CartModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }


    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View mView = LayoutInflater.from(context).inflate(R.layout.row_wish_list, parent, false);
        return new WishListAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CartModel listItem = listItems.get(position);


        holder.courseName.setText(listItem.getCourseTitle());

        if (!listItem.getThumbnail().equals("null")) {
            Glide.with(context).load(listItem.getThumbnail()).into(holder.thumbnail);
        }
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalWishListHelper wishListHelper = new LocalWishListHelper(context);
                wishListHelper.deleteItem(listItem);
                listItems.remove(listItem);
                notifyDataSetChanged();
            }
        });
        holder.ratingBar.setRating(Float.parseFloat(listItem.getRatings()));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleCoursePage.class);
                intent.putExtra("key_id", listItem.getCourseId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
        RatingBar ratingBar;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.trendingCourseBanner);
            courseName = itemView.findViewById(R.id.trendingCourseTitle);
            deleteImageView = itemView.findViewById(R.id.removeWishIv);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            card = itemView.findViewById(R.id.card);
        }
    }
}
