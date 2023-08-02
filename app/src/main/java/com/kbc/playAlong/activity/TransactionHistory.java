package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kbc.playAlong.R;
import com.kbc.playAlong.adapter.Transaction_Adapter;
import com.kbc.playAlong.model.Transaction_Model;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {

    Transaction_Adapter transaction_adapter;
    List<Transaction_Model> transaction_models = new ArrayList<>();
    String token,accountId;
    RecyclerView recycler_view;
    ImageView trns_back;
    RelativeLayout trans_layout;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

//        trans_layout = findViewById(R.id.trans_layout);
//        trns_back = findViewById(R.id.trns_back);
//        trns_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        recycler_view  = findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recycler_view.setLayoutManager(layoutManager);
//        my_transaction();
//        progressDialog = new ProgressDialog(TransactionHistory.this);
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.loader_layout);
//        progressDialog.getWindow().setBackgroundDrawableResource(
//                android.R.color.transparent
//        );
    }

   /* private void my_transaction() {
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        accountId = loginPref.getString("ACCOUNT_ID","null");

        Call<Transaction_Response> transaction_responseCall = ApiService.apiHolders().get_history(accountId,token);
        transaction_responseCall.enqueue(new Callback<Transaction_Response>() {
            @Override
            public void onResponse(Call<Transaction_Response> call, Response<Transaction_Response> response) {
                if (response.isSuccessful()){
                    List<Transaction_Response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Transaction_Model tr_model = new Transaction_Model(arrayList.get(i).getTitle(),arrayList.get(i).getCreatedAt(),
                                arrayList.get(i).getAmount());
                        transaction_models.add(tr_model);
                    }
                    if (transaction_models.size()>0){
                        transaction_adapter = new Transaction_Adapter(transaction_models,getApplicationContext());
                        recycler_view.setAdapter(transaction_adapter);
                    }
                    else {
                        Snackbar errorBar;
                        errorBar = Snackbar.make(trans_layout, "Sorry! no transaction Available now", Snackbar.LENGTH_LONG);
                        errorBar.setTextColor(getResources().getColor(R.color.white));
                        errorBar.setActionTextColor(getResources().getColor(R.color.white));
                        errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                        errorBar.show();
                    }
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Transaction_Response> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }*/
}