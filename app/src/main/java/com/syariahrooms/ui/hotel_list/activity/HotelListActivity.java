package com.syariahrooms.ui.hotel_list.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.syariahrooms.R;
import com.syariahrooms.login.Login;
import com.syariahrooms.ui.home.FilterHotelActivity;
import com.syariahrooms.ui.home.register;
import com.syariahrooms.ui.hotel_list.fragment.HotelListFragment;

public class HotelListActivity extends AppCompatActivity {

    boolean isUp;
    View myViewsort;
    private RecyclerView lvhape;
    private RelativeLayout bfilter, bsort, backgroundsort;
    private TextView titlelokasi;
    private ImageView presedback;
    String lokasi="";
    String snamahotel, skamar, scheckin, scheckout, stamu, jumlahmalam;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        bfilter = findViewById(R.id.button_filter);
        bsort = findViewById(R.id.button_sort);
        titlelokasi = findViewById(R.id.text_view_toolbar_title);
        myViewsort = findViewById(R.id.my_viewsort);
        presedback = findViewById(R.id.backpresedhotellist);
        myViewsort = findViewById(R.id.my_viewsort);
        backgroundsort=findViewById(R.id.backgorundsort);
        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            lokasi=bundle.getString("lokasi");
            snamahotel=bundle.getString("namahotel");
            scheckin=bundle.getString("checkin");
            scheckout=bundle.getString("checkout");
            stamu=bundle.getString("tamu");
            jumlahmalam = bundle.getString("totalmalam");
            skamar=bundle.getString("kamar");
        }


        isUp = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhotellist);
        setSupportActionBar(toolbar);
        titlelokasi.setText(lokasi);
        if (titlelokasi != null && toolbar != null) {
            setSupportActionBar(toolbar);
            presedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        }
        loadFragment(new HotelListFragment());

        bfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FilterHotelActivity.class);
                startActivity(intent);
            }
        });
        backgroundsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDown(myViewsort);
                backgroundsort.setVisibility(View.INVISIBLE);
                isUp = false;

            }
        });
        bsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUp) {
                    slideDown(myViewsort);
                    backgroundsort.setVisibility(View.INVISIBLE);
                } else {
                    slideUp(myViewsort);
                    backgroundsort.setVisibility(View.VISIBLE);

                }
                isUp = !isUp;
            }
        });
    }


    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.v_progress, fragment)
                .commit();
    }

    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }
    public String getDatalokasi(){
        return lokasi;
    }
    public String getCheckin(){
        return scheckin;
    }
    public String getChekout(){
        return scheckout;
    }

    public String getJumlahmalam() {
        return jumlahmalam;
    }
    public String getKamar(){
        return skamar;
    }
    public String getTamu(){
        return stamu;
    }

}
