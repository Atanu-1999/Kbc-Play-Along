package com.kbc.playAlong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kbc.playAlong.R;
import com.kbc.playAlong.model.Transaction_Model;

import java.util.List;

public class Transaction_Adapter extends RecyclerView.Adapter<Transaction_Adapter.ViewHolder> {

    public List<Transaction_Model> transaction_models;
    Context context;

    public Transaction_Adapter(List<Transaction_Model> transaction_models, Context context) {
        this.transaction_models = transaction_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Transaction_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_layout,parent,false);
        return new Transaction_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Transaction_Adapter.ViewHolder holder, int position) {
        Transaction_Model transaction_model = transaction_models.get(position);
        holder.txt_contest_title.setText((transaction_model.getTitle()));
        holder.txt_amount.setText(String.valueOf(transaction_model.getAmount()));
        String setDate1 = String.valueOf(transaction_model.getCreatedAt());
        holder.txt_date.setText(setDate1.substring(0, setDate1.length() - 8).replace("T","    "));
    }

    @Override
    public int getItemCount() {
        return transaction_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_contest_title,txt_amount,txt_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_contest_title = itemView.findViewById(R.id.txt_contest_title);
            txt_amount = itemView.findViewById(R.id.txt_amount);
            txt_date = itemView.findViewById(R.id.txt_date);
        }
    }
}
