package com.kbc.playAlong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kbc.playAlong.activity.ContestPage;
import com.kbc.playAlong.activity.MyProfile;
import com.kbc.playAlong.activity.Notification;
import com.kbc.playAlong.activity.Otp_Verify;
import com.kbc.playAlong.activity.Pages;
import com.kbc.playAlong.adapter.Banner_Adapter;
import com.kbc.playAlong.adapter.Blog_Adapter;
import com.kbc.playAlong.adapter.Contest_Adapter;
import com.kbc.playAlong.adapter.Winner_Adapter;
import com.kbc.playAlong.api.ApiService;
import com.kbc.playAlong.model.Banner_Model;
import com.kbc.playAlong.model.Blog_Model;
import com.kbc.playAlong.model.Contest_Model;
import com.kbc.playAlong.model.Winner_Model;
import com.kbc.playAlong.response.Banner_Response;
import com.kbc.playAlong.response.Blog_Response;
import com.kbc.playAlong.response.Contest_response;
import com.kbc.playAlong.response.RecentWinner_Response;
import com.kbc.playAlong.response.UserProfile_Response;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ImageView menu,bell_image;
    RelativeLayout wallet;
    String token,deviceId;
    TextView txt_name,txt_number,txt_email,txt_viewAll,txt_balance,txt_recent_winner;
    RecyclerView slider_view,contest_view,blog_view,winners_view;
    List<Banner_Model> sliderView = new ArrayList<>();
    Banner_Adapter sliderAdapter;
    Contest_Adapter contest_adapter;
    List<Contest_Model> contest_models = new ArrayList<>();
    Blog_Adapter blog_adapter;
    List<Blog_Model> blog_models = new ArrayList<>();
    Winner_Adapter winner_adapter;
    List<Winner_Model> winner_models = new ArrayList<>();
    AlertDialog alertDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        editor.putString("TOKEN",getIntent().getStringExtra("d_token"));
        editor.commit();

        txt_recent_winner = findViewById(R.id.txt_recent_winner);
        txt_balance = findViewById(R.id.txt_balance);
        wallet = findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.wallet_layout);
                TextView btn_redeem = (TextView)dialog.findViewById(R.id.btn_redeem);
                TextView txt_amt = (TextView)dialog.findViewById(R.id.txt_amt);
                txt_amt.setText(loginPref.getString("Amount",""));
                btn_redeem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:9670053215"));
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

        txt_viewAll = findViewById(R.id.txt_viewAll);
        txt_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContestPage.class));
            }
        });
        nav = findViewById(R.id.navigation_drawer);
        View hView =  nav.getHeaderView(0);
        txt_email = hView.findViewById(R.id.txt_email);
        txt_name = hView.findViewById(R.id.txt_name);
        txt_number = hView.findViewById(R.id.txt_number);

        slider_view = findViewById(R.id.slider_view);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        slider_view.setLayoutManager(layoutManager1);
        get_Details();
        banner_Api();
        recent_contest();
        Blog_Api();
        recent_Winners();

        bell_image = findViewById(R.id.bell_image);
        bell_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Notification.class));
            }
        });
        menu = findViewById(R.id.menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.setDrawerIndicatorEnabled(false);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this, MyProfile.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.contest:
                        startActivity(new Intent(MainActivity.this, ContestPage.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.notification:
                        startActivity(new Intent(MainActivity.this, Notification.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.about:
                        Intent i = new Intent(MainActivity.this, Pages.class);
                        i.putExtra("About",3);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.pp:
                        Intent intent = new Intent(MainActivity.this,Pages.class);
                        intent.putExtra("About",2);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.use:
                        Intent use = new Intent(MainActivity.this,Pages.class);
                        use.putExtra("About",1);
                        startActivity(use);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.contact:
                        Intent contact = new Intent(MainActivity.this, Otp_Verify.class);
                        startActivity(contact);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Are you sure you want to exit");
                        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = loginPref.edit();
                                editor.clear();
                                editor.apply();
                                finishAffinity();
                            }
                        });
                        android.app.AlertDialog alert = builder.create();
                        alert.show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        /*contest Recycler View*/
        contest_view  = findViewById(R.id.contest_view);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        contest_view.setLayoutManager(layoutManager2);
        /*Blog Recycler View*/
        blog_view  = findViewById(R.id.blog_view);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        blog_view.setLayoutManager(layoutManager3);
        /*winner Recycler View*/
        winners_view  = findViewById(R.id.winners_view);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        winners_view.setLayoutManager(layoutManager4);
    }
    private void recent_Winners() {
        token = getIntent().getStringExtra("d_token");
        Call<RecentWinner_Response> winner_responseCall = ApiService.apiHolders().get_winner(token);
        winner_responseCall.enqueue(new Callback<RecentWinner_Response>() {
            @Override
            public void onResponse(Call<RecentWinner_Response> call, Response<RecentWinner_Response> response) {
                if (response.isSuccessful()){
                    List<RecentWinner_Response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Winner_Model winner_model = new Winner_Model(arrayList.get(i).getProfile(),
                                arrayList.get(i).getName(),arrayList.get(i).getWinningPrize());
                        winner_models.add(winner_model);
                    }
                    if (winner_models.size()>0){
                        winner_adapter = new Winner_Adapter(winner_models,getApplicationContext());
                        winners_view.setAdapter(winner_adapter);
                    }
                    else {

                    }
                }
            }

            @Override
            public void onFailure(Call<RecentWinner_Response> call, Throwable t) {
                txt_recent_winner.setVisibility(View.GONE);
                winners_view.setVisibility(View.GONE);
            }
        });
    }
    private void recent_contest() {
        token = getIntent().getStringExtra("d_token");

        Call<Contest_response> contest_responseCall = ApiService.apiHolders().get_contest(token);
        contest_responseCall.enqueue(new Callback<Contest_response>() {
            @Override
            public void onResponse(Call<Contest_response> call, Response<Contest_response> response) {
                if (response.isSuccessful()){
                    List<Contest_response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Contest_Model m_model = new Contest_Model(arrayList.get(i).getTitle(),arrayList.get(i).getStartTime(),
                                arrayList.get(i).getDate(), arrayList.get(i).getId(),arrayList.get(i).getEntryFee(),
                                arrayList.get(i).getWinningPrize(), arrayList.get(i).getTimmer());
                        contest_models.add(m_model);
                    }
                    if (contest_models.size()>0){
                        contest_adapter = new Contest_Adapter(contest_models,getApplicationContext());
                        contest_view.setAdapter(contest_adapter);
                    }
                    else {
                        Snackbar errorBar;
                        errorBar = Snackbar.make(contest_view, "Sorry! no Contest Available now", Snackbar.LENGTH_LONG);
                        errorBar.setTextColor(getResources().getColor(R.color.white));
                        errorBar.setActionTextColor(getResources().getColor(R.color.white));
                        errorBar.setBackgroundTint(getResources().getColor(R.color.red));
                        errorBar.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Contest_response> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void banner_Api() {
        token = getIntent().getStringExtra("d_token");
        Call<Banner_Response> banner = ApiService.apiHolders().User_benner(token);
        banner.enqueue(new Callback<Banner_Response>() {
            @Override
            public void onResponse(Call<Banner_Response> call, Response<Banner_Response> response) {
                if (response.isSuccessful()){
                    List<Banner_Response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Banner_Model slider_model = new Banner_Model(arrayList.get(i).getImage(),arrayList.get(i).getShortDesc());
                        sliderView.add(slider_model);
                        autoScroll();
                    }
                    if (sliderView.size()>0){
                        sliderAdapter = new Banner_Adapter(sliderView,getApplicationContext());
                        slider_view.setAdapter(sliderAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<Banner_Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void autoScroll(){
        final int speedScroll = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count == sliderAdapter.getItemCount())
                    count =0;
                if(count < sliderAdapter.getItemCount()){
                    slider_view.smoothScrollToPosition(++count);
                    handler.postDelayed(this,speedScroll);
                }
            }
        };
        handler.postDelayed(runnable,speedScroll);
    }
    private void get_Details() {
        token = getIntent().getStringExtra("d_token");
        Call<UserProfile_Response> get_profile = ApiService.apiHolders().get_user(token);
        get_profile.enqueue(new Callback<UserProfile_Response>() {
            @Override
            public void onResponse(Call<UserProfile_Response> call, Response<UserProfile_Response> response) {
                if (response.isSuccessful()){
                    txt_number.setText(response.body().getData().get(0).getPhoneNumber());
                    txt_email.setText(response.body().getData().get(0).getEmailId());
                    txt_name.setText(response.body().getData().get(0).getName());
                    txt_balance.setText(String.valueOf(response.body().getData().get(0).getWinningAmount()));

                    SharedPreferences loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPref.edit();
                    editor.putString("NAME",response.body().getData().get(0).getName() );
                    editor.putString("EMAIL",response.body().getData().get(0).getEmailId());
                    editor.putString("PHONE",response.body().getData().get(0).getPhoneNumber());
                    editor.putString("ACCOUNT_ID", String.valueOf(response.body().getData().get(0).getId()));
                    editor.putString("Amount", String.valueOf(response.body().getData().get(0).getWinningAmount()));
                    editor.commit();
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<UserProfile_Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Blog_Api() {
        token = getIntent().getStringExtra("d_token");
        Call<Blog_Response> blog_responseCall = ApiService.apiHolders().get_blog(token);
        blog_responseCall.enqueue(new Callback<Blog_Response>() {
            @Override
            public void onResponse(Call<Blog_Response> call, Response<Blog_Response> response) {
                if (response.isSuccessful()){
                    List<Blog_Response.Datum> arrayList = response.body().getData();
                    for (int i = 0;i<arrayList.size();i++){
                        Blog_Model blog_model = new Blog_Model(arrayList.get(i).getImage(),arrayList.get(i).getDescription(),
                                arrayList.get(i).getTitle(), arrayList.get(i).getPublisher(), arrayList.get(i).getDate(),arrayList.get(i).getId());
                        blog_models.add(blog_model);
                    }
                    if (blog_models.size()>0){
                        blog_adapter = new Blog_Adapter(blog_models,getApplicationContext());
                        blog_view.setAdapter(blog_adapter);
                    }
                    else {

                    }
                }
            }

            @Override
            public void onFailure(Call<Blog_Response> call, Throwable t) {

            }
        });

    }

}