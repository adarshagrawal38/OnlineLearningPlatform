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
import com.arhamtechnolabs.karohamapp.config.ListURL;
import com.arhamtechnolabs.karohamapp.models.CourseModel;
import com.arhamtechnolabs.karohamapp.models.Duration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncludesFragment extends Fragment {

    public IncludesFragment() {
        // Required empty public constructor
    }

    View root;


    String durationURL = ListURL.GET_DURATION;

    TextView duration, totalLession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_includes, container, false);

        duration = root.findViewById(R.id.duration);
        totalLession = root.findViewById(R.id.totalLession);

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

        durationURL = durationURL + data;

        System.out.println("URl new : " + durationURL);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, durationURL,new Response.Listener<String>() {
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

                        Duration item = new Duration(
                                jsonObject.getString("section"),
                                jsonObject.getString("duration"),
                                jsonObject.getString("total_lession")
                        );


                        totalLession.setText(item.getTotal_lession() + " Lessions");
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
