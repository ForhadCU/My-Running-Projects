package com.example.viewpager2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpager2.Model.Main_Model;
import com.example.viewpager2.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RV_Adapter_nested extends RecyclerView.Adapter<RV_Adapter_nested.ViewHolder2> {
    private ArrayList<Main_Model> clientList;

    public RV_Adapter_nested(ArrayList<Main_Model> clientList) {
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rv_mid_frag_nested, parent, false);

        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        Main_Model current = clientList.get(position);
//        holder.clientName.setText(current.getClients());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView clientName;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.tv_rv_nested_name);
        }
    }
}
