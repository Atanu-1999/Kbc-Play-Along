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
import com.kbc.playAlong.model.Banner_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Banner_Adapter extends RecyclerView.Adapter<Banner_Adapter.ViewHolder>{

    public List<Banner_Model> slider_models;
    Context context;
    String image_url = "http://kbcplayalong.live/zpanel/public/uploads";

    public Banner_Adapter(List<Banner_Model> slider_models, Context context) {
        this.slider_models = slider_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Banner_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_layout,parent,false);
        return new Banner_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Banner_Adapter.ViewHolder holder, int position) {
        Banner_Model slider_model = slider_models.get(position);
        Picasso.with(context)
                .load(image_url+slider_model.getImage())
                .into(holder.banner_image);
        holder.txt_title.setText(slider_model.getDesc());
    }

    @Override
    public int getItemCount() {
        return slider_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView banner_image;
        TextView txt_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner_image = itemView.findViewById(R.id.banner_image);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }
}
