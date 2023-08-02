package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.kbc.playAlong.R;
import com.kbc.playAlong.adapter.Question_Adapter;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.model.Question_Model;
import com.kbc.playAlong.response.Points_Response;
import com.kbc.playAlong.response.Question_Response;
import com.kbc.playAlong.response.Setting_Response;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Question_Page extends AppCompatActivity {
    TextView txt_price,txt_time,txt_total_question,count_time,btn_submit;
    TextView txt_question,txt_option1,txt_option2,txt_option3,txt_option4;
    String token;
    RecyclerView recycler_view;
    Question_Adapter question_adapter;
    List<Question_Model> question_models = new ArrayList<>();
    ImageView question_back;
    private int currentQuestionIndex = 0;
    ProgressDialog progressDialog;
    String playing_date,points,questionId,accountId,url,image,l_status,q_status;
    RelativeLayout question_layout;
    String user_answer = "";
    List<Question_Response.Datum> arrayList =new ArrayList<>();
    List<String> questionaAnswers=new ArrayList<>();
    AlertDialog alertDialog;
    String image_url = "http://kbcplayalong.live/zpanel/public/uploads";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        token = loginPref.getString("TOKEN","null");
        accountId = loginPref.getString("ID","null");

        question_layout = findViewById(R.id.question_layout);
        btn_submit = findViewById(R.id.btn_submit);
        txt_question = findViewById(R.id.txt_question);
        txt_option1 = findViewById(R.id.txt_option1);
        txt_option2 = findViewById(R.id.txt_option2);
        txt_option3 = findViewById(R.id.txt_option3);
        txt_option4 = findViewById(R.id.txt_option4);

        txt_option1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                user_answer = txt_option1.getText().toString();
                txt_option1.setBackgroundResource(R.drawable.answ_bg);
                txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                txt_option3.setBackgroundResource(R.drawable.wrong_bg);
                txt_option4.setBackgroundResource(R.drawable.wrong_bg);
            }
        });
        txt_option2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                user_answer = txt_option2.getText().toString();
                txt_option2.setBackgroundResource(R.drawable.answ_bg);
                txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                txt_option3.setBackgroundResource(R.drawable.wrong_bg);
                txt_option4.setBackgroundResource(R.drawable.wrong_bg);
            }
        });
        txt_option3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                user_answer = txt_option3.getText().toString();
                txt_option3.setBackgroundResource(R.drawable.answ_bg);
                txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                txt_option4.setBackgroundResource(R.drawable.wrong_bg);
            }
        });
        txt_option4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                user_answer = txt_option4.getText().toString();
                txt_option4.setBackgroundResource(R.drawable.answ_bg);
                txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                txt_option3.setBackgroundResource(R.drawable.wrong_bg);
            }
        });

        question_back = findViewById(R.id.question_back);
        question_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        count_time = findViewById(R.id.count_time);
        txt_total_question = findViewById(R.id.txt_total_question);
        txt_time = findViewById(R.id.txt_time);
        txt_price = findViewById(R.id.txt_price);
        txt_price.setText((CharSequence) getIntent().getExtras().get("PRICE"));
        txt_time.setText((CharSequence) getIntent().getExtras().get("timer") + " Sec");
        count_time.setText((CharSequence) getIntent().getExtras().get("timer"));
        /*Timer Set*/
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        playing_date = dateFormat.format(currentDate);

        String timeStr = getIntent().getStringExtra("timer");
        if (timeStr != null) {
            int time = Integer.parseInt(timeStr) * 1000;
            new CountDownTimer(time, 1000) {
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    String SetTime = String.valueOf(seconds);
                    count_time.setText(SetTime);
                }
                public void onFinish() {
                    Intent i  = new Intent(Question_Page.this, ContestPage.class);
                    startActivity(i);
                }
            }.start();
        }
        else {

        }
        get_question();
        progressDialog = new ProgressDialog(Question_Page.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loader_layout);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }
    private void get_question() {
        Call<Question_Response> question_responseCall = ApiService.apiHolders().get_question(String.valueOf(getIntent().getExtras().get("C_ID")),token);
        question_responseCall.enqueue(new Callback<Question_Response>() {
            @Override
            public void onResponse(Call<Question_Response> call, Response<Question_Response> response) {
                if (response.isSuccessful()){
                    arrayList = response.body().getData();
                    questionId = arrayList.get(currentQuestionIndex).getId();

                    txt_total_question.setText(String.valueOf(arrayList.size()));
                    txt_question.setText(arrayList.get(0).getContestQuestion());
                    txt_option1.setText(arrayList.get(0).getOptionOne());
                    txt_option2.setText(arrayList.get(0).getOptionTwo());
                    txt_option3.setText(arrayList.get(0).getOptionThree());
                    txt_option4.setText(arrayList.get(0).getOptionFour());
                    btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!user_answer.equals("")){
                                if (user_answer.equals(arrayList.get(currentQuestionIndex).getAnswer())){
                                    True_question_point(questionId);
                                }
                                else {
                                    question_point(questionId);
                                }
                            }
                            else {
                                Snackbar errorBar;
                                errorBar = Snackbar.make(question_layout, "Please Give Your Answer!", Snackbar.LENGTH_LONG);
                                errorBar.setTextColor(getResources().getColor(R.color.white));
                                errorBar.setActionTextColor(getResources().getColor(R.color.white));
                                errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                                errorBar.show();
                            }
                        }
                    });
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Question_Response> call, Throwable t) {
                Toast.makeText(Question_Page.this, "Wait For Sometime", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    private void True_question_point(String questionId) {
        points = "1";
        Call<Points_Response> points_responseCall = ApiService.apiHolders().points(accountId,String.valueOf(getIntent().getExtras().get("C_ID")),
                questionId,user_answer,points,playing_date,token);
        points_responseCall.enqueue(new Callback<Points_Response>() {
            @Override
            public void onResponse(Call<Points_Response> call, Response<Points_Response> response) {
                if (response.isSuccessful()){
                    questionaAnswers.add(user_answer);
                    Snackbar errorBar;
                    errorBar = Snackbar.make(question_layout, "Your Answer Submit Successfully!", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.add));
                    errorBar.show();
                    currentQuestionIndex++;
                    user_answer = "";
                    if(questionaAnswers.size()<arrayList.size()) {
                        if (currentQuestionIndex < arrayList.size()) {
                            txt_question.setText(String.valueOf(arrayList.get(currentQuestionIndex).getContestQuestion()));
                            txt_option1.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionOne()));
                            txt_option2.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionTwo()));
                            txt_option3.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionThree()));
                            txt_option4.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionFour()));

                            txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                            txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                            txt_option3.setBackgroundResource(R.drawable.wrong_bg);
                            txt_option4.setBackgroundResource(R.drawable.wrong_bg);

                            btn_submit.setVisibility(View.VISIBLE);
                        }
                    }else{
                        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View layout = inflater.inflate(R.layout.winning_layout,null);
                        AlertDialog.Builder alertbox = new AlertDialog.Builder(Question_Page.this);
                        alertbox.setView(layout);
                        alertbox.setCancelable(false);
                        TextView btn_redeem = (TextView)layout.findViewById(R.id.btn_redeem);
                        alertDialog = alertbox.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        btn_redeem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Question_Page.this,ContestPage.class));
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Points_Response> call, Throwable t) {

            }
        });
    }
    private void question_point(String questionId) {
        points = "0";
        Call<Points_Response> points_responseCall = ApiService.apiHolders().points(accountId,String.valueOf(getIntent().getExtras().get("C_ID")),
                questionId,user_answer,points,playing_date,token);
        points_responseCall.enqueue(new Callback<Points_Response>() {
            @Override
            public void onResponse(Call<Points_Response> call, Response<Points_Response> response) {
                if (response.isSuccessful()){
                    questionaAnswers.add(user_answer);
                    Snackbar errorBar;
                    errorBar = Snackbar.make(question_layout, "Thanks!", Snackbar.LENGTH_LONG);
                    errorBar.setTextColor(getResources().getColor(R.color.white));
                    errorBar.setActionTextColor(getResources().getColor(R.color.white));
                    errorBar.setBackgroundTint(getResources().getColor(R.color.add));
                    errorBar.show();
                    currentQuestionIndex++;
                    user_answer = "";
                    if(questionaAnswers.size()<arrayList.size()) {
                    if (currentQuestionIndex < arrayList.size()) {
                        txt_question.setText(String.valueOf(arrayList.get(currentQuestionIndex).getContestQuestion()));
                        txt_option1.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionOne()));
                        txt_option2.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionTwo()));
                        txt_option3.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionThree()));
                        txt_option4.setText(String.valueOf(arrayList.get(currentQuestionIndex).getOptionFour()));

                        txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                        txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                        txt_option3.setBackgroundResource(R.drawable.wrong_bg);
                        txt_option4.setBackgroundResource(R.drawable.wrong_bg);
                        btn_submit.setVisibility(View.VISIBLE);
                    }
                }else{
                        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View layout = inflater.inflate(R.layout.winning_layout,null);
                        AlertDialog.Builder alertbox = new AlertDialog.Builder(Question_Page.this);
                        alertbox.setView(layout);
                        alertbox.setCancelable(false);
                        TextView btn_redeem = (TextView)layout.findViewById(R.id.btn_redeem);
                        alertDialog = alertbox.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        btn_redeem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Question_Page.this,ContestPage.class));
                            }
                        });
                }
                }
            }
            @Override
            public void onFailure(Call<Points_Response> call, Throwable t) {

            }
        });
    }
}