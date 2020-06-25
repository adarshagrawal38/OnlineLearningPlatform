package com.arhamtechnolabs.karohamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arhamtechnolabs.karohamapp.Hepers.LocalDataBaseHelper;
import com.arhamtechnolabs.karohamapp.adapters.CartAdapter;
import com.arhamtechnolabs.karohamapp.models.CartModel;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView checkOutRecyclerView;
    TextView checkOutTotalAmount_tv, checkOutcount_box_tv, checkOutConfirm_tv;
    LocalDataBaseHelper localDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        checkOutRecyclerView = findViewById(R.id.checkOutRecyclerView);
        checkOutRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        checkOutTotalAmount_tv = findViewById(R.id.checkOutTotalAmount);
        checkOutcount_box_tv = findViewById(R.id.checkOutcount_box);
        checkOutConfirm_tv = findViewById(R.id.checkOutConfirm);

         localDataBaseHelper = new LocalDataBaseHelper(getApplicationContext());

        List<CartModel> cartModels = localDataBaseHelper.fetchCartData();
        CartAdapter cartAdapter = new CartAdapter(this, cartModels);
        checkOutRecyclerView.setAdapter(cartAdapter);
        updateView();


        checkOutConfirm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Select mode of payment*/
                int numberOfItemsInCart = localDataBaseHelper.getTotalItemsInCart();
                if (numberOfItemsInCart < 1) {
                    Toast.makeText(CartActivity.this, "Add Some Item In Cart!", Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(CartActivity.this, "Procced to pay", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SelectModeOfPayment.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void updateView() {
        int numberOfItemsInCart = localDataBaseHelper.getTotalItemsInCart();
        int amount = localDataBaseHelper.getGrandTotal();
        checkOutcount_box_tv.setText(String.valueOf(numberOfItemsInCart));
        checkOutTotalAmount_tv.setText("Pay "+ Html.fromHtml("&#x20B9;") +" "+amount+"/-");
        if (numberOfItemsInCart < 1)onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}