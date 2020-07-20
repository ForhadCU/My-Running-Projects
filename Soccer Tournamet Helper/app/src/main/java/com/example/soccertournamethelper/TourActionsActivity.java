package com.example.soccertournamethelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TourActionsActivity extends AppCompatActivity implements View.OnClickListener {
    private String tourID, tourName;
    private ListView listView_teamList;
    private Button button_ScoreEntry, button_AddTeams, button_showPointTable;

    private DbHelper dbHelper;
    private SimpleCursorAdapter simpleCursorAdapter;
    final String[] from = new String[]{DbHelper.dID, DbHelper.dTeamNames};
    final int[] to = new int[]{R.id.tv_teamList_serialNoID, R.id.tv_teamList_viewID};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_actions);
        //set NavigationBar and statusBar color
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }//End

        dbHelper = new DbHelper(this);

        Intent intent = getIntent();
        tourID = intent.getStringExtra("TourUID");
//        tourName = intent.getStringExtra("TourName");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        tourName = sharedPreferences.getString("key_tourName", "Default");

        this.setTitle(tourName);

        listView_teamList = findViewById(R.id.lvTeamListId);
        button_ScoreEntry = findViewById(R.id.btnScoreEntryId_1);
        button_AddTeams = findViewById(R.id.btnAddTeamsId_1);
        button_showPointTable = findViewById(R.id.btnShowPtsTableId);

        button_showPointTable.setOnClickListener(this);
        button_AddTeams.setOnClickListener(this);
        button_ScoreEntry.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mShowTeamList();
    }

    private void mShowTeamList() {
        Cursor cursor = dbHelper.mShowTeamList(tourID);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.lv_teamlist_lay, cursor, from, to, 0);
        simpleCursorAdapter.notifyDataSetChanged();
        listView_teamList.setAdapter(simpleCursorAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnScoreEntryId_1:
                Intent intent = new Intent(this, EntryPage2Activity.class);
                intent.putExtra("TourUID", tourID);
                startActivity(intent);
                break;
            case R.id.btnAddTeamsId_1:
                Intent intent1 = new Intent(this, EntryPage3Activity.class);
                intent1.putExtra("TourUID", tourID);
                startActivity(intent1);
                break;
            case R.id.btnShowPtsTableId:
                Intent intent2 = new Intent(this, PointsTableActivity.class);
                startActivity(intent2);


        }
    }
}


/*             SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("key", eventName);
               editor.commit();*/

/*
* //fetch eventName from AddEventActivity
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        eventName = sharedPreferences.getString("key", "Default");*/