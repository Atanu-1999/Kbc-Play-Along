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
import com.kbc.playAlong.model.Rules_Model;

import java.util.List;

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.ViewHolder> {

    public List<Rules_Model> rules_models;
    Context context;

    public RulesAdapter(List<Rules_Model> rules_models, Context context) {
        this.rules_models = rules_models;
        this.context = context;
    }

    @NonNull
    @Override
    public RulesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rules_layout,parent,false);
        return new RulesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RulesAdapter.ViewHolder holder, int position) {
        Rules_Model rules_model = rules_models.get(position);
        holder.txt_title.setText(rules_model.getTitle());
        holder.txt_desc.setText(rules_model.getDescription());

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txt_desc.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txt_desc.setVisibility(View.GONE);
                holder.minus.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rules_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_desc;
        ImageView plus,minus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_desc = itemView.findViewById(R.id.txt_desc);

            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
        }
    }
}
