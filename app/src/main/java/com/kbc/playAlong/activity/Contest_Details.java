package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kbc.playAlong.R;
import com.kbc.playAlong.adapter.RulesAdapter;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.model.Rules_Model;
import com.kbc.playAlong.response.ContestRules_Response;
import com.kbc.playAlong.response.JoinContest_Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contest_Details extends AppCompatActivity {
    TextView btn_play,txt_title,contest_time,contest_date;
    ImageView details_back;
    String Token,entry_fee,Winner_price,token,contestId,accountId;
    RulesAdapter rulesAdapter;
    List<Rules_Model> rules_models = new ArrayList<>();
    RecyclerView rules_view;
    final int PAY_REQUEST = 1;
    ProgressDialog progressDialog;
    final int UPI_PAYMENT = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_details);

        txt_title = findViewById(R.id.txt_title);
        contest_time = findViewById(R.id.contest_time);
        contest_date = findViewById(R.id.contest_date);
        /*Contest Details Set*/
        txt_title.setText((CharSequence) getIntent().getExtras().get("contest_name"));
        contest_date.setText((CharSequence) getIntent().getExtras().get("contest_date"));
        contest_time.setText((CharSequence) getIntent().getExtras().get("contest_time"));
        entry_fee = String.valueOf(getIntent().getExtras().get("contest_fee"));
        Winner_price = String.valueOf(getIntent().getExtras().get("contest_win_price"));
        /*Shared Prefarance*/
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        Token = "Bearer "+ loginPref.getString("TOKEN","null");

        rules_view  = findViewById(R.id.rules_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rules_view.setLayoutManager(layoutManager);
        contest_rules();
        progressDialog = new ProgressDialog(Contest_Details.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loader_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        details_back = findViewById(R.id.details_back);
        details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_play = findViewById(R.id.btn_play);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* Intent i = new Intent(Contest_Details.this,Question_Page.class);
               i.putExtra("PRICE",String.valueOf(getIntent().getExtras().get("contest_win_price")));
                i.putExtra("timer",String.valueOf(getIntent().getExtras().get("contest_timer")));
                i.putExtra("C_ID",String.valueOf(getIntent().getExtras().get("contest_id")));
                startActivity(i);*/
                join_contest();
            }
        });
    }

    private void join_contest() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        contestId = String.valueOf(getIntent().getExtras().get("contest_id"));
        accountId = loginPref.getString("ID","null");
        Call<JoinContest_Response> contest_join = ApiService.apiHolders().join_contest(accountId,contestId,token);
        contest_join.enqueue(new Callback<JoinContest_Response>() {
            @Override
            public void onResponse(Call<JoinContest_Response> call, Response<JoinContest_Response> response) {
                if (response.isSuccessful()){
                    Intent i = new Intent(Contest_Details.this,Question_Page.class);
                    i.putExtra("PRICE",String.valueOf(getIntent().getExtras().get("contest_win_price")));
                    i.putExtra("timer",String.valueOf(getIntent().getExtras().get("contest_timer")));
                    i.putExtra("C_ID",String.valueOf(getIntent().getExtras().get("contest_id")));
                    startActivity(i);
                    }
            }

            @Override
            public void onFailure(Call<JoinContest_Response> call, Throwable t) {

            }
        });

    }

    private void contest_rules() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        contestId = String.valueOf(getIntent().getExtras().get("contest_id"));

        Call<ContestRules_Response> rules_responseCall = ApiService.apiHolders().get_rules(contestId,token);
        rules_responseCall.enqueue(new Callback<ContestRules_Response>() {
            @Override
            public void onResponse(Call<ContestRules_Response> call, Response<ContestRules_Response> response) {
                    if (response.isSuccessful()){
                        List<ContestRules_Response.Datum> arrayList = response.body().getData();
                        for (int i = 0;i<arrayList.size();i++){
                            Rules_Model rules_model = new Rules_Model(arrayList.get(i).getTitle(),arrayList.get(i).getDescription());
                            rules_models.add(rules_model);
                        }
                        if (rules_models.size()>0){
                            rulesAdapter = new RulesAdapter(rules_models,getApplicationContext());
                            rules_view.setAdapter(rulesAdapter);
                        }
                        else {
                            Toast.makeText(Contest_Details.this, "No Rules Available", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                    else {
                        progressDialog.dismiss();
                    }
            }
            @Override
            public void onFailure(Call<ContestRules_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Contest_Details.this, "No Rules And Regulation", Toast.LENGTH_SHORT).show();
            }
        });
    }

}