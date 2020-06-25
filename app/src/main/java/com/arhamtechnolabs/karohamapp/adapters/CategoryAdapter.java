package com.arhamtechnolabs.karohamapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arhamtechnolabs.karohamapp.AllCourseActivity;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.SingleCoursePage;
import com.arhamtechnolabs.karohamapp.models.CategoryModel;
import com.arhamtechnolabs.karohamapp.models.TrendingCourseModel;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryModel> listItems;

    public CategoryAdapter(Context context, List<CategoryModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View mView = LayoutInflater.from(context).inflate(R.layout.row_category_file, parent, false);
        return new CategoryAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        final CategoryModel listItem = listItems.get(position);

        if (position % 2 == 0){
            holder.linearLayoutColor.setBackgroundColor(context.getResources().getColor(R.color.list1));
        }else{
            holder.linearLayoutColor.setBackgroundColor(context.getResources().getColor(R.color.list2));
        }

        holder.categoryName.setText(listItem.getName());
        holder.categoryCourseCount.setText("8 Courses");

        if (listItem.getThumbnail().equals("http://karoham.co.in/uploads/thumbnails/category_thumbnails/")){
            System.out.println("Image not found");
        }else{
            Glide.with(context).load(listItem.getThumbnail()).into(holder.categoryImage);
        }

        holder.linearLayoutColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(context, AllCourseActivity.class);
                intent.putExtra("ID", listItem.getId());
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

        ImageView categoryImage,nextButton;
        TextView categoryName, categoryCourseCount;
        LinearLayout linearLayoutColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            nextButton = itemView.findViewById(R.id.nextButton);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryCourseCount = itemView.findViewById(R.id.categoryCourseCount);
            linearLayoutColor = itemView.findViewById(R.id.linearLayoutColor);

        }
    }
}
