package com.arhamtechnolabs.karohamapp.ui;

import android.media.Rating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arhamtechnolabs.karohamapp.R;
import com.arhamtechnolabs.karohamapp.config.ListURL;
import com.arhamtechnolabs.karohamapp.models.CourseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructorDetailsFragment extends Fragment {


    String courseUrl = ListURL.GET_FULL_COURSE;
    View root;
    TextView instName;

    RatingBar ratingBarInst;


    public InstructorDetailsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root =inflater.inflate(R.layout.fragment_instructor_details, container, false);
        ratingBarInst = root.findViewById(R.id.ratingBarInst);


        instName = root.findViewById(R.id.instName);
        String data = "0";
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null){
            data = extras.getString("key_id", "1");
            System.out.println("DATA : " + data);
        }

        loadDesc(data);
        return root;
    }

    private void loadDesc(String data) {

        courseUrl = courseUrl + data;

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


                        instName.setText(Html.fromHtml(item.getName()));
                        ratingBarInst.setRating(Float.parseFloat(item.getRating()));

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
