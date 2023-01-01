package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(){
            public void run(){
                    try {
                        sleep(Const.DELAY_SPLASH_SCREEN);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        startActivity(new Intent(MainActivity.this, Home.class));
                        finish();
                    }
            }
        };
        thread.start();
    }
}