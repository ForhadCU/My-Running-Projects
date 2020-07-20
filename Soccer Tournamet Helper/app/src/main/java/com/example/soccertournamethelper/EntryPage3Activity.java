package com.example.soccertournamethelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EntryPage3Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_addTeam;
    private Button button_addTeam;
    private String tourUID_1;
    private DbHelper dbHelper;
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;
    final String[] from = new String[]{DbHelper.dID, DbHelper.dTeamNames};
    final int[] to = new int[]{R.id.tv_teamList_serialNoID, R.id.tv_teamList_viewID};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("TEAMS");
        setContentView(R.layout.activity_entry_page3);
        //set NavigationBar and statusBar color
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }//End

        editText_addTeam = findViewById(R.id.addTeamsEdittextID);
        button_addTeam = findViewById(R.id.addTeamButtonID);
        listView = findViewById(R.id.lvTeamListId);
        listView.setEmptyView(findViewById(R.id.tvEmptyList_1));

        Intent intent = getIntent();
        tourUID_1 = intent.getStringExtra("TourUID");

        dbHelper = new DbHelper(this);
        button_addTeam.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mCursorMethod();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addTeamButtonID:
                String teamName = editText_addTeam.getText().toString().trim();

                if (!TextUtils.isEmpty(teamName) && !TextUtils.isEmpty(tourUID_1))
                {
                    long id_1 = dbHelper.mAddTeams(tourUID_1, teamName);
                    if (id_1<0)
                        Toast.makeText(getApplicationContext(), "Failed to add team", Toast.LENGTH_SHORT).show();
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                        editText_addTeam.setText("");
                        editText_addTeam.requestFocus();

                        mCursorMethod();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_SHORT).show();


                break;


        }
    }

    private void mCursorMethod() {
        Cursor cursor1 = dbHelper.mShowTeamList(tourUID_1);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.lv_teamlist_lay, cursor1, from, to, 0);
        simpleCursorAdapter.notifyDataSetChanged();

        listView.setAdapter(simpleCursorAdapter);
    }
}

/*int mp = 0;
                int w = 0;
                int d = 0;
                int l = 0;
                int gf = 0;
                int ga = 0;
                int gd = 0;
                int pts = 0;*/
//                long id_1 = dbHelper.mAddTeams(tourUID_1, teamName, mp, w, d, l, gf, ga, gd, pts);
