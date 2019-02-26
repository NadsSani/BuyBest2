package com.starwings.app.buybestdirect.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.starwings.app.buybestdirect.ListingPage;
import com.starwings.app.buybestdirect.R;
import com.starwings.app.buybestdirect.data.MainMenuItem;

import java.util.ArrayList;
import java.util.Vector;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {

    private ArrayList<MainMenuItem> data;
    private Context parentObj;
    public MainMenuAdapter(ArrayList<MainMenuItem> contents,Context Parent)
    {
        data=contents;
        parentObj=Parent;

    }
    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.bb_main_cardview, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCategory.setText(data.get(position).getTitle());
        holder.imgCategory.setImageResource(data.get(position).getImageresource());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtCategory;
        public ImageView imgCategory;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);
            txtCategory=(TextView)itemLayoutView.findViewById(R.id.txt_option);
            imgCategory=(ImageView)itemLayoutView.findViewById(R.id.img_holder);
            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int itemPosition=getAdapterPosition();
                    callListingPage(itemPosition);

                }
            });
        }


    }
    private void callListingPage(int itemPosition) {

        Intent adListingPage=new Intent(parentObj, ListingPage.class);
        adListingPage.putExtra("category",itemPosition+1);
        parentObj.startActivity(adListingPage);
    }
}
