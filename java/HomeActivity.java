package com.example.cafit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {
    MaterialButton workOutActivityBtn;
    ImageView noAccessLockIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        String haveAccount = i.getStringExtra("haveAccount");
        workOutActivityBtn = findViewById(R.id.workoutsBtnID);
        noAccessLockIcon = findViewById(R.id.noAccessID);

        if (haveAccount.equals("true")) {
            workOutActivityBtn.setOnClickListener(v -> {
                Intent j = new Intent(this, WorkOutsActivity.class);
                startActivity(j);
            });
        } else {
            workOutActivityBtn.setBackgroundColor(0xE3E3DC);
            workOutActivityBtn.setIconTintResource(com.google.android.material.R.color.material_on_surface_disabled);
            noAccessLockIcon.setVisibility(View.VISIBLE);

            workOutActivityBtn.setOnClickListener(v -> {
                showDialog("REMINDER!", "You must have an account to access this feature.");
            });
        }
    }

    public void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("LOG IN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                launchLoginActivity();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        positiveButton.setTextColor(Color.parseColor("#FF5C2A0B"));
        negativeButton.setTextColor(Color.parseColor("#FF5C2A0B"));
    }

    public void launchLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void launchCalculatorsActivity(View v) {
        Intent i = new Intent(this, CalculatorsActivity.class);
        startActivity(i);
    }

    public void exitApp(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Application?");
        builder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("YES",
                        (dialog, id) -> {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        })
                .setNegativeButton("NO",
                        (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        positiveButton.setTextColor(Color.parseColor("#FF5C2A0B"));
        negativeButton.setTextColor(Color.parseColor("#FF5C2A0B"));
    }

    public void openAbout(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ABOUT");
        builder
                .setCancelable(false)
                .setPositiveButton("OKAY",
                        (dialog, id) -> dialog.cancel());

        String message = "CAFit is an application software ... " +
                "<br><br><b>Developers:</b><br>\t\t\tGeraldine Candelaria<br>" +
                "\t\t\tJerald Constantino<br>" +
                "\t\t\tDeborah Ruth Espiritu<br>" +
                "\t\t\tLance Chester Malaluan<br>" +
                "\t\t\tJonelle Ervyn Mesa<br><br><b>Version:</b> 1.0";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
        } else {
            builder.setMessage(Html.fromHtml(message));
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF5C2A0B"));
    }
}