package com.example.soccertournamethelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TournamentsActivity extends AppCompatActivity implements Add_tour_dialog_frag.Communicator {
    private DbHelper dbHelper;
    private ListView listView;
    private TextView emptyTextview;
    private SimpleCursorAdapter simpleCursorAdapter;
    final String[] from = new String[]{DbHelper.dID, DbHelper.dTourUID, DbHelper.dTournamentName, DbHelper.dTeamNumber};
    final int[] to = new int[]{R.id.tvUIDid, R.id.tvTourIdID, R.id.tvTournamentNameID, R.id.tvTeamNumID};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Tournament List");
        setContentView(R.layout.activity_tournaments);
        //set NavigationBar and statusBar color
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }//End


        dbHelper = new DbHelper(this);
        Cursor cursor = dbHelper.selectMethod();

        listView = findViewById(R.id.lvTournamentListID);
        listView.setEmptyView(findViewById(R.id.tvEmptyList));

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.tour_list_view_layout, cursor, from, to, 0);
        simpleCursorAdapter.notifyDataSetChanged();

        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tourID = view.findViewById(R.id.tvTourIdID);
                TextView tourName = view.findViewById(R.id.tvTournamentNameID);

                String getTourID = tourID.getText().toString();
                String getTourName = tourName.getText().toString();

                Intent intent = new Intent(getApplicationContext(), TourActionsActivity.class);
                intent.putExtra("TourUID", getTourID);
//                intent.putExtra("TourName", getTourName);
                //Using SharedPreference, Transfer data to another Activity
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("key_tourName", getTourName);
                editor.putString("key_tourID", getTourID);
                editor.commit();
                startActivity(intent);
            }
        });


    }

    /*//work for me (MyTag: 1111)
    public void btnViewProcess(View view) {
        String data = dbAdapter.selectMethod();
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tournaments_acticity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_add_recordID:
                FragmentManager fragmentManager = getSupportFragmentManager();
                Add_tour_dialog_frag dialog_frag = new Add_tour_dialog_frag();
                dialog_frag.show(fragmentManager, "add_Dialog_frag_tourListActivity");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dataParsMethod(String tourUID, String tournamentName, String teamNumber, String winnerPoint, String drawPoint) {
        long id = dbHelper.mCreateTournament(tourUID, tournamentName, teamNumber, winnerPoint, drawPoint);


        if (id<0)
            Toast.makeText(getApplicationContext(), "Failed to add new tournament", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
        }
    }
}
