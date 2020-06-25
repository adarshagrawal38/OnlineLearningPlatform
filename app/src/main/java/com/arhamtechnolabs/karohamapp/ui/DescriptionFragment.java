package com.arhamtechnolabs.karohamapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.SharedPreferenceHelper;
import com.arhamtechnolabs.karohamapp.SingleCoursePage;
import com.arhamtechnolabs.karohamapp.config.ListURL;
import com.arhamtechnolabs.karohamapp.models.CourseModel;
import com.arhamtechnolabs.karohamapp.models.Transfer;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {


    public DescriptionFragment() {
        // Required empty public constructor
    }
    View root;
    String courseUrl = ListURL.GET_FULL_COURSE;
    TextView tvDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_description, container, false);
        tvDescription = root.findViewById(R.id.tvDescription);
        String data = "0";
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null){
             data = extras.getString("key_id", "1");
            System.out.println("DATA : " + data);
        }


        loadDesc(data);
        return root;
    }

    private void loadDesc(String x) {
        courseUrl = courseUrl + x;

        System.out.println("URl new : " + courseUrl);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, courseUrl,new Response.Listener<String>() {
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

                        CourseModel item = new CourseModel(
                                jsonObject.getString("id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("short_description"),
                                jsonObject.getString("description"),
                                jsonObject.getString("outcomes"),
                                jsonObject.getString("language"),
                                jsonObject.getString("category_id"),
                                jsonObject.getString("sub_category_id"),
                                jsonObject.getString("section"),
                                jsonObject.getString("requirements"),
                                jsonObject.getString("price"),
                                jsonObject.getString("discount_flag"),
                                jsonObject.getString("discounted_price"),
                                jsonObject.getString("level"),
                                jsonObject.getString("user_id"),
                                jsonObject.getString("thumbnail"),
                                jsonObject.getString("video_url"),
                                jsonObject.getString("date_added"),
                                jsonObject.getString("last_modified"),
                                jsonObject.getString("visibility"),
                                jsonObject.getString("is_top_course"),
                                jsonObject.getString("is_free_course"),
                                jsonObject.getString("course_overview_provider"),
                                jsonObject.getString("status"),
                                jsonObject.getString("rating"),
                                jsonObject.getString("name")
                        );



                        tvDescription.setText(Html.fromHtml(item.getDescription()));


                    }


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
