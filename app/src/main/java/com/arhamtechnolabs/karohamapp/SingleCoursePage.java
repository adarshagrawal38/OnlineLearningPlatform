package com.arhamtechnolabs.karohamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arhamtechnolabs.karohamapp.Hepers.LocalDataBaseHelper;
import com.arhamtechnolabs.karohamapp.Hepers.LocalWishListHelper;
import com.arhamtechnolabs.karohamapp.adapters.CoursePagerAdapter;
import com.arhamtechnolabs.karohamapp.config.ListURL;
import com.arhamtechnolabs.karohamapp.models.CartModel;
import com.arhamtechnolabs.karohamapp.models.CourseModel;
import com.arhamtechnolabs.karohamapp.models.Transfer;
import com.arhamtechnolabs.karohamapp.ui.DescriptionFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SingleCoursePage extends AppCompatActivity {

    ViewPager pager1;
    String data;
    ImageView imageView;
    TextView textViewTitle, shortDesc, coursePriceTv;
    Button buyNowButton;
    String courseUrl = ListURL.GET_FULL_COURSE;
    CourseModel item;
    boolean isPresentInWishList = false;
    ImageView wishListIndicatorIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_course_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            data = extras.getString("key_id", "1");
        }

        imageView = findViewById(R.id.imageView);
        textViewTitle = findViewById(R.id.textView);
        buyNowButton = findViewById(R.id.buyNowButton);
        coursePriceTv = findViewById(R.id.coursePriceTv);
        wishListIndicatorIV = findViewById(R.id.wishListIndicatorIV);

        shortDesc = findViewById(R.id.shortDesc);

        getFullCourse(data);

        System.out.println("Data : " + data);
        pager1 = findViewById(R.id.pager1);


        CoursePagerAdapter myPagerAdapter = new CoursePagerAdapter(getSupportFragmentManager() , 0);
        pager1.setAdapter(myPagerAdapter);

        final TabLayout tabLayout1 = findViewById(R.id.tablayout1);
        tabLayout1.setupWithViewPager(pager1);

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDataBaseHelper dataBaseHelper = new LocalDataBaseHelper(getApplicationContext());
                CartModel cartModel = new CartModel(item);
                if (dataBaseHelper.isCourseAddedToCart(cartModel)) {
                    Toast.makeText(SingleCoursePage.this, "Already Added to cart!", Toast.LENGTH_SHORT).show();
                }else{
                    dataBaseHelper.insertData(cartModel);
                    Toast.makeText(SingleCoursePage.this, "Added to cart!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        wishListIndicatorIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalWishListHelper wishListHelper = new LocalWishListHelper(getApplicationContext());
                CartModel cartModel = new CartModel(item);
                if (isPresentInWishList) {
                    /*remove from whish list*/
                    wishListHelper.deleteItem(cartModel);
                    Toast.makeText(SingleCoursePage.this, "Removed from wish list!", Toast.LENGTH_SHORT).show();
                }else {
                    /*add to wish list*/
                    wishListHelper.insertData(cartModel);
                    Toast.makeText(SingleCoursePage.this, "Added to wish list!", Toast.LENGTH_SHORT).show();
                }
                updateWishIndicator();

            }
        });
    }

    private void updateWishIndicator() {
        LocalWishListHelper wishListHelper = new LocalWishListHelper(getApplicationContext());
        CartModel cartModel = new CartModel(item);
        isPresentInWishList = wishListHelper.isCourseAddedToWishList(cartModel);
        if (isPresentInWishList) {
            wishListIndicatorIV.setImageResource(R.drawable.ic_heart_small_selected);
        }else {
            wishListIndicatorIV.setImageResource(R.drawable.ic_heart_small);
        }
    }


    String desc = "";


    private void getFullCourse(String x) {

        courseUrl = courseUrl + x;

        System.out.println("URl : " + courseUrl);

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

                            item = new CourseModel(
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



                        textViewTitle.setText(item.getTitle());
                        System.out.println("Hello : " + item.getTitle());
                        Glide.with(getApplicationContext()).load(R.drawable.logo).into(imageView);
                        desc = item.getDescription();
                        System.out.println("GET : " + desc);
                        updateWishIndicator();

                        Transfer transfer = new Transfer();
                        transfer.setDesc(item.getDescription());
                        shortDesc.setText(item.getShort_description());
                        String price = item.getPrice();
                        if (item.getDiscount_flag().equals("1")) {
                            price= item.getDiscounted_price();
                        }
                        coursePriceTv.setText("Pay "+ Html.fromHtml("&#x20B9;") +" "+price+"/-");
//                        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(getApplicationContext());
//                        sharedPreferenceHelper.setDesc(item.getDescription());

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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public String myData(){
        return data;
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

