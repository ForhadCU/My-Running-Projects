package com.example.soccertournamethelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PointsTableActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private String tourName, tourID;
    private ArrayList<Data_handler> listAllData = new ArrayList<>();
    private Adapter_rv_PointsTable adapter_rv_pointsTable;


    /*
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;
    final String[] from = new String[]{DbHelper.dID, DbHelper.dTeamNames, DbHelper.dMatch, DbHelper.dWin, DbHelper.dDraw,
                                        DbHelper.dLoss, DbHelper.dGoalFOr, DbHelper.dGoalAgainst, DbHelper.dGoalDiff, DbHelper.dPoints};
    final int[] to = new int[]{R.id.lv_pt_serialNoID, R.id.lv_pt_teamNameId, R.id.lv_pt_mpID, R.id.lv_pt_winID, R.id.lv_pt_drawID,
                                R.id.lv_pt_lossID, R.id.lv_pt_gfID, R.id.lv_pt_gaID, R.id.lv_pt_gdID, R.id.lv_pt_ptsID};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_table);
        //set NavigationBar and statusBar color
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }//End
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        tourName = sharedPreferences.getString("key_tourName", "Default");
        tourID = sharedPreferences.getString("key_tourID", "Default");
        this.setTitle(tourName);

        dbHelper = new DbHelper(this);
        RecyclerView rv = findViewById(R.id.rv_id);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listAllData = dbHelper.mListData(tourID);

        if (listAllData.size() > 0)
        {
            rv.setVisibility(View.VISIBLE);
            adapter_rv_pointsTable = new Adapter_rv_PointsTable(this, listAllData);
            rv.setAdapter(adapter_rv_pointsTable);
        }
        else
        {
            rv.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Point Table is empty", Toast.LENGTH_LONG).show();
        }

      /*  Cursor cursor = dbHelper.mSelectAllResult(tourID);
//        listData_result.clear();
        if (cursor.moveToFirst())
        {
            do {
                int index1 = cursor.getColumnIndex(DbHelper.dTeamNames);
                int index2 = cursor.getColumnIndex(DbHelper.dWin);
                int index3 = cursor.getColumnIndex(DbHelper.dDraw);
                int index4 = cursor.getColumnIndex(DbHelper.dLoss);
                int index5 = cursor.getColumnIndex(DbHelper.dGoalFOr);
                int index6 = cursor.getColumnIndex(DbHelper.dGoalAgainst);
                int index8 = cursor.getColumnIndex(DbHelper.dPoints);
                int index9 = cursor.getColumnIndex(DbHelper.dMatch);
                String teamName = cursor.getString(index1);
                String preWin = cursor.getString(index2);
                String preDraw = cursor.getString(index3);
                String preLoss = cursor.getString(index4);
                String preGoalFor = cursor.getString(index5);
                String preGoalAgainst = cursor.getString(index6);
                String prePoints = cursor.getString(index8);
                String match = cursor.getString(index9);
                int i = count++;
                String counter = Integer.toString(i);

                listData_result.add(new Data_handler(counter, teamName, match, preWin, preDraw, preLoss, preGoalFor, preGoalAgainst, prePoints));
            }while (cursor.moveToNext());
            cursor.close();
            count = 0;
        }

        if (listData_result.size() > 0)
        {
            rv.setVisibility(View.VISIBLE);
            Adapter_rv_PointsTable adapter_rv_pointsTable = new Adapter_rv_PointsTable(listData_result);
            rv.setAdapter(adapter_rv_pointsTable);

        }
        else
        {
            rv.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Point Table is empty", Toast.LENGTH_LONG).show();
        }*/
        /*simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.lv_points_table_layout, cursor, from, to, 0);
        simpleCursorAdapter.notifyDataSetChanged();
        listView.setAdapter(simpleCursorAdapter);*/


    }
}
