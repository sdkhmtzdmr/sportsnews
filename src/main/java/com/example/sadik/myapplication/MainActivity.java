package com.example.sadik.myapplication;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="extracreator";
    public static final String EXTRA_CONTENT="ekstracontent";
    public static final String EXTRA_HABERURL="ekstrahaberurl";
    public static final String EXTRA_PUBLİSHED="ekstrahaberkaynak";


   private RecyclerView mRecyclerView;
   private ArrayList<String> mSwipeList;
    ViewPager viewPager;


   private ItemAdapter mItemAdapter;



   private ArrayList<Item> mItem;
   private RequestQueue mRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager);



        mRecyclerView=findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mItem=new ArrayList<>();


        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();





    }
    private void parseJSON(){

        String url ="https://newsapi.org/v2/everything?q=spor&apiKey=b2be9a4689b248dbad1a8ff34341a5be";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("articles");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject article=jsonArray.getJSONObject(i);
                        String mTitle=article.getString("title");
                        String imageUrl=article.getString("urlToImage");
                        String content=article.getString("description");
                        String published=article.getString("publishedAt");
                        String haberurl=article.getString("url");






                        mItem.add(new Item(imageUrl,mTitle,content,haberurl,published));



                    }
                    mItemAdapter=new ItemAdapter(MainActivity.this  ,mItem);
                    mRecyclerView.setAdapter(mItemAdapter);
                    mItemAdapter.setOnItemClickListener(MainActivity.this);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent=new Intent(new Intent(this,DetailActivity.class));
        Item clickedItem=mItem.get(position);
        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getmCreator());
        detailIntent.putExtra(EXTRA_CONTENT,clickedItem.getmContent());
        detailIntent.putExtra(EXTRA_HABERURL,clickedItem.getmHaberUrl());
        detailIntent.putExtra(EXTRA_PUBLİSHED,clickedItem.getMpublished());
        startActivity(detailIntent);


    }
}
