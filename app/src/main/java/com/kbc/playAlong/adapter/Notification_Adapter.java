package com.kbc.playAlong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kbc.playAlong.R;
import com.kbc.playAlong.model.Notification_Model;

import java.util.List;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.ViewHolder> {

    public List<Notification_Model> notification_models;
    Context context;

    public Notification_Adapter(List<Notification_Model> notification_models, Context context) {
        this.notification_models = notification_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Notification_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout,parent,false);
        return new Notification_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Notification_Adapter.ViewHolder holder, int position) {
        Notification_Model notification_model = notification_models.get(position);
        holder.txt_title.setText(notification_model.getNotiTitle());
        holder.txt_desc.setText(notification_model.getNotiDesc());
        holder.txt_date.setText(notification_model.getSendDate());
    }

    @Override
    public int getItemCount() {
        return notification_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_desc,txt_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_title = itemView.findViewById(R.id.txt_title);

        }
    }
}
