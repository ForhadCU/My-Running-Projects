/*
package com.example.viewpager2.;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.allproject.Adapter.DataRetriveAdapter;
import com.example.allproject.Class.DataRetrive;
import com.example.allproject.Class.Product;
import com.example.allproject.Class.Student;
import com.example.allproject.Constant.JsonArray;
import com.example.allproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    HashMap<String, ArrayList<Student>> myList = new HashMap<>() ;

    ArrayList<String> keyofMyList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        try {
            JSONObject object = new JSONObject(Array_JSON.student_json) ;
            JSONArray  studentArray = object.getJSONObject("student").getJSONArray("data") ;

            for(int i=0;i<studentArray.length();i++){
                JSONObject studentData = studentArray.getJSONObject(i);

                Student aStudentModel = new Student() ;
                aStudentModel.setDepartment(studentData.getString("cattitle"));
                aStudentModel.setStudentId(studentData.getString("studentId"));

                if(myList.containsKey(aStudentModel.getDepartment()) && myList.get(aStudentModel.getDepartment() ) != null ){
                    myList.get(aStudentModel.getDepartment()).add(aStudentModel) ;

                } else{
                    ArrayList<Student> studentList = new ArrayList<>() ;
                    studentList.add(aStudentModel) ;
                    myList.put(aStudentModel.getDepartment(), studentList ) ;

                    keyofMyList.add(aStudentModel.getDepartment());
                }



            }


            Log.e("hashmap", "hashList: "+myList.toString() ) ;
            Log.e("hashmap", "hashList: "+myList.keySet().toString() ) ;
            Log.e("hashmap", "keyofMyList: "+keyofMyList.toString() ) ;

            for(int i=0; i<keyofMyList.size(); i++){
                onBindViewHolder(i) ;

            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("hashmap", "error: "+e.toString() ) ;
        }


    }
    public void onBindViewHolder(int position){
//        keyofMyList.get(position);
        myList.get(keyofMyList.get(position)) ;
    }




}
*/
