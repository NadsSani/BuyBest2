package com.starwings.app.buybestdirect.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guardanis.imageloader.ImageRequest;
import com.starwings.app.buybestdirect.ApiLinks;
import com.starwings.app.buybestdirect.DetailPage;
import com.starwings.app.buybestdirect.R;
import com.starwings.app.buybestdirect.data.AdContent;
import com.starwings.app.buybestdirect.data.MainMenuItem;

import java.util.ArrayList;

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.ViewHolder> {

    private ArrayList<AdContent> data;
    private Context parentObj;
    public AdListAdapter(ArrayList<AdContent> contents, Context Parent)
    {
        data=contents;
        parentObj=Parent;

    }
    @Override
    public AdListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.bb_ads_listing, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos=position;
        holder.txtCategory.setText(data.get(position).getAdTitle());
        Log.e("Path",ApiLinks.baseLink+data.get(position).getAdFeaturedImage());
        ImageRequest.create(holder.imgCategory)
                .setTargetUrl(ApiLinks.baseLink+data.get(position).getAdFeaturedImage())
                .execute();
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails(pos);
            }
        });
      //  Glide.with(parentObj).load(ApiLinks.baseLink+data.get(position).getAdFeaturedImage()).into(holder.imgCategory);

    }

    private void showDetails(int pos) {
        Intent detailIntent=new Intent(parentObj, DetailPage.class);
        detailIntent.putExtra("selectedAd",data.get(pos));
        parentObj.startActivity(detailIntent);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtCategory;
        public ImageView imgCategory;
        public Button btnDetails;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);
            txtCategory=(TextView)itemLayoutView.findViewById(R.id.txt_option);
            imgCategory=(ImageView)itemLayoutView.findViewById(R.id.img_holder);
            btnDetails=(Button)itemLayoutView.findViewById(R.id.btndetails);
            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
    }
}
