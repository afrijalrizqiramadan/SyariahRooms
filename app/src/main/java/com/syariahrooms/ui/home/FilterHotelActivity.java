package com.syariahrooms.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.syariahrooms.EmailFragment;
import com.syariahrooms.login.Login;
import com.syariahrooms.R;
import com.syariahrooms.TelpFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FilterHotelActivity extends AppCompatActivity {
    Button bregister;
    TextView btologin, bregisterchange, breset, bsimpan, btitle;
    Boolean state = true;
    CrystalRangeSeekbar rangeSeekbar;
    String minharga, maxharga;
    EditText etminharga, etmaxharga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_filter);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        breset = findViewById(R.id.tv_reset_btn);
//        rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar5);
        bsimpan = findViewById(R.id.tv_simpan_btn);
        btitle = findViewById(R.id.text_view_toolbar_title);
//        etmaxharga = findViewById(R.id.edit_text_max_price);
//        etminharga = findViewById(R.id.edit_text_min_price);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        btitle.setText("Filter");
//        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
//            @Override
//            public void valueChanged(Number minValue, Number maxValue) {
//                DecimalFormat formatter = new DecimalFormat("#,###,###");
//                etminharga.setText(String.valueOf(formatter.format(minValue)));
//                etmaxharga.setText(String.valueOf(formatter.format(maxValue)));
//                minharga=String.valueOf(minValue);
//                maxharga=String.valueOf(maxValue);
//            }
//        });

        breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        bsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
