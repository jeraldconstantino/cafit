package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalculatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculators);
    }

    public void launchBMICalculatorActivity(View v) {
        Intent i = new Intent(this, BMICalculatorActivity.class);
        startActivity(i);
    }

    public void launchBMRCalculatorActivity(View v) {
        Intent i = new Intent(this, BMRCalculatorActivity.class);
        startActivity(i);
    }

//    public void launchTDEECalculatorActivity(View v) {
//        Intent i = new Intent(this, Tde.class);
//        startActivity(i);
//    }

//    public void launchCalorieBurnedCalculatorActivity(View v) {
//        Intent i = new Intent(this, CalorieBurnedCalculatorActivity.class);
//        startActivity(i);
//    }
}