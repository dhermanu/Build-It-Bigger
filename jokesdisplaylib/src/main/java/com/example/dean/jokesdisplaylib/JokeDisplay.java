package com.example.dean.jokesdisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplay extends AppCompatActivity {

    public static String INTENT_JOKE = "JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Intent intent = getIntent();
        String jokeString = intent.getStringExtra(INTENT_JOKE);
        TextView JokeText = (TextView) findViewById(R.id.JokeText);

        if(jokeString != null)
           JokeText.setText(jokeString);
    }
}
