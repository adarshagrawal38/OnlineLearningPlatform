package com.arhamtechnolabs.karohamapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.SingleCoursePage;
import com.arhamtechnolabs.karohamapp.models.TrendingCourseModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingCourseAdapter extends RecyclerView.Adapter<TrendingCourseAdapter.ViewHolder> {

    private Context context;
    private List<TrendingCourseModel> listItems;

    public TrendingCourseAdapter(Context context, List<TrendingCourseModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public TrendingCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View mView = LayoutInflater.from(context).inflate(R.layout.row_course_file, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingCourseAdapter.ViewHolder holder, int position) {
        final TrendingCourseModel listItem = listItems.get(position);

        holder.trendingCourseTitle.setText(listItem.getTitle());
        holder.trendingCourseAmount.setText(Html.fromHtml("&#8377;") + listItem.getDiscounted_price());
        holder.ratingBar.setRating(Float.parseFloat("4.7"));
        holder.courseCard.setOnClickListener(new View.OnClickListener() {
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

        ImageView trendingCourseBanner;
        TextView trendingCourseTitle, trendingCourseAmount;
        RatingBar ratingBar;
        CardView courseCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trendingCourseBanner = itemView.findViewById(R.id.trendingCourseBanner);
            trendingCourseTitle = itemView.findViewById(R.id.trendingCourseTitle);
            trendingCourseAmount = itemView.findViewById(R.id.trendingCourseAmount);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            courseCard = itemView.findViewById(R.id.courseCard);

        }
    }
}
