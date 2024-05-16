package com.syariahrooms.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.syariahrooms.EmailFragment;
import com.syariahrooms.R;
import com.syariahrooms.TelpFragment;

public class LupaPasswordActivity extends AppCompatActivity {
    TextView  bresetchange;
    Button breset;
    Boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapassword);
        bresetchange = (TextView) findViewById(R.id.LoginContactChangeTV);
        bresetchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state) {
                    bresetchange.setText("Using Phone Number ?");
                    loadFragment(new EmailFragment());
                    state = false;
                } else {
                    bresetchange.setText("Using Email ?");
                    loadFragment(new TelpFragment());
                    state = true;
                }
            }
        });
    }
    private void loadFragment(Fragment fragment) {

// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();

// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();


// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.ResetFragmentContainerLL, fragment);
        fragmentTransaction.commit(); // save the changes

    }
}
