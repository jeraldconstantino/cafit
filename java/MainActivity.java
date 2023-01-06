package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchLoginActivity(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void launchHomeActivity(View v) {
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("haveAccount", "false");
        startActivity(i);
    }

}