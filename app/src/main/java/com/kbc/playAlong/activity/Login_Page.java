package com.kbc.playAlong.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kbc.playAlong.MainActivity;
import com.kbc.playAlong.R;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.response.Login_Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Page extends AppCompatActivity {

    TextView btn_otp,txt_new_account;
    EditText edit_phone,edit_user_password;
    String phoneNumber,deviceId,password;
    LinearLayout login_layout;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        deviceId = task.getResult();
                        Log.d(TAG, deviceId);
                    }
                });

        login_layout = findViewById(R.id.login_layout);
        txt_new_account = findViewById(R.id.txt_new_account);
        txt_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Page.this,Register_Page.class));
            }
        });

        edit_user_password = findViewById(R.id.edit_user_password);
        edit_phone = findViewById(R.id.edit_phone);
        btn_otp = findViewById(R.id.btn_otp);
        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_phone.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(login_layout, "Please Enter Valid Number", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_phone.requestFocus();
                }
                else if (edit_phone.length() > 10 || edit_phone.length() < 10){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(login_layout, "Please Enter 10 Digits numbers", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_phone.requestFocus();
                }
                else if (edit_user_password.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(login_layout, "Please Enter Password", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_password.requestFocus();
                }
                else if (edit_user_password.length() < 5){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(login_layout, "Please enter Min 5 Digit Pass", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_password.requestFocus();
                }
                else {
                    user_Login();
                    progressDialog = new ProgressDialog(Login_Page.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.loader_layout);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );
                }
            }
        });
    }
    private void user_Login() {
        phoneNumber = edit_phone.getText().toString();
        password = edit_user_password.getText().toString();
        Call<Login_Response> login_apiCall = ApiService.apiHolders().UserLogin(phoneNumber,deviceId,password);
        login_apiCall.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                if (response.isSuccessful()){
                    String id = String.valueOf(response.body().getData().get(0).getId());
                    Snackbar errorBar;
                    errorBar = Snackbar.make(login_layout, ""+response.body().getMessage(), Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.add));
                    errorBar.show();

                    Intent i = new Intent(Login_Page.this, MainActivity.class);
                    i.putExtra("number",edit_phone.getText().toString());
                    i.putExtra("d_token",response.body().getData().get(0).getDeviceId());
                    startActivity(i);

                    SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPref.edit();
                    editor.putString("ID",id);
                    editor.putString("D_TOKEN",response.body().getData().get(0).getDeviceId());
                    editor.commit();
                    progressDialog.dismiss();
                }
                else {

                }
            }
            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Snackbar errorBar;
                errorBar = Snackbar.make(login_layout, "Oops, Something went wrong please try again!", Snackbar.LENGTH_LONG);
                errorBar.setTextColor(getResources().getColor(R.color.white));
                errorBar.setActionTextColor(getResources().getColor(R.color.white));
                errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                errorBar.show();
                progressDialog.dismiss();
            }
        });
    }
}