package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dean.jokesdisplaylib.JokeDisplay;
import com.udacity.gradle.builditbigger.AsyncJokeReceiver;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.onJokeListener;


public class MainActivity extends ActionBarActivity implements onJokeListener {

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
//        Intent intent = new Intent(this, JokeDisplay.class);
//        String Joke = jokes.getJoke();
//        intent.putExtra(JokeDisplay.INTENT_JOKE, Joke);
//        startActivity(intent);
//        new EndpointsAsyncTask().execute((this));
//        Toast.makeText(this, jokes.getJoke(), Toast.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.VISIBLE);
        new AsyncJokeReceiver(this).receiveJoke();
    }


    @Override
    public void onJokeReceiver(String jokes) {
        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra(JokeDisplay.INTENT_JOKE, jokes);
        startActivity(intent);
    }
}
