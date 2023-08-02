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
import com.kbc.playAlong.response.Pages_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pages extends AppCompatActivity {

    ImageView pages_back;
    TextView txt_page_name,txt_desc;
    String token;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

//        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = loginPref.edit();
//        Token = "Bearer "+ loginPref.getString("TOKEN","null");
        open_pages();
        progressDialog = new ProgressDialog(Pages.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loader_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        txt_desc = findViewById(R.id.txt_desc);
        txt_page_name = findViewById(R.id.txt_page_name);
        pages_back = findViewById(R.id.pages_back);

        pages_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void open_pages() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");

        Call<Pages_Response> pages_responseCall = ApiService.apiHolders().get_pages(String.valueOf(getIntent().getExtras().get("About")),token);
        pages_responseCall.enqueue(new Callback<Pages_Response>() {
            @Override
            public void onResponse(Call<Pages_Response> call, Response<Pages_Response> response) {
                if (response.isSuccessful()){
                    txt_page_name.setText(response.body().getData().get(0).getTitle());
                    txt_desc.setText(response.body().getData().get(0).getDescription());
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Pages_Response> call, Throwable t) {
                Toast.makeText(Pages.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}