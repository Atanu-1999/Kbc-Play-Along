package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kbc.playAlong.MainActivity;
import com.kbc.playAlong.R;
import com.kbc.playAlong.adapter.Contest_Adapter;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.model.Contest_Model;
import com.kbc.playAlong.response.Contest_response;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestPage extends AppCompatActivity {

    TextView btn_start;
    Contest_Adapter contest_adapter;
    List<Contest_Model> contest_models = new ArrayList<>();
    String token;
    RecyclerView recycler_view;
    ImageView contest_back;
    RelativeLayout contest_layout;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_page);

        contest_layout = findViewById(R.id.contest_layout);
        contest_back = findViewById(R.id.contest_back);
        contest_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        recycler_view  = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(layoutManager);
        my_contest();
        progressDialog = new ProgressDialog(ContestPage.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loader_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }

    private void my_contest() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");

        Call<Contest_response> contest_responseCall = ApiService.apiHolders().get_contest(token);
        contest_responseCall.enqueue(new Callback<Contest_response>() {
            @Override
            public void onResponse(Call<Contest_response> call, Response<Contest_response> response) {
                if (response.isSuccessful()){
                    List<Contest_response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Contest_Model m_model = new Contest_Model(arrayList.get(i).getTitle(),arrayList.get(i).getStartTime(),
                                arrayList.get(i).getDate(), arrayList.get(i).getId(), arrayList.get(i).getEntryFee(),
                                arrayList.get(i).getWinningPrize(),arrayList.get(i).getTimmer());
                        contest_models.add(m_model);
                    }
                    if (contest_models.size()>0){
                        contest_adapter = new Contest_Adapter(contest_models,getApplicationContext());
                        recycler_view.setAdapter(contest_adapter);
                    }
                    else {
                        Snackbar errorBar;
                        errorBar = Snackbar.make(contest_layout, "Sorry! no Contest Available now", Snackbar.LENGTH_LONG);
                        errorBar.setTextColor(getResources().getColor(R.color.white));
                        errorBar.setActionTextColor(getResources().getColor(R.color.white));
                        errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                        errorBar.show();
                    }
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Contest_response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ContestPage.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}