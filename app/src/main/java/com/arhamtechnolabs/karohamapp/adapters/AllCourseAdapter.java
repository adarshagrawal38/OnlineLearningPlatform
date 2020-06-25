package com.arhamtechnolabs.karohamapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.SingleCoursePage;
import com.arhamtechnolabs.karohamapp.models.AllCourseModel;
import com.arhamtechnolabs.karohamapp.models.CategoryModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.ViewHolder> {

    private Context context;
    private List<AllCourseModel> listItems;

    public AllCourseAdapter(Context context, List<AllCourseModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }


    @NonNull
    @Override
    public AllCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View mView = LayoutInflater.from(context).inflate(R.layout.row_all_course_list_file, parent, false);
        return new AllCourseAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseAdapter.ViewHolder holder, int position) {
        final AllCourseModel listItem = listItems.get(position);


        holder.courseName.setText(listItem.getTitle());
        holder.coursePrice.setText("₹ " + listItem.getDiscounted_price());
        holder.courseRate.setRating(Float.parseFloat(listItem.getRating()));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(context, SingleCoursePage.class);
                intent.putExtra("key_id", listItem.getId());
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
        RatingBar courseRate;
        CardView card;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            courseName = itemView.findViewById(R.id.courseName);
            coursePrice = itemView.findViewById(R.id.coursePrice);
            courseRate = itemView.findViewById(R.id.courseRate);
            card = itemView.findViewById(R.id.card);


        }
    }
}
