package com.arhamtechnolabs.karohamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arhamtechnolabs.karohamapp.Hepers.LocalDataBaseHelper;
import com.arhamtechnolabs.karohamapp.adapters.AllCourseAdapter;
import com.arhamtechnolabs.karohamapp.adapters.CategoryAdapter;
import com.arhamtechnolabs.karohamapp.config.ListURL;
import com.arhamtechnolabs.karohamapp.models.AllCourseModel;
import com.arhamtechnolabs.karohamapp.models.CategoryModel;
import com.arhamtechnolabs.karohamapp.models.TrendingCourseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCourseActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllCourse;
    private RecyclerView.Adapter adapterRV;
    private String allCourseUrl = ListURL.SELECT_ALL_COURSE;
    private List<AllCourseModel> allCourseModels;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_course);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            data = extras.getString("ID", "1");
        }

        recyclerViewAllCourse = findViewById(R.id.recyclerViewAllCourse);
        allCourseModels = new ArrayList<>();
        loadCourses(data);
    }

    private void loadCourses(String x) {

        allCourseUrl = allCourseUrl + x;

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, allCourseUrl,new Response.Listener<String>() {
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
                        AllCourseModel item = new AllCourseModel(
                                jsonObject.getString("id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("discounted_price"),
                                jsonObject.getString("level"),
                                jsonObject.getString("rating")
                        );

                        allCourseModels.add(item);

                    }

                    adapterRV = new AllCourseAdapter(getApplicationContext(),allCourseModels);
                    recyclerViewAllCourse.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                    recyclerViewAllCourse.setAdapter(adapterRV);
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

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_box_menu, menu);
        //MenuItem mSearch = menu.findItem(R.id.appOnlyShoppingCart);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.appShoppingCart:
                LocalDataBaseHelper localDataBaseHelper = new LocalDataBaseHelper(getApplicationContext());
                int numOfItems = localDataBaseHelper.getTotalItemsInCart();
                if (numOfItems > 0) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Add some course in cart!", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
