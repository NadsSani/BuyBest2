package com.starwings.app.buybestdirect;

/**
 * Created by user on 25-10-2017.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.json.JSONObject;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutPageScreen extends AppCompatActivity {

    int viewcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simulateDayNight(/* DAY */ 0);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("We offer\n" +
                        "\n" +
                        "> Listing of Classifieds\n" +
                        "> Search New 'n' Pre - Owned Building, Four Wheeler or Two - Wheeler\n" +
                        "> Interaction via phone call, email and whatsapp")
                .addItem(new Element().setTitle("Version 1.0.0"))
                .addGroup("Connect with us")
                .addEmail("info@buybest.direct")
                .addWebsite("http://buybest.direct/")
                .addPlayStore("com.starwings.app.buybestdirect")

                .addGroup("Marketed By")
                .addEmail("info@starwingsindia.com")

                .create();

        setContentView(aboutPage);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void parseResponse(byte[] responseBody) throws Exception{
        String responseString=new String(responseBody);
        JSONObject countjson=new JSONObject(responseString);
        viewcount=countjson.getInt("viewcount");

    }





    void simulateDayNight(int currentSetting) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }
}