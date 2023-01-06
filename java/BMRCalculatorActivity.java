package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class BMRCalculatorActivity extends AppCompatActivity {
    EditText weightStr, heightStr, ageStr;
    RadioGroup sexRadioGroup;
    MaterialButton calculate_bmr;
    TextView bmr;
    String sex;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmrcalculator);

        weightStr = findViewById(R.id.BMRWeight);
        heightStr = findViewById(R.id.BMRHeight);
        ageStr = findViewById(R.id.BMRAge);
        bmr = findViewById(R.id.bmrTextID);
        sexRadioGroup = findViewById(R.id.sexRB);
        calculate_bmr = findViewById(R.id.calculateBMR);

        sex = "null";
        sexRadioGroup.setOnCheckedChangeListener((radioGroup, checkedButtonID) -> {
            sex = (checkedButtonID == R.id.maleRBBMRID) ? "male" : "female";
        });

        calculate_bmr.setOnClickListener(v -> {
            if (isEmpty(weightStr)) {
                weightStr.setError("This field is required!");
            } else if (isEmpty(heightStr)) {
                heightStr.setError("This field is required!");
            } else if (isEmpty(ageStr)){
                ageStr.setError("This field is required!");
            } else if (sex.equals("null")) {
                Toast.makeText(BMRCalculatorActivity.this, "Sex must be filled", Toast.LENGTH_SHORT).show();
            } else {
                double weight = Double.parseDouble(weightStr.getText().toString());
                double height = Double.parseDouble(heightStr.getText().toString());
                int age = Integer.parseInt(ageStr.getText().toString());
                double bmr_result = (10 * weight) + (6.25 * height) - (5 * age);

                if (sex.equals("male")) {
                    bmr_result += 5;
                } else {
                    bmr_result -= 161;
                }

                bmr.setText(String.format("%.2f", bmr_result));
            }
        });
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