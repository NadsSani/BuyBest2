package com.starwings.app.buybestdirect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.guardanis.imageloader.ImageRequest;
import com.starwings.app.buybestdirect.data.AdContent;

public class DetailPage extends AppCompatActivity {
    ImageView adFeaturedImage;
    TextView txtTitle,txtdesciption,txtClient,txt_category,txt_sub_category,txt_phone,txt_email,txt_ad_expiry;
    Button btnphone,btnwhatsapp,btnsms,btnmail,btnshare;
    AdContent adContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bb_ad_detail_page);
        adContent=(AdContent)getIntent().getSerializableExtra("selectedAd");
        adFeaturedImage=(ImageView)findViewById(R.id.imageFeatured);
        ImageRequest.create(adFeaturedImage)
                .setTargetUrl(ApiLinks.baseLink+adContent.getAdFeaturedImage())
                .execute();
        txtTitle=(TextView)findViewById(R.id.txtTitle);
        txtTitle.setText(adContent.getAdTitle());

        txtdesciption=(TextView)findViewById(R.id.txtdesciption);
        txtdesciption.setText(adContent.getAdDescription());

        txtClient=(TextView)findViewById(R.id.txtClient);
        txtClient.setText(adContent.getClientDisplayName());

        txt_category=(TextView)findViewById(R.id.txt_category);
        txt_category.setText(adContent.getCategoryName());

        txt_sub_category=(TextView)findViewById(R.id.txt_sub_category);
        txt_sub_category.setText(adContent.getSubCategoryName());

        txt_phone=(TextView)findViewById(R.id.txt_phone);
        txt_phone.setText(adContent.getPhoneNo());

        txt_email=(TextView)findViewById(R.id.txt_email);
        txt_email.setText(adContent.getEmail());

        txt_ad_expiry=(TextView)findViewById(R.id.txt_ad_expiry);
        txt_ad_expiry.setText(adContent.getAdExpiry());


        btnphone=(Button)findViewById(R.id.btnphone);
        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initiatePhone(adContent.getPhoneNo());
            }
        });

        btnwhatsapp=(Button)findViewById(R.id.btnwhatsapp);
        btnwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWhatsapp(adContent.getPhoneNo());
            }
        });

        btnsms=(Button)findViewById(R.id.btnsms);
        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendSMS(adContent);
            }
        });
        btnmail=(Button)findViewById(R.id.btnmail);
        btnmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMail(adContent.getEmail());
            }
        });
        btnshare=(Button)findViewById(R.id.btnshare);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareContent(adContent);
            }
        });

    }

    private void sendSMS(AdContent adContent) {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Snackbar snackbar = Snackbar
                    .make(adFeaturedImage, "Need Permission to Send SMS", Snackbar.LENGTH_LONG)
                    .setAction("SETTINGS", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            openSettings();
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        else {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", "Subject : " + adContent.getAdTitle());
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);
        }

    }

    public void initiatePhone(String phone) {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Snackbar snackbar = Snackbar
                    .make(adFeaturedImage, "Need Permission to initiate Phone Call", Snackbar.LENGTH_LONG)
                    .setAction("SETTINGS", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            openSettings();
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        else {
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel:"+phone));
            startActivity(phoneIntent);
        }

    }


    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }


    public void shareContent(AdContent current) {
        String card_site_page= current.getAdTitle()+"\n"+current.getAdDescription()+"\n"+current.getCategoryName()+"\n"+current.getSubCategoryName()+"\n"+current.getNameOfClient()+"\n"+current.getPhoneNo();
        Intent intent=new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

// Add data to the intent, the receiving app will decide what to do with it.
        intent.putExtra(Intent.EXTRA_SUBJECT, current.getAdTitle());
        intent.putExtra(Intent.EXTRA_TEXT, card_site_page);
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }
    public void sendMail(String mail) {
        if(mail.equals("NA"))
        {
            Snackbar.make(adFeaturedImage,"No Mail Address Specified",Snackbar.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mail});
        i.putExtra(Intent.EXTRA_SUBJECT, "Enquiry");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Snackbar.make(adFeaturedImage,"There are no email clients installed.",Snackbar.LENGTH_SHORT).show();


        }
    }

    public void openWhatsapp(String phone) {
        if(phone.equals("NA")||phone.equals(""))
        {
            Snackbar.make(adFeaturedImage,"No Phone Number Specified",Snackbar.LENGTH_SHORT).show();
            return;
        }
        Uri uri = Uri.parse("smsto:" + phone);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }
}
