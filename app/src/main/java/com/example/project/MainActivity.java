package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22ahmom";
    private final String JSON_FILE = "wildanimals.json";
    private ArrayList<WildAnimals> wildAnimalsArrayList;
    RecyclerView recyclerView;

    private String readFile(String fileName) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return new Scanner(getApplicationContext().getAssets().open(fileName), Charset.forName("UTF-8").name()).useDelimiter("\\A").next();
        } catch (IOException e) {
            Log.d("TAG", "Could not read file: " + fileName);
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wildAnimalsArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        Button aboutScreenButton = findViewById(R.id.aboutScreen);
        TextView textView = (TextView) findViewById(R.id.textview);
        aboutScreenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AboutScreen.class);
                startActivity(intent);
            }
        });

        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=a22ahmom");

        String s = readFile("wildanimals.json");
        Log.d("MainActivity","The following text was found in textfile:\n\n"+s);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        //Log.d("Mainactivity ==>", json);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<WildAnimals>>(){}.getType();
        ArrayList<WildAnimals> wildAnimalsArrayList = gson.fromJson(json, type);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(wildAnimalsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for(int i = 0; i < wildAnimalsArrayList.size(); i++) {
            Log.d("Mainactivity ==>", "Hittade ett vilt djur " + wildAnimalsArrayList.get(i).getName());
        }

    }
}

