package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kbc.playAlong.R;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.response.BlogDetails_Response;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogDetails extends AppCompatActivity {

    ImageView blog_back;
    TextView txt_heading,txt_publish,txt_date,txt_description;
    ImageView blog_image;
    String token;
    String image_url = "http://kbcplayalong.live/zpanel/public/uploads";
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = "Bearer "+ loginPref.getString("TOKEN","null");
        blog_details_Api();
        progressDialog = new ProgressDialog(BlogDetails.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loader_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        blog_back = findViewById(R.id.blog_back);
        blog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        blog_image = findViewById(R.id.blog_image);
        txt_heading = findViewById(R.id.txt_heading);
        txt_publish = findViewById(R.id.txt_publish);
        txt_date = findViewById(R.id.txt_date);
        txt_description = findViewById(R.id.txt_description);
    }

    private void blog_details_Api() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");

        Call<BlogDetails_Response> blog_responseCall = ApiService.apiHolders().get_blog_details(String.valueOf(getIntent().getExtras().get("blog_id")),token);
        blog_responseCall.enqueue(new Callback<BlogDetails_Response>() {
            @Override
            public void onResponse(Call<BlogDetails_Response> call, Response<BlogDetails_Response> response) {
                if (response.isSuccessful()){
                    Picasso.with(BlogDetails.this)
                            .load(image_url+response.body().getData().get(0).getImage())
                            .into(blog_image);
                    txt_heading.setText(response.body().getData().get(0).getTitle());
                    txt_date.setText(response.body().getData().get(0).getTitle());
                    txt_publish.setText("by" + response.body().getData().get(0).getPublisher());
                    txt_description.setText(response.body().getData().get(0).getDescription());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<BlogDetails_Response> call, Throwable t) {
                Toast.makeText(BlogDetails.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}