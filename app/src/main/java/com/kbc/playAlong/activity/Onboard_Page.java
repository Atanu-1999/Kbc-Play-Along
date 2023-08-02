package com.kbc.playAlong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kbc.playAlong.R;


public class Onboard_Page extends AppCompatActivity {
    TextView btn_play_now,txt_call;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard_page);

        btn_play_now = findViewById(R.id.btn_play_now);
        btn_play_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboard_Page.this,Register_Page.class));
            }
        });
    }
}