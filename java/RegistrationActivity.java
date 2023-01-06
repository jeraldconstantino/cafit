package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    EditText fullName, userName, age, password, confirmPassword;
    CheckBox termOfAgreement;
    Button signInBtn;
    DatabaseHelper db;
    RadioGroup radioGroup;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullName = findViewById(R.id.fullNameInputRegisterID);
        userName =  findViewById(R.id.userNameInputRegisterID);
        age = findViewById(R.id.ageInputRegisterID);
        password = findViewById(R.id.passwordInputRegisterID);
        confirmPassword = findViewById(R.id.confirmPasswordInputRegisterID);
        termOfAgreement = findViewById(R.id.agreeTOGID);
        signInBtn = findViewById(R.id.signInID);
        radioGroup = findViewById(R.id.sexRB);
        sex = "null";

        radioGroup.setOnCheckedChangeListener((radioGroup, checkedButtonID) -> {
            sex = (checkedButtonID == R.id.maleRBID) ? "male" : "female";
        });

        db = new DatabaseHelper(this);
    }

    public void signInUser(View v) {
        if (isEmpty(fullName)) {
            fullName.setError("This field is required!");
        } else if (isEmpty(userName)) {
            userName.setError("This field is required!");
        } else if (isEmpty(age)) {
            age.setError("This field is required!");
        } else if (sex.equals("null")) {
            Toast.makeText(RegistrationActivity.this, "Sex must be selected.", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            password.setError("Password must be greater than five characters.");
        } else if ((confirmPassword.getText().toString()).equals(password.getText().toString())) {
            if (!termOfAgreement.isChecked()) {
                Toast.makeText(RegistrationActivity.this,
                        "You must agree with the Terms and Conditions.",
                        Toast.LENGTH_SHORT).show();
            } else {
                boolean checkUser = db.checkUsername(userName.getText().toString());
                if (!checkUser) {
                    boolean isInserted = db.insertData(userName.getText().toString(),
                                                       fullName.getText().toString(),
                                                       age.getText().toString(), sex,
                                                       password.getText().toString());
                    if (isInserted) {
                        Toast.makeText(RegistrationActivity.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Registration failed, please try again later!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationActivity.this,
                            "The username is already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            confirmPassword.setError("Password does not match!");
        }
    }

    public boolean isEmpty(EditText text) {
        boolean isWhiteSpace = (text.getText().toString()).matches("^\\s*$");
        return isWhiteSpace || text.length() == 0;
    }

    public void launchLoginActivity(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void launchTSCDialog(View v) {
        Typeface typeface = ResourcesCompat.getFont(this, R.font.poppins_light);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.terms_and_conditions_layout, null);

        TextView textView = view.findViewById(R.id.termsAndConditionsID);
        textView.setTypeface(typeface);
        String message = "<br>\t\tPlease read these terms and conditions carefully before using CAFit mobile application operated by CAF Company. " +
                "<br><br><b>1. CONDITIONS OF USE </b><br>\t\tBy using this software, you certify that you have read and reviewed this Agreement and that you agree to comply with its terms. If you do not want to be bound by the terms of this Agreement, you are advised to leave the software accordingly. CAF only grants use and access of this mobile application, its products, and its services to those who have accepted its terms. " +
                "<br><br><b>2. INTELLECTUAL PROPERTY </b><br>\t\tYou agree that all materials, products, and services provided on this software are the property of CAF company, its affiliates, directors, officers, employees, agents, suppliers, or licensors including all copyrights, trade secrets, trademarks, patents, and other intellectual property. You also agree that you will not reproduce or redistribute the CAF’s intellectual property in any way, including electronic, digital, or new trademark registrations. " +
                "<br>\t\tYou grant CAF a royalty-free and non-exclusive license to display, use, copy, transmit, and broadcast the content you upload and publish. For issues regarding intellectual property claims, you should contact the company in order to come to an agreement. " +
                "<br><br><b>3. USER ACCOUNTS </b><br>\t\tAs a user of this mobile application, you may be asked to register with us and provide private information. You are responsible for ensuring the accuracy of this information, and you are responsible for maintaining the safety and security of your identifying information. You are also responsible for all activities that occur under your account or password. " +
                "<br>\t\tIf you think there are any possible issues regarding the security of your account on the software, inform us immediately so we may address them accordingly. " +
                "<br>\t\tWe reserve all rights to terminate accounts, edit or remove content and cancel orders at our sole discretion. " +
                "<br><br><b>4. APPLICABLE LAW </b><br>\t\tBy visiting this mobile application, you agree that the laws of the Philippines, without regard to principles of conflict laws, will govern these terms and conditions, or any dispute of any sort that might come between CAF company and you, or its business partners and associates. " +
                "<br><br><b>5. DISPUTES </b><br>\t\tAny dispute related in any way to your visit to this mobile application or to products you purchase from us shall be arbitrated by state or federal court in the Philippines and you consent to exclusive jurisdiction and venue of such courts. " +
                "<br><br><b>6. INDEMNIFICATION </b><br>\t\tYou agree to indemnify CAF and its affiliates and hold CAF harmless against legal claims and demands that may arise from your use or misuse of our services. We reserve the right to select our own legal counsel.  " +
                "<br><br><b>7. LIMITATION AND LIABILITY </b><br>\t\tCAF company is not liable for any damages that may occur to you as a result of your misuse of our website. " +
                "<br>\t\tCAF company reserves the right to edit, modify, and change this Agreement at any time. We shall let our users know of these changes through electronic mail. This Agreement is an understanding between CAF company and the user, and this supersedes and replaces all prior agreements regarding the use of this website. ";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(message));
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Terms and Conditions");
        alertDialog.setView(view);
        alertDialog.setPositiveButton("I UNDERSTAND!", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog alert = alertDialog.create();
        alert.show();

        Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF5C2A0B"));
    }
}