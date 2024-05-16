package com.syariahrooms.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jaeger.library.StatusBarUtil;
import com.syariahrooms.R;
import com.syariahrooms.login.Login;

public class MainActivity extends AppCompatActivity {
    String lokasi = "";
    SharedPreferences pref;

    BottomNavigationView bottomNavigationView;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.boboboxTabMenuHome:
                    item.setCheckable(true);
                    loadFragment(new MainHomeFragment());
                    return true;
//                case R.id.boboboxTabMenuStay:
//                    item.setCheckable(true);
//                    loadFragment(new MainStaysFragment());
//                    return true;
                case R.id.boboboxTabMenuMyAccount:
                    item.setCheckable(true);
                    pref = getSharedPreferences("user_details", MODE_PRIVATE);
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    if (!pref.contains("username")) {
                        startActivity(intent);
                        finish();

                    } else {
                        loadFragment(new MainMyAccountFragment());
                    }
                    return true;
                case R.id.boboboxTabMenuAbout:
                    item.setCheckable(true);
                    loadFragment(new MainAboutFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setActionListener();

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    void setActionListener() {
        Fragment fragment = new MainHomeFragment();
        loadFragment(fragment);

    }

    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    public String getlokasi(){
        return lokasi;
    }
}
