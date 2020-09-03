package com.example.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FoodListVolleyActivity extends AppCompatActivity {

    private static final String URL_PRODUCTS = "http://192.168.1.2/android/Food_Shop/all_food.php";

//    private static final String URL_PRODUCTS = "http://10.0.2.2/android/Food_Shop/all_food.php";
    private RecyclerView recyclerView ;
    List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_food_list_volley);

       /* recyclerView = findViewById(R.id.foodRecyclerView) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

        productList = new ArrayList<>();

        loadProducts();
    }



    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("serverResponse", "response: "+response );
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);


                            //traversing through all the object
//                            for (int i = 0; i < array.length(); i++) {
//
//                                //getting product object from json array
//                                JSONObject product = array.getJSONObject(i);
//
//                                //adding the product to product list
//                                productList.add(new Product(
//                                        product.getInt("id"),
//                                        product.getString("food_name"),
//                                        product.getDouble("price"),
//                                        product.getString("resturant_name"),
//                                        product.getString("rating"),
//                                        product.getString("image")
//                                ));
//                            }

                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject aProductJson = array.getJSONObject(i);
                                Product aProductModel = new Product();

                                Field[] fields =  aProductModel.getAllFields() ;
                                for(int j=0; j<fields.length; j++){
                                    String fieldName = fields[j].getName() ;
                                    String fieldValueInJson =aProductJson.has(fieldName)? aProductJson.getString(fieldName) : "" ;
                                    fields[j].set(aProductModel, fieldValueInJson);

                                }

                                productList.add(aProductModel) ;



//                                //adding the product to product list
//                                productList.add(new Product(
//                                        product.getInt("id"),
//                                        product.getString("food_name"),
//                                        product.getDouble("price"),
//                                        product.getString("resturant_name"),
//                                        product.getString("rating"),
//                                        product.getString("image")
//                                ));
                            }
                            Log.d("TAG", "prductList: "+productList.size()) ;
/*
                            ProductsAdapter adapter = new ProductsAdapter(getApplicationContext(), productList);
                            recyclerView.setAdapter(adapter);
                            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);*/

//                            Toast.makeText(MainActivity.this, "successfull", Toast.LENGTH_LONG).show();
                        } catch (JSONException | IllegalAccessException e) {
                            e.printStackTrace();
                            Log.e("serverResponseError", "error: "+e.toString() );

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
