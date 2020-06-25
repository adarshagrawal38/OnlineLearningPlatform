package com.arhamtechnolabs.karohamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arhamtechnolabs.karohamapp.Hepers.LocalDataBaseHelper;

public class SelectModeOfPayment extends AppCompatActivity {

    TextView paymentBtn;

    String selectedRadiotext;
    RadioGroup radioGroup;
    TextView totalTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode_of_payment);
        paymentBtn = findViewById(R.id.paymentBtn);
        radioGroup = findViewById(R.id.radioGroup);
        totalTextView = findViewById(R.id.totalTextView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        LocalDataBaseHelper localDataBaseHelper = new LocalDataBaseHelper(getApplicationContext());

        int amount = localDataBaseHelper.getGrandTotal();
        totalTextView.setText(String.valueOf(amount));
        paymentBtn.setText("Pay "+ Html.fromHtml("&#x20B9;") +" "+amount+"/-");

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadio = findViewById(selectedId);

                if (selectedRadio == null) {
                    Toast.makeText(getApplicationContext(), "Please select payment option!", Toast.LENGTH_SHORT).show();
                }else{
                    String text = selectedRadio.getText().toString();
                    selectedRadiotext = text;
                    if (text.equals("   Cash Payment")) {
                        Toast.makeText(SelectModeOfPayment.this, "Pay by cash", Toast.LENGTH_SHORT).show();
                        //confirmUserBooking(selectedDate, selectedTime, "Cash");
                    }else{
                        Toast.makeText(SelectModeOfPayment.this, "Pay through online mode", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

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
}