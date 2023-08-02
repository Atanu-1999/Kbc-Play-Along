package com.kbc.playAlong.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kbc.playAlong.R;
import com.kbc.playAlong.model.Question_Model;

import java.util.List;

public class Question_Adapter extends RecyclerView.Adapter<Question_Adapter.ViewHolder>{

    public List<Question_Model> question_models;
    Context context;
    String d_answer,u_answer;

    public Question_Adapter(List<Question_Model> question_models, Context context) {
        this.question_models = question_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Question_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout,parent,false);
        return new Question_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Question_Adapter.ViewHolder holder, int position) {
        Question_Model question_model = question_models.get(position);
        holder.txt_question.setText(question_model.getContest_question());
        holder.txt_option1.setText(question_model.getOption_one());
        holder.txt_option2.setText(question_model.getOption_two());
        holder.txt_option3.setText(question_model.getOption_three());
        holder.txt_option4.setText(question_model.getOption_four());

        holder.txt_option1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                d_answer = question_model.getAnswer();
                u_answer = holder.txt_option1.getText().toString();
                holder.txt_option1.setBackgroundResource(R.drawable.answ_bg);
                holder.txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option3.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option4.setBackgroundResource(R.drawable.wrong_bg);
            }
        });
        holder.txt_option2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                d_answer = question_model.getAnswer();
                u_answer = holder.txt_option2.getText().toString();
                holder.txt_option2.setBackgroundResource(R.drawable.answ_bg);
                holder.txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option3.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option4.setBackgroundResource(R.drawable.wrong_bg);
            }
        });
        holder.txt_option3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                d_answer = question_model.getAnswer();
                u_answer = holder.txt_option3.getText().toString();
                holder.txt_option3.setBackgroundResource(R.drawable.answ_bg);
                holder.txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option4.setBackgroundResource(R.drawable.wrong_bg);
            }
        });
        holder.txt_option4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                d_answer = question_model.getAnswer();
                u_answer = holder.txt_option4.getText().toString();
                holder.txt_option4.setBackgroundResource(R.drawable.answ_bg);
                holder.txt_option1.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option2.setBackgroundResource(R.drawable.wrong_bg);
                holder.txt_option3.setBackgroundResource(R.drawable.wrong_bg);
            }
        });

    }

    @Override
    public int getItemCount() {
        return question_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btn_submit,txt_question,txt_option1,txt_option2,txt_option3,txt_option4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question = itemView.findViewById(R.id.txt_question);
//            txt_option1 = itemView.findViewById(R.id.txt_option1);
//            txt_option2 = itemView.findViewById(R.id.txt_option2);
//            txt_option3 = itemView.findViewById(R.id.txt_option3);
//            txt_option4 = itemView.findViewById(R.id.txt_option4);
            btn_submit = itemView.findViewById(R.id.btn_submit);
        }
    }
}
