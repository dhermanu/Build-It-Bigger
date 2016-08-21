package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.dean.jokesdisplaylib.JokeDisplay;
import com.example.dhermanu.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by dhermanu on 8/17/16.
 */

public class AsyncJokeReceiver{
    private onJokeListener jokeListener;

    public AsyncJokeReceiver(onJokeListener jokeListener){
        this.jokeListener = jokeListener;
    }

    public void receiveJoke(){
        new EndpointsAsyncTask().execute();
    }

    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;
        //private Context context;

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://build-bigger.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            //context = params[0];


            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result == null)
                jokeListener.onJokeReceiver("An error occurred!");
            else
                jokeListener.onJokeReceiver(result);
        }
    }
}

