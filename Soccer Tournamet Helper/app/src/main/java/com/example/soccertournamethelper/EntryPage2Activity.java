package com.example.soccertournamethelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class EntryPage2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button_save;
    private EditText editText_gf, editText_ga, editText_mp;
    private Spinner spinner_teamList;
    String tourID;
    DbHelper dbHelper;
    String selectedTeamName;
    int matchPoint;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int newMatchPoint = 0;
    String tourName;
    private ArrayList<String> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page2);
        dbHelper = new DbHelper(this);
        //set NavigationBar and statusBar color
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }//End

        Intent intent = getIntent();
        tourID = intent.getStringExtra("TourUID");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        tourName = sharedPreferences.getString("key_tourName", "Default");
        this.setTitle(tourName);

        button_save = findViewById(R.id.btnSaveResultID);
        editText_gf = findViewById(R.id.gfEditTextID);
        editText_ga = findViewById(R.id.gaEditTextID);
        editText_mp = findViewById(R.id.matchPlayEditTextID);
        spinner_teamList = findViewById(R.id.spinnerTeamListID);
        radioGroup = findViewById(R.id.radioGroupID);


        button_save.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mshowTeamList();
    }

    private void mshowTeamList() {
        teamList = dbHelper.mShowTeamArrayList(tourID);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_teamlist_layout, R.id.tv_teamList_viewID, teamList);
        spinner_teamList.setAdapter(arrayAdapter);
    }

/*    private void mCursorMehto() {
        Cursor cursor1 = dbHelper.mShowTeamList(tourID);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.spinner_teamlist_layout, cursor1, from, to, 0);
        simpleCursorAdapter.notifyDataSetChanged();

        spinner_teamList.setAdapter(simpleCursorAdapter);

    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveResultID:
                String getMP = editText_mp.getText().toString();
                String getGF = editText_gf.getText().toString();
                String getGA = editText_ga.getText().toString();


                if (!TextUtils.isEmpty(getMP) && !TextUtils.isEmpty(getGF) && !TextUtils.isEmpty(getGA))
                {
                    int mp = Integer.parseInt(getMP);
                    int gf = Integer.parseInt(getGF);  //or int gf = Integer.valueOf(getGF);
                    int ga = Integer.parseInt(getGA); //or int ga = Integer.valueOf(getGA);

                    selectedTeamName = mSpinner();
                    Cursor cursor = dbHelper.mReadSavedColumn(tourID, selectedTeamName);
                    int index1 = cursor.getColumnIndex(DbHelper.dMatch);
                    int preMatch = cursor.getInt(index1);
                    if (mp == preMatch+1)
                    {
                        int index2 = cursor.getColumnIndex(DbHelper.dWin);
                        int index3 = cursor.getColumnIndex(DbHelper.dDraw);
                        int index4 = cursor.getColumnIndex(DbHelper.dLoss);
                        int index5 = cursor.getColumnIndex(DbHelper.dGoalFOr);
                        int index6 = cursor.getColumnIndex(DbHelper.dGoalAgainst);
                        int index8 = cursor.getColumnIndex(DbHelper.dPoints);
                        int preWin = cursor.getInt(index2);
                        int preDraw = cursor.getInt(index3);
                        int preLoss = cursor.getInt(index4);
                        int preGoalFor = cursor.getInt(index5);
                        int preGoalAgainst = cursor.getInt(index6);
                        int prePoints = cursor.getInt(index8);

                        Cursor cursor1 = dbHelper.mReadPoints(tourID);
                        int index9 = cursor1.getColumnIndex(DbHelper.dWinnerPoint);
                        int index10 = cursor1.getColumnIndex(DbHelper.dDrawPoint);
                        int winnerPoint = cursor1.getInt(index9);
                        int drawPoint = cursor1.getInt(index10);

                        int rgSelectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = findViewById(rgSelectedId);
                        String radioText = radioButton.getText().toString();

                        if (radioText.equals("WIN"))
                        {
                            preWin++;
                            matchPoint = winnerPoint;
//                        matchPoint = 3;
                            newMatchPoint = prePoints + matchPoint;
                        }

                        else if (radioText.equals("LOSS"))
                        {
                            preLoss++;
                            matchPoint = 0;
                            newMatchPoint = prePoints + matchPoint;
                        }

                        else if (radioText.equals("DRAW")){
                            preDraw++;
                            matchPoint = drawPoint;
//                        matchPoint = 1;
                            newMatchPoint = prePoints + matchPoint;
                        }
                        int newGoalFor = gf + preGoalFor;
                        int newGoalAgainst = ga + preGoalAgainst;
                        int newGoalDiff = newGoalFor - newGoalAgainst;
//                    int newMatch = mp;

                        int count = dbHelper.mUpdatePointsTable(tourID, selectedTeamName, mp, preWin, preDraw, preLoss, newGoalFor, newGoalAgainst, newGoalDiff, newMatchPoint);
                        if (count > 0 )
                        {
                            Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_LONG).show();
                            radioGroup.clearCheck();
                            editText_gf.setText("");
                            editText_ga.setText("");
                            editText_mp.setText("");
                            editText_mp.requestFocus();
                            Intent intent = new Intent(getApplicationContext(), PointsTableActivity.class);
                            teamList.clear();
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Update unsuccessful", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Wrong MP!! \n\nIt should "+(preMatch+1)+"th match", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private String mSpinner() {
        selectedTeamName = spinner_teamList.getSelectedItem().toString();
        return selectedTeamName;
    }
}
