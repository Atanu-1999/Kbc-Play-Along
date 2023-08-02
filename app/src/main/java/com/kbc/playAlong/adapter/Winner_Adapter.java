package com.kbc.playAlong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kbc.playAlong.R;
import com.kbc.playAlong.model.Winner_Model;

import java.util.List;

public class Winner_Adapter extends RecyclerView.Adapter<Winner_Adapter.ViewHolder>{

    public List<Winner_Model> winner_models;
    Context context;
    String image_url = "http://192.168.29.231/KbcAdmin/public/uploads";

    public Winner_Adapter(List<Winner_Model> winner_models, Context context) {
        this.winner_models = winner_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Winner_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.winners_layout,parent,false);
        return new Winner_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Winner_Adapter.ViewHolder holder, int position) {
        Winner_Model winner_model = winner_models.get(position);
//        Picasso.with(context)
//                .load(image_url+winner_model.getImage())
//                .into(holder.user_image);
        holder.txt_winner_price.setText(String.valueOf(winner_model.getPrice()));
        holder.txt_name.setText(winner_model.getName());
    }

    @Override
    public int getItemCount() {
        return winner_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_winner_price,txt_name;
        ImageView user_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_winner_price = itemView.findViewById(R.id.txt_winner_price);
            user_image = itemView.findViewById(R.id.user_image);
        }
    }
}
