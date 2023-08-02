package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kbc.playAlong.R;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.common.AuthenticationClass;
import com.kbc.playAlong.response.Update_Profile_Response;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile extends AppCompatActivity  {
    EditText edite_name,edite_email,edit_user_phone;
    String token,accountId;
    TextView btn_update;
    RelativeLayout profile_layout;
    ImageView profile_back;
    ProgressDialog progressDialog;

    String name,emailId,phone_no,deviceId,password,dob,city,pincode,address;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profile_back = findViewById(R.id.profile_back);
        profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profile_layout = findViewById(R.id.profile_layout);
        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        accountId = loginPref.getString("ACCOUNT_ID","null");

        edite_name = findViewById(R.id.edite_name);
        edite_email = findViewById(R.id.edite_email);
        edite_name.setText(loginPref.getString("NAME","null"));
        edite_email.setText(loginPref.getString("EMAIL","null"));

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = edite_email.getText().toString();
                if (edite_name.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(profile_layout, "Please Enter Name", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edite_name.requestFocus();
                }
                else if (edite_email.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(profile_layout, "Please Enter Email", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edite_email.requestFocus();
                }
                else if (!AuthenticationClass.isValidEmail(emailid)){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(profile_layout, "Please Enter Valid Email", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edite_email.requestFocus();
                }
                else {
                    update_profile();
                    progressDialog = new ProgressDialog(MyProfile.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.loader_layout);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );
                }
            }
        });
    }

    private void update_profile() {
        name = edite_name.getText().toString();
        emailId = edite_email.getText().toString();
        dob = "";
        city = "";
        pincode = "";
        address = "";
        Call<Update_Profile_Response> update_profile = ApiService.apiHolders().profile(accountId,name,emailId,dob,
                city,pincode,address,token);
        update_profile.enqueue(new Callback<Update_Profile_Response>() {
            @Override
            public void onResponse(Call<Update_Profile_Response> call, Response<Update_Profile_Response> response) {
                if (response.isSuccessful()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(profile_layout, "Profile Update Successfully", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.add));
                    errorBar.show();
                    progressDialog.dismiss();
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Update_Profile_Response> call, Throwable t) {
                Toast.makeText(MyProfile.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}