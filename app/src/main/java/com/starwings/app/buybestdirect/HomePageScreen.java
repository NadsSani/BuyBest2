package com.starwings.app.buybestdirect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.starwings.app.buybestdirect.adapter.MainMenuAdapter;
import com.starwings.app.buybestdirect.data.MainMenuItem;

import java.util.ArrayList;

public class HomePageScreen extends AppCompatActivity {

    MainMenuAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.bb_home_page);

        /* Creating main menu */

        MainMenuItem menu1=new MainMenuItem();
        menu1.setTitle("REAL ESTATE");
        menu1.setImageresource(R.drawable.btn_restate);

        MainMenuItem menu2=new MainMenuItem();
        menu2.setTitle("FOUR WHEELER");
        menu2.setImageresource(R.drawable.btn_fwheeler);

        MainMenuItem menu3=new MainMenuItem();
        menu3.setTitle("TWO WHEELER");
        menu3.setImageresource(R.drawable.btn_two_wheeler);

        ArrayList<MainMenuItem> contents=new ArrayList<MainMenuItem>();
        contents.add(menu1);
        contents.add(menu3);
        contents.add(menu2);


        RecyclerView mainRecycle=(RecyclerView)findViewById(R.id.recMainMenu);
        mainRecycle.setHasFixedSize(true);

        // ListView
        mainRecycle.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MainMenuAdapter(contents,this);

        mainRecycle.setAdapter(mAdapter);





    }
}
