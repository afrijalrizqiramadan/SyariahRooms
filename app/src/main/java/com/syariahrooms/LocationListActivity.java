package com.syariahrooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.syariahrooms.ui.home.FilterHotelActivity;
import com.syariahrooms.ui.home.MainActivity;
import com.syariahrooms.ui.hotel_list.fragment.HotelListFragment;

public class LocationListActivity extends AppCompatActivity {

    boolean isUp;
    View myViewsort;
    private RecyclerView lvhape;
    private RelativeLayout bfilter, bsort;
    private LinearLayout tbatu, tmalang,tsemarang;
    private ImageView gambararticle, backpresed;
    private String var="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);
        bfilter = findViewById(R.id.button_filter);
        bsort = findViewById(R.id.button_sort);
        myViewsort = findViewById(R.id.my_viewsort);
        backpresed = (ImageView) findViewById(R.id.backpreseds);
        tbatu=findViewById(R.id.tbatu);
        tmalang=findViewById(R.id.tmalang);
        tsemarang=findViewById(R.id.tsemarang);

        tbatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("lokasi", "KOTA BATU, JAWA TIMUR");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        tmalang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("lokasi", "KOTA MALANG, JAWA TIMUR");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        tsemarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("lokasi", "KOTA SEMARANG, JAWA TENGAH");
                setResult(RESULT_OK, intent);
                finish();
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            backpresed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        }

    }


    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.v_progress, fragment)
                .commit();
    }

}
