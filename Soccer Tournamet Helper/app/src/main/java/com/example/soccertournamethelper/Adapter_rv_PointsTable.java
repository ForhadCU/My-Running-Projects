package com.example.soccertournamethelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_rv_PointsTable extends RecyclerView.Adapter<Adapter_rv_PointsTable.MyViewHolder> {
    private ArrayList<Data_handler> listData_result;
    private Context context;
    private DbHelper dbHelper;

    public Adapter_rv_PointsTable(Context context, ArrayList<Data_handler> listData_result) {
        this.context = context;
        this.listData_result = listData_result;
        dbHelper = new DbHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_points_table_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Data_handler dhRef = listData_result.get(position);
        holder.vh_rank.setText(dhRef.getRank());
        holder.vh_teamName.setText(dhRef.getTeam());
        holder.vh_mp.setText(dhRef.getMP());
        holder.vh_win.setText(dhRef.getW());
        holder.vh_draw.setText(dhRef.getD());
        holder.vh_loss.setText(dhRef.getL());
        holder.vh_gf.setText(dhRef.getGF());
        holder.vh_ga.setText(dhRef.getGA());
        holder.vh_gd.setText(dhRef.getGD());
        holder.vh_pts.setText(dhRef.getPts());
    }

    @Override
    public int getItemCount() {
        return listData_result.size();
    }

   class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vh_rank, vh_teamName, vh_mp, vh_win, vh_draw, vh_loss, vh_gf, vh_ga, vh_gd, vh_pts;
       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           vh_rank = itemView.findViewById(R.id.lv_rankID);
           vh_teamName = itemView.findViewById(R.id.lv_pt_teamNameId);
           vh_mp = itemView.findViewById(R.id.lv_pt_mpID);
           vh_win = itemView.findViewById(R.id.lv_pt_winID);
           vh_draw = itemView.findViewById(R.id.lv_pt_drawID);
           vh_loss = itemView.findViewById(R.id.lv_pt_lossID);
           vh_gf = itemView.findViewById(R.id.lv_pt_gfID);
           vh_ga = itemView.findViewById(R.id.lv_pt_gaID);
           vh_gd = itemView.findViewById(R.id.lv_pt_gdID);
           vh_pts = itemView.findViewById(R.id.lv_pt_ptsID);
       }
   }
}
