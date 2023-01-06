package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class BMICalculatorActivity extends AppCompatActivity {
    EditText weightStr, heightStr;
    MaterialButton calculate_bmi;
    TextView bmi, weightStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        weightStr = findViewById(R.id.weight);
        heightStr = findViewById(R.id.height);
        calculate_bmi = findViewById(R.id.calculateBMI);
        bmi = findViewById(R.id.bmiTextID);
        weightStatus = findViewById(R.id.weightStatusID);
    }

    @SuppressLint("DefaultLocale")
    public void calcBMI(View v) {
        if (isEmpty(weightStr)) {
            weightStr.setError("This field is required!");
        } else if (isEmpty(heightStr)) {
            heightStr.setError("This field is required!");
        } else {
            double weight = Double.parseDouble(weightStr.getText().toString());
            double height = Double.parseDouble(heightStr.getText().toString());
            double bmi_result = weight / (Math.pow(height, 2));
            String weightState = "Extremely Obese";

            if (bmi_result < 18.5) {
                weightState = "Wasted";
            } else if (bmi_result < 24.9) {
                weightState = "Normal";
            } else if (bmi_result < 29.9) {
                weightState = "Overweight";
            } else if (bmi_result < 39.9) {
                weightState = "Obese";
            }
            bmi.setText(String.format("%.2f", bmi_result));
            weightStatus.setText(weightState);
        }
    }

    public boolean isEmpty(EditText text) {
        boolean isWhiteSpace = (text.getText().toString()).matches("^\\s*$");
        return isWhiteSpace || text.length() == 0;
    }

    public void launchCalculatorsActivity(View v) {
        Intent i = new Intent(this, CalculatorsActivity.class);
        startActivity(i);
    }
}