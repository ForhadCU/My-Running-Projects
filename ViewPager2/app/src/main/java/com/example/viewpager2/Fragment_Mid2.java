package com.example.viewpager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.viewpager2.Adapters.RV_Adapter_main;
import com.example.viewpager2.Model.Main_Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Mid2 extends Fragment {
    private static final String TAG = "Fragment_Mid";
    private HashMap<String, ArrayList<Main_Model>> myList = new HashMap<>();
    private ArrayList<String> keyOfMyList = new ArrayList<>();

    private BottomNavigationView bottomNavigationView;
    private ImageView imageView_search;
    private RecyclerView recyclerView_main;

    private RV_Adapter_main rv_adapter_main ;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Called");

        mJsonParsing();


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Called before View");
        final View view = inflater.inflate(R.layout.layout_frag_mid, container, false);
        Log.d(TAG, "onCreateView: Called after View");

        bottomNavigationView = view.findViewById(R.id.nav_bottom);
        imageView_search = view.findViewById(R.id.imgV_search);
        recyclerView_main = view.findViewById(R.id.rv_mid_frag_main);

        recyclerViewHandler();
        clickSearchView();
        clickBottomNav();

        return view;
    }

    private void recyclerViewHandler() {
        recyclerView_main.setHasFixedSize(true);
        recyclerView_main.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rv_adapter_main = new RV_Adapter_main(keyOfMyList, myList, getContext());
        recyclerView_main.setAdapter(rv_adapter_main);
    }

    private void mJsonParsing() {
        try {
            JSONObject object = new JSONObject(Array_JSON.student_json);
            JSONArray jsonArray_data = object.getJSONObject("person").getJSONArray("data");

            ArrayList<Main_Model> arrayListofMainData = new ArrayList<>();

            for (int i = 0; i < jsonArray_data.length(); i++){
                JSONObject itemOf_jsonArray_data = jsonArray_data.getJSONObject(i);
                Main_Model main_model = new Main_Model();
                Field[] fields = main_model.getAllFields();

                for (int j = 0; j < fields.length; j++)
                {
                    String currenfieldName = fields[j].getName();
                    String jsonFieldValue = itemOf_jsonArray_data.has(currenfieldName) ? itemOf_jsonArray_data.getString(currenfieldName) : "";
                    fields[j].setAccessible(true);
                    fields[j].set(main_model, jsonFieldValue);
                }

            /*    if (myList.containsKey(main_model.getProjects()) && myList.get(main_model.getProjects()) != null){
                    myList.get(main_model.getProjects()).add(main_model);
                }else {
                    ArrayList<Main_Model> clientList = new ArrayList<>();
                    clientList.add(main_model);
                    myList.put(main_model.getProjects(), clientList);

                    keyOfMyList.add(main_model.getProjects());
                }*/

            arrayListofMainData.add(main_model);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "mJsonParsing: set Field value :: " + e.toString());
            e.printStackTrace();
        }
    }




    private void clickBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homeItem:
                        Toast.makeText(getActivity(), "Clicked Home", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.notifyItem:
                        Toast.makeText(getActivity(), "Clicked Notify", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.infoItem:
                        Toast.makeText(getActivity(), "Clicked Info", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.settingItem:
                        Toast.makeText(getActivity(), "Clicked Settings", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.locationItem:
                        Toast.makeText(getActivity(), "Clicked Location", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

    }

    private void clickSearchView() {
        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "searchView", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
