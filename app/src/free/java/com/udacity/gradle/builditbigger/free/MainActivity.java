package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JavaJokes;
import com.example.dean.jokesdisplaylib.JokeDisplay;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.AsyncJokeReceiver;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.onJokeListener;


public class MainActivity extends ActionBarActivity implements onJokeListener {

    InterstitialAd mInterstitialAd;
    private boolean jokeStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                new AsyncJokeReceiver(MainActivity.this).receiveJoke();
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
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

        new AsyncJokeReceiver(this).receiveJoke();
    }


    @Override
    public void onJokeReceiver(String jokes) {
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }

        else{
            Intent intent = new Intent(this, JokeDisplay.class);
            intent.putExtra(JokeDisplay.INTENT_JOKE, jokes);
            startActivity(intent);
        }

    }
}
