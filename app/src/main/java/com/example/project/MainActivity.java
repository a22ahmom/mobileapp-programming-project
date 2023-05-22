package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

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

        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=a22ahmom");

        String s = readFile("wildanimals.json");
        Log.d("MainActivity","The following text was found in textfile:\n\n"+s);

    }

    @SuppressLint("NotifyDataSetChanged")
    public void onPostExecute(String json) {
        Log.d("Mainactivity ==>", json);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<WildAnimals>>(){}.getType();
        ArrayList<WildAnimals> wildAnimalsArrayList = gson.fromJson(json, type);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(wildAnimalsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for(int i = 0; i < wildAnimalsArrayList.size(); i++) {
            Log.d("Mainactivity ==>", "Hittade ett berg " + wildAnimalsArrayList.get(i).getName());
        }
    }
}

