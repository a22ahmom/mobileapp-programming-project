package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView textView = findViewById(R.id.textview);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String info = extras.getString("info");
            textView.setText(info);
        }

        Button returnToMainPageButton = findViewById(R.id.returnToMainPage);
        returnToMainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}
