package com.example.soccertournamethelper;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class Add_tour_dialog_frag extends DialogFragment implements View.OnClickListener {
    private EditText editText_TournamentName, editText_TeamNumber, editText_WinPoint, editText_Drawpoint, editText_TourUID;
    private Button btn_discard, btn_save;
    Communicator communicatior;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        communicatior = (Communicator) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_tour_dialog_frag_layout, null);
        editText_TournamentName = view.findViewById(R.id.tourNameEditTxtID);
        editText_TeamNumber = view.findViewById(R.id.teamNumberEditTextID);
        editText_WinPoint = view.findViewById(R.id.winPointEditTextID);
        editText_Drawpoint = view.findViewById(R.id.drawPointEditTxtID);
        editText_TourUID = view.findViewById(R.id.tourUIDEditTxtID);
        btn_discard = view.findViewById(R.id.btnBackID);
        btn_save = view.findViewById(R.id.tourCreateDoneButtonID);


        btn_save.setOnClickListener(this);
        btn_discard.setOnClickListener(this);

        setCancelable(false);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tourCreateDoneButtonID:
                String tournamentName = editText_TournamentName.getText().toString().trim();
                String winnerPoint = editText_WinPoint.getText().toString().trim();
                String drawPoint = editText_Drawpoint.getText().toString().trim();
                String teamNumber = editText_TeamNumber.getText().toString().trim();
                String tourUID = editText_TourUID.getText().toString().trim();

                if (!TextUtils.isEmpty(tourUID) && !TextUtils.isEmpty(tournamentName) && !TextUtils.isEmpty(winnerPoint) &&
                        !TextUtils.isEmpty(drawPoint)) {
                    communicatior.dataParsMethod(tourUID, tournamentName, teamNumber, winnerPoint, drawPoint);
                    dismiss();
                    Intent intent = new Intent(getActivity(), TournamentsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    }
                else
                    Toast.makeText(getActivity(), "Fill up all fields", Toast.LENGTH_SHORT).show();


                    break;
                    case R.id.btnBackID:
                        dismiss();
                        break;
                }
        }

        interface Communicator {
            public void dataParsMethod(String tourUID, String tournamentName, String teamNumber, String winnerPoint, String drawPoint);
        }
    }

