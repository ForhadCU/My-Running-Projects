/*
package com.example.viewpager2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpager2.Model.Main_Model;
import com.example.viewpager2.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RV_Adapter_main2 extends RecyclerView.Adapter<RV_Adapter_main2.ViewHolder1> {

    private ArrayList<String> keyOfmyList;
    private HashMap<String, ArrayList<Main_Model>> myList;
    private Context context;

    private RV_Adapter_nested rv_adapter_nested;

    public RV_Adapter_main2(ArrayList<String> keyOfmyList, HashMap<String, ArrayList<Main_Model>> myList, Context context) {
        this.keyOfmyList = keyOfmyList;
        this.myList = myList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rv_mid_frag_main, parent, false);

        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        holder.projectName.setText(keyOfmyList.get(position));

        nestedRecylerViewHandler(holder.nestedRecyclerView, myList.get(keyOfmyList.get(position)));
    }


    @Override
    public int getItemCount() {
        return keyOfmyList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView projectName;
        RecyclerView nestedRecyclerView;
        ImageView imageView_Add;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.tv_rv_main_heading);
//            nestedRecyclerView =itemView.findViewById(R.id.rv_mid_frag_nested);
            imageView_Add = itemView.findViewById(R.id.imgV_rv_main_add);
        }
    }


    private void nestedRecylerViewHandler(RecyclerView nestedRecyclerView, ArrayList<Main_Model> main_models) {
        nestedRecyclerView.setHasFixedSize(true);
        nestedRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        rv_adapter_nested = new RV_Adapter_nested(main_models);
//        nestedRecyclerView.setAdapter(rv_adapter_nested);
    }

}
*/
