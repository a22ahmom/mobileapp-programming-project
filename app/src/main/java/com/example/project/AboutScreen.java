package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutScreen extends AppCompatActivity {
    private WebView webView;

    public void showInternalWebPage(){
        webView.loadUrl("file:///android_asset/about.html");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView textView = findViewById(R.id.textview);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_internal_web) {
            Log.d("==>","Will display internal web page");
            showInternalWebPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}