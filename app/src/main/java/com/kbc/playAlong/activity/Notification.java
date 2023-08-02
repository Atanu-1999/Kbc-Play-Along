package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kbc.playAlong.R;
import com.kbc.playAlong.adapter.Notification_Adapter;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.model.Notification_Model;
import com.kbc.playAlong.response.Notification_Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {

    ImageView notification_back;
    Notification_Adapter notification_adapter;
    List<Notification_Model> notification_models = new ArrayList<>();
    String token,accountId;
    RecyclerView recycler_view;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recycler_view  = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(layoutManager);
        my_notification();
        progressDialog = new ProgressDialog(Notification.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loader_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        notification_back = findViewById(R.id.notification_back);
        notification_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void my_notification() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        accountId = loginPref.getString("ID","null");

        Call<Notification_Response> notification_responseCall = ApiService.apiHolders().get_notification(accountId,token);
        notification_responseCall.enqueue(new Callback<Notification_Response>() {
            @Override
            public void onResponse(Call<Notification_Response> call, Response<Notification_Response> response) {
                if (response.isSuccessful()){
                    List<Notification_Response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Notification_Model notification_model = new Notification_Model(arrayList.get(i).getNotiTitle(),arrayList.get(i).getNotiDesc(),
                                arrayList.get(i).getSendDate(), arrayList.get(i).getNotiId());
                        notification_models.add(notification_model);
                    }
                    if (notification_models.size()>0){
                        notification_adapter = new Notification_Adapter(notification_models,getApplicationContext());
                        recycler_view.setAdapter(notification_adapter);
                    }
                    else {

                    }
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Notification_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Notification.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}