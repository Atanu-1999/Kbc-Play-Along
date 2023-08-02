package com.kbc.playAlong.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kbc.playAlong.R;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.common.AuthenticationClass;
import com.kbc.playAlong.response.Register_Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kbc.playAlong.response.Setting_Response;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView btn_register,btn_login,edit_user_dob;
    EditText edit_user_name,edit_user_email,edit_user_phone,edit_user_password,edit_user_address,edit_pin;
    LinearLayout signup_layout;
    String name,email,phone_no,deviceId,password,dob,city,pincode,address,token,Payment_url;
    ProgressDialog progressDialog;
    Spinner board_spinner;
    String[] value = {"West Bengal","Tamil Nadu","Bihar","Gujarat","Kerala","Rajasthan","Karnataka","Uttar Pradesh"};
    String image_url = "http://kbcplayalong.live/zpanel/public/uploads";
    private Calendar calendar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

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
        edit_user_dob = findViewById(R.id.edit_user_dob);
        edit_user_address = findViewById(R.id.edit_user_address);
        edit_pin = findViewById(R.id.edit_pin);
        board_spinner = findViewById(R.id.board_spinner);
        board_spinner.setOnItemSelectedListener(Register_Page.this);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList);
        board_spinner.setAdapter(arrayAdapter);

        signup_layout = findViewById(R.id.signup_layout);
        edit_user_phone = findViewById(R.id.edit_user_phone);
        edit_user_name = findViewById(R.id.edit_user_name);
        edit_user_email = findViewById(R.id.edit_user_email);
        edit_user_password = findViewById(R.id.edit_user_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_Page.this,Login_Page.class));
            }
        });
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = edit_user_email.getText().toString().trim();
                if (edit_user_name.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Name", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_name.requestFocus();
                }
                else if (edit_user_email.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Email", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_email.requestFocus();
                }
                else if (!AuthenticationClass.isValidEmail(emailid)){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Valid Email", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_email.requestFocus();
                }
                else if (edit_user_phone.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Phone Number", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_phone.requestFocus();
                }
                else if (edit_user_phone.length() > 10 || edit_user_phone.length() < 10){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Valid Phone Number", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_phone.requestFocus();
                }
                else if (edit_user_password.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter New Password", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_password.requestFocus();
                }
                else if (edit_user_password.length() < 5){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please enter Min 5 Digit Pass", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_password.requestFocus();
                }
                else if (edit_user_address.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Address", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_address.requestFocus();
                }
                else if (edit_user_address.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter Address", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_address.requestFocus();
                }
                else if (edit_user_dob.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter DOB", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_user_dob.requestFocus();
                }
                else if (edit_pin.getText().toString().trim().isEmpty()){
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Please Enter your Area Pincode", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                    edit_pin.requestFocus();
                }
                else {
                    user_register();
                    progressDialog = new ProgressDialog(Register_Page.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.loader_layout);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );
                }
            }
        });

        calendar = Calendar.getInstance();
        edit_user_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Register_Page.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                edit_user_dob.setText(dateFormat.format(calendar.getTime()));
                            }
                        },
                        year,
                        month,
                        dayOfMonth);
                datePickerDialog.show();
            }
        });
    }
    private void user_register() {
        name = edit_user_name.getText().toString();
        email = edit_user_email.getText().toString();
        phone_no = edit_user_phone.getText().toString();
        password = edit_user_password.getText().toString();
        String dateString = edit_user_dob.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
            dob =  dateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        address = edit_user_address.getText().toString();
        pincode = edit_pin.getText().toString();
        Call<Register_Response> register_apiCall = ApiService.apiHolders().UserRegister(name,email,phone_no,dob,city,pincode,address,deviceId,password);
        register_apiCall.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                String register_response = response.body().getCode();
                if (register_response.equalsIgnoreCase(String.valueOf(200))){
                     if (response.isSuccessful()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register_Page.this);
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
                        builder.setView(dialogView);
                        builder.setTitle("Registration Successfully");
                        builder.setPositiveButton("Proceed Next", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register_Page.this);
                                LayoutInflater inflater = getLayoutInflater();
                                View dialogView = inflater.inflate(R.layout.qr_layout, null);
                                builder.setView(dialogView);
                                AlertDialog dialog1 = builder.create();
                                dialog1.show();
                                dialog1.setCanceledOnTouchOutside(false);
                                TextView btn_contest = dialog1.findViewById(R.id.btn_contest);
                                TextView txt_payment = dialog1.findViewById(R.id.txt_payment);
                                TextView txt_upi = dialog1.findViewById(R.id.txt_upi);
                                ImageView qr_image = dialog1.findViewById(R.id.qr_image);
                                ImageView cancell = dialog1.findViewById(R.id.cancell);
                                token = deviceId;
                                Call<Setting_Response> setting_responseCall = ApiService.apiHolders().get_setting(token);
                                setting_responseCall.enqueue(new Callback<Setting_Response>() {
                                    @Override
                                    public void onResponse(Call<Setting_Response> call, Response<Setting_Response> response) {
                                        if (response.isSuccessful()){
                                            List<Setting_Response.Datum> response1 = response.body().getData();
                                            Payment_url = response1.get(0).getPaymentLink();
                                            txt_upi.setText(response1.get(0).getUpiAddress());
                                            txt_payment.setText(response1.get(0).getPaymentLink());
                                            Picasso.with(Register_Page.this)
                                                    .load(image_url+response.body().getData().get(0).getQrimage())
                                                    .into(qr_image);
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Setting_Response> call, Throwable t) {

                                    }
                                });
                                txt_upi.setMovementMethod(LinkMovementMethod.getInstance());
                                txt_payment.setMovementMethod(LinkMovementMethod.getInstance());
                                cancell.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Register_Page.this, Login_Page.class));
                                    }
                                });
                                btn_contest.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Register_Page.this, Login_Page.class));
                                    }
                                });
                                txt_payment.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent webViewIntent = new Intent(Register_Page.this, WebViewActivity.class);
                                        webViewIntent.putExtra("paymentUrl", Payment_url);
                                        startActivity(webViewIntent);
                                    }
                                });
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);
                        progressDialog.dismiss();
                    }
                    else {
                        Snackbar errorBar;
                        errorBar = Snackbar.make(signup_layout, "Please Check Your Internet", Snackbar.LENGTH_LONG);
                        errorBar.setTextColor(getResources().getColor(R.color.white));
                        errorBar.setActionTextColor(getResources().getColor(R.color.white));
                        errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                        errorBar.show();
                        progressDialog.dismiss();
                    }
                }
                else {
                    Snackbar errorBar;
                    errorBar = Snackbar.make(signup_layout, "Oops, Phone number already exists!!!", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                    errorBar.show();
                }
            }
            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                Toast.makeText(Register_Page.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        city = board_spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}