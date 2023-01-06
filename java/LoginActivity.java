package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userName, password;
    Button logInBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName =  findViewById(R.id.emailInputLoginID);
        password = findViewById(R.id.passwordInputLoginID);
        logInBtn = findViewById(R.id.loginID);
        db = new DatabaseHelper(this);

        logInBtn.setOnClickListener(v -> {
            if (isEmpty(userName)) {
                userName.setError("This field is required!");
            } else if (password.length() < 6) {
                password.setError("Password must be greater than five characters!");
            } else {
                boolean isVerified = db.verifyAccount(userName.getText().toString(),
                                                     password.getText().toString());
                if (isVerified) {
                    Toast.makeText(LoginActivity.this,
                            "Log in successfully",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    i.putExtra("haveAccount", "true");
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Invalid credentials!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isEmpty(EditText text) {
        boolean isWhiteSpace = (text.getText().toString()).matches("^\\s*$");
        return isWhiteSpace || text.length() == 0;
    }

    public void launchRegistrationActivity(View v) {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }
}