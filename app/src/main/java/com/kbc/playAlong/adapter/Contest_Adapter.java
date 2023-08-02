package com.kbc.playAlong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kbc.playAlong.R;
import com.kbc.playAlong.activity.Contest_Details;
import com.kbc.playAlong.model.Contest_Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Contest_Adapter extends RecyclerView.Adapter<Contest_Adapter.ViewHolder>{

    public List<Contest_Model> contest_models;
    Context context;
    String istDateTime;


    public Contest_Adapter(List<Contest_Model> contest_models, Context context) {
        this.contest_models = contest_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Contest_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_details,parent,false);
        return new Contest_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Contest_Adapter.ViewHolder holder, int position) {
        Contest_Model contest_model = contest_models.get(position);
        holder.txt_title.setText(contest_model.getTitle());

        SimpleDateFormat ukDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        ukDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date ukDateTime = ukDateFormat.parse(contest_model.getTime());
            SimpleDateFormat istDateFormat = new SimpleDateFormat("HH:mm", Locale.UK);
            istDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            istDateTime = istDateFormat.format(ukDateTime);
            holder.contest_time.setText(istDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(new Date());
        holder.contest_date.setText(startDate.replace("T","    "));

        holder.btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Contest_Details.class);
                i.putExtra("contest_id",contest_model.getContest_id());
                i.putExtra("contest_name",contest_model.getTitle());
                i.putExtra("contest_date",contest_model.getDate());
                i.putExtra("contest_time",istDateTime);
                i.putExtra("contest_fee",contest_model.getEntry_fee());
                i.putExtra("contest_win_price",contest_model.getWinning_prize());
                i.putExtra("contest_timer",contest_model.getTimmer());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contest_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,contest_time,contest_date,btn_start;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            contest_time = itemView.findViewById(R.id.contest_time);
            contest_date = itemView.findViewById(R.id.contest_date);
            btn_start = itemView.findViewById(R.id.btn_start);

        }
    }
}
