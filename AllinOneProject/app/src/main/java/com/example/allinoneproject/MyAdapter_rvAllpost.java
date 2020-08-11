package com.example.allinoneproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter_rvAllpost extends RecyclerView.Adapter<MyAdapter_rvAllpost.MyViewHolder> {
    private List<Data_Handler> dataHandlerList;


    public MyAdapter_rvAllpost(List<Data_Handler> dataHandlerList) {
        this.dataHandlerList = dataHandlerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_allpost_custm_lay, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data_Handler current = dataHandlerList.get(position);
        holder.vTitle.setText(current.getTitle());
        holder.vDesc.setText(current.getDesc());
        Picasso.get().load(current.getImageUri()).into(holder.vImageView);
    }

    @Override
    public int getItemCount() {
        return dataHandlerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle, vDesc;
        ImageView vImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vTitle = itemView.findViewById(R.id.titleID);
            vDesc = itemView.findViewById(R.id.descID);
            vImageView = itemView.findViewById(R.id.imgViewID);
        }
    }
}
