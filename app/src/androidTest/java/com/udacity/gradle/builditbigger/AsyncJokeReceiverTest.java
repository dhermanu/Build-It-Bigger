package com.udacity.gradle.builditbigger;

import android.test.UiThreadTest;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by dean on 8/19/16.
 */
public class AsyncJokeReceiverTest extends TestCase implements onJokeListener{
    AsyncJokeReceiver asyncJokeReceiver;
    CountDownLatch signal;
    String jokes;


    public void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        asyncJokeReceiver = new AsyncJokeReceiver(this);

    }

    @UiThreadTest
    public void testReceiveJoke() throws InterruptedException {
        asyncJokeReceiver.receiveJoke();
        signal.await(30, TimeUnit.SECONDS);

        assertTrue("Joke wasn't received", !jokes.isEmpty());

    }

    @Override
    public void onJokeReceiver(String jokes) {
        signal.countDown();
        this.jokes = jokes;

    }
}