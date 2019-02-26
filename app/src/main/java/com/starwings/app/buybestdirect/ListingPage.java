package com.starwings.app.buybestdirect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.starwings.app.buybestdirect.adapter.AdListAdapter;
import com.starwings.app.buybestdirect.adapter.MainMenuAdapter;
import com.starwings.app.buybestdirect.data.AdContent;
import com.starwings.app.buybestdirect.data.ImageResource;
import com.starwings.app.buybestdirect.data.VideoResource;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListingPage extends AppCompatActivity {

    AdListAdapter mAdapter;
    RadioGroup rbgroup;
    ArrayList<AdContent> adContentsMain;
    RecyclerView mainRecycle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bb_listing_page);
        mainRecycle=(RecyclerView)findViewById(R.id.recAdList);
        int category=getIntent().getIntExtra("category",0);

        rbgroup=findViewById(R.id.choiceGroup);
        rbgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedId);
                if(checkedRadioButton.getText().toString().trim().toUpperCase().equals("NEW"))
                {
                    filterAdContent("NEW");
                }
                else
                {
                    filterAdContent("PRE-OWNED");
                }
            }
        });

        fetchAdDetails(category);



    }

    private  ArrayList<AdContent> filterAdContent(String catname)
    {
        ArrayList<AdContent> adContents=new ArrayList<AdContent>();

        for(int i=0;i<adContentsMain.size();i++)
        {
            if(adContentsMain.get(i).getSubCategoryName().toUpperCase().equals(catname.toUpperCase()))
            {
                adContents.add(adContentsMain.get(i));
            }
        }
        mAdapter = new AdListAdapter(adContents,this);
//
        mainRecycle.setAdapter(mAdapter);
        return adContents;
    }
    private void fetchAdDetails(int category) {

        AsyncHttpClient adClient=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("apikey","1987654321");
        params.put("category",category);
        adClient.post(ApiLinks.adDetails, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    parseSuccessResponse(responseBody);
                } catch (Exception e) {
                    Log.e("Response",e.getClass().getName());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                parseFailResponse(error);
            }
        });
    }

    private void parseFailResponse(Throwable error) {

        Log.e("Response",error.getClass().getName());
    }

    private void parseSuccessResponse(byte[] responseBody) throws Exception {

        String response=new String(responseBody);
        JSONArray responseArray=new JSONArray(response);
        ArrayList<AdContent> adContents=new ArrayList<AdContent>();
        for(int i=0;i<responseArray.length();i++)
        {
            JSONObject responseJSon=(responseArray.getJSONObject(i));
            AdContent temp=new AdContent();
            temp.setSlNo(responseJSon.getInt("slno"));
            temp.setAdTitle(responseJSon.getString("adTitle"));
            temp.setAdDescription(responseJSon.getString("adDescription"));
            temp.setAdCategory(responseJSon.getString("adCategory"));
            temp.setAdSubCategory(responseJSon.getString("adSubCategory"));
            temp.setAdClient(responseJSon.getString("adClient"));
            temp.setAdResources(responseJSon.getString("adResources"));
            temp.setAdReleaseDate(responseJSon.getString("adReleaseDate"));
            temp.setAdExpiry(responseJSon.getString("adExpiry"));
            temp.setAdAmount(responseJSon.getString("adAmount"));
            temp.setAdStatus(responseJSon.getString("adStatus"));
            temp.setAdFeaturedImage(responseJSon.getString("adFeaturedImage"));
            temp.setCategoryName(responseJSon.getString("category_name"));
            temp.setSubCategoryName(responseJSon.getString("sub_cat_name"));
            temp.setNameOfClient(responseJSon.getString("name_of_client"));
            temp.setEmail(responseJSon.getString("email"));
            temp.setPhoneNo(responseJSon.getString("phoneno"));
            temp.setClientDisplayName(responseJSon.getString("client_display_name"));

            JSONArray imagesArray=responseJSon.getJSONArray("images");
            for(int j=0;j<imagesArray.length();j++)
            {
                JSONObject tempimage=imagesArray.getJSONObject(j);
                ImageResource imgResource=new ImageResource();
                imgResource.setSlno(tempimage.getInt("slno"));
                imgResource.setAdID(tempimage.getInt("adID"));
                imgResource.setImgPath(tempimage.getString("imgPath"));
                temp.getAdImageResources().add(imgResource);
            }

            JSONArray videoArray=responseJSon.getJSONArray("videos");
            for(int j=0;j<videoArray.length();j++)
            {
                JSONObject tempimage=videoArray.getJSONObject(j);
                VideoResource vidResource=new VideoResource();
                vidResource.setSlno(tempimage.getInt("slno"));
                vidResource.setAdID(tempimage.getInt("adID"));
                vidResource.setVidPath(tempimage.getString("vidPath"));
                temp.getAdVideoResources().add(vidResource);
            }

            adContents.add(temp);



        }

        mainRecycle.setHasFixedSize(true);
//
//        // ListView
        mainRecycle.setLayoutManager(new LinearLayoutManager(this));
//
        mAdapter = new AdListAdapter(adContents,this);
//
        mainRecycle.setAdapter(mAdapter);

        adContentsMain=adContents;
    }
}
