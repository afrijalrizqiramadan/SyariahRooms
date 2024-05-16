package com.syariahrooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.syariahrooms.login.Login;
import com.syariahrooms.ui.home.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private int waktu_loading = 3000;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //4000=4 detik

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                        //setelah loading maka akan langsung berpindah ke home activity
                        Intent home = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();


                }
            },waktu_loading);
        }
    }