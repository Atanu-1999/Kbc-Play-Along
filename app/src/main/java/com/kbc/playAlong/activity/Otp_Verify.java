package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kbc.playAlong.MainActivity;
import com.kbc.playAlong.R;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.common.AuthenticationClass;
import com.kbc.playAlong.response.ContactResponse;
import com.kbc.playAlong.response.UserProfile_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Otp_Verify extends AppCompatActivity {

    ImageView contact_back;
    TextView btn_submit;
    EditText edite_name,edite_email,edite_phone,edit_user_address;
    RelativeLayout otp_verify_layout;
    String token,name,email,phone,message ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        edite_name = findViewById(R.id.edite_name);
        edite_email = findViewById(R.id.edite_email);
        edite_phone = findViewById(R.id.edite_phone);

        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        name = loginPref.getString("NAME","null");
        email = loginPref.getString("EMAIL","null");
        phone = loginPref.getString("PHONE","null");

        edite_name.setText(name);
        edite_phone.setText(phone);
        edite_email.setText(email);

        edit_user_address = findViewById(R.id.edit_user_address);
        otp_verify_layout =findViewById(R.id.otp_verify_layout);
        btn_submit = findViewById(R.id.btn_submit);
        contact_back = findViewById(R.id.contact_back);
        contact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = edite_email.getText().toString().trim();
                if (edite_name.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(otp_verify_layout, "Please Enter Name", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edite_name.requestFocus();
                }
                else if (edite_email.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(otp_verify_layout, "Please Enter Email", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edite_email.requestFocus();
                }
                else if (!AuthenticationClass.isValidEmail(emailid)){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(otp_verify_layout, "Please Enter Valid Email", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edite_email.requestFocus();
                }
                else if (edit_user_address.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(otp_verify_layout, "Please Enter your quire", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_address.requestFocus();
                }
                else {
                    post_contacr();
                }
            }
        });
    }

    private void post_contacr() {
        message = edit_user_address.getText().toString();
        Call<ContactResponse> getContact = ApiService.apiHolders().get_contact(name,email,phone,message,token);
        getContact.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if (response.isSuccessful()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(otp_verify_layout, "Enquiry Submit Successfully", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.add));
                    errorBar.show();
                    startActivity(new Intent(Otp_Verify.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });
    }
}