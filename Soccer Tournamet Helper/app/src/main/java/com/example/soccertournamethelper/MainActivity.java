package com.example.soccertournamethelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DbHelper dbHelper;
    private long backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        //set NavigationBar and statusBar color
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.mainActivity));
        }//End

        Button tournamentsButton = findViewById(R.id.btnLeagueID);
        //database connect
        dbHelper = new DbHelper(this);

        tournamentsButton.setOnClickListener(this);
    }

    //BackPressed Process with Toast
    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000)
        {
            backPressedTime = t;
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        else {
            super.onBackPressed();
        }
    }//END

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeagueID:
                Intent intent2 = new Intent(MainActivity.this, TournamentsActivity.class);
                startActivity(intent2);
                break;

           /* case R.id.btnViewID:
                String data = dbAdapter.selectMethod();
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                break;*/

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_recordID:
                FragmentManager manager = getSupportFragmentManager();
                Add_tour_dialog_frag add_tour_dialog_frag = new Add_tour_dialog_frag();
                add_tour_dialog_frag.show(manager, "tag_add_tour_dialog_frag");
                break;

        }

        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void dataParsMethod(String tournametName, String winnerPoint, String drawPoint) {
        long id = dbHelper.mCreateTournament(tournametName, winnerPoint, drawPoint);
        if (id < 0)
            Toast.makeText(getApplicationContext(), "insertion failed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();
    }
*/



    }
