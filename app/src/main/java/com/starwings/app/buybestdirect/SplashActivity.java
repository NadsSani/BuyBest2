package com.starwings.app.buybestdirect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    Button btnEnter,btnAbout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.bb_splash_layout);
        btnEnter=(Button)findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayHomePage();
            }
        });

        btnAbout=(Button)findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAboutPage();
            }
        });

    }

    private void displayAboutPage() {
        Intent homePage=new Intent(this,AboutPageScreen.class);
        startActivity(homePage);
        finish();
    }

    private void displayHomePage() {
        Intent homePage=new Intent(this,HomePageScreen.class);
        startActivity(homePage);
        finish();
    }
}
