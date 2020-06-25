package com.arhamtechnolabs.karohamapp.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.adapters.CategoryAdapter;
import com.arhamtechnolabs.karohamapp.adapters.TrendingCourseAdapter;
import com.arhamtechnolabs.karohamapp.config.ListURL;
import com.arhamtechnolabs.karohamapp.models.CategoryModel;
import com.arhamtechnolabs.karohamapp.models.TrendingCourseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    //Course Page

    RecyclerView trendingCoursesRecyclerView, categoriesRecyclerView;
    private RecyclerView.Adapter adapterRV;

    private String trendingCourseURL = ListURL.VIEW_TRENDING_COURSE;
    private List<TrendingCourseModel> trendingCourses;

    private String categoryListURL = ListURL.VIEW_CATEGORIES_API;
    private List<CategoryModel> categoryList;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        trendingCoursesRecyclerView = root.findViewById(R.id.trendingCoursesRecyclerView);
        categoriesRecyclerView = root.findViewById(R.id.categoriesRecyclerView);

        trendingCourses = new ArrayList<>();
        categoryList = new ArrayList<>();

        loadTrendingCourses();
        loadCategories();

        return root;
    }

    private void loadCategories() {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, categoryListURL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    System.out.println(response);
                    JSONArray jsonArray = object.getJSONArray("data");

                    System.out.println(jsonArray);

                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        System.out.println(jsonObject);

                        CategoryModel item = new CategoryModel(
                                jsonObject.getString("id"),
                                jsonObject.getString("code"),
                                jsonObject.getString("name"),
                                jsonObject.getString("thumbnail")
                        );
                        categoryList.add(item);
                    }

                    adapterRV = new CategoryAdapter(getActivity(),categoryList);
                    categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                    categoriesRecyclerView.setAdapter(adapterRV);
                    adapterRV.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        } );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private void loadTrendingCourses() {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, trendingCourseURL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    System.out.println(response);
                    JSONArray jsonArray = object.getJSONArray("data");

                    System.out.println(jsonArray);

                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        System.out.println(jsonObject);

                        TrendingCourseModel item = new TrendingCourseModel(
                                jsonObject.getString("id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("short_description"),
                                jsonObject.getString("description"),
                                jsonObject.getString("language"),
                                jsonObject.getString("section"),
                                jsonObject.getString("price"),
                                jsonObject.getString("discounted_price"),
                                jsonObject.getString("level"),
                                jsonObject.getString("video_url"),
                                jsonObject.getString("status")
                        );
                        trendingCourses.add(item);
                    }

                    adapterRV = new TrendingCourseAdapter(getActivity(),trendingCourses);
                    trendingCoursesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                    trendingCoursesRecyclerView.setAdapter(adapterRV);
                    adapterRV.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        } );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
