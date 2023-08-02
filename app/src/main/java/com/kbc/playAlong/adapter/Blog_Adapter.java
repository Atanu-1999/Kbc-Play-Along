package com.kbc.playAlong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kbc.playAlong.R;
import com.kbc.playAlong.activity.BlogDetails;
import com.kbc.playAlong.model.Blog_Model;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Blog_Adapter extends RecyclerView.Adapter<Blog_Adapter.ViewHolder>{

    public List<Blog_Model> blog_models;
    Context context;
    String image_url = "http://kbcplayalong.live/zpanel/public/uploads";
    String istDateTime;

    public Blog_Adapter(List<Blog_Model> blog_models, Context context) {
        this.blog_models = blog_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Blog_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_layout,parent,false);
        return new Blog_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Blog_Adapter.ViewHolder holder, int position) {
        Blog_Model blog_model = blog_models.get(position);
        Picasso.with(context)
                .load(image_url+blog_model.getImage())
                .into(holder.blog_image);
        holder.txt_heading.setText(blog_model.getHeading());
        holder.txt_description.setText(blog_model.getDesc());
        holder.txt_publish.setText("by " + blog_model.getPublish());

        SimpleDateFormat ukDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        ukDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date ukDateTime = ukDateFormat.parse(blog_model.getDate());
            SimpleDateFormat istDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            istDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            istDateTime = istDateFormat.format(ukDateTime);
            holder.txt_date.setText(istDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BlogDetails.class);
                i.putExtra("blog_id",blog_model.getId());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blog_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_description,txt_date,txt_publish,txt_heading;
        ImageView blog_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blog_image = itemView.findViewById(R.id.blog_image);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_publish = itemView.findViewById(R.id.txt_publish);
            txt_heading = itemView.findViewById(R.id.txt_heading);

        }
    }
}
