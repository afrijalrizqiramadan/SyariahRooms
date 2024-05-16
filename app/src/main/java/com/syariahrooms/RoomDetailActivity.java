package com.syariahrooms;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomDetailActivity extends AppCompatActivity {
    TextView namahotel, tvprice, namaruangan;
    Button pesankamar;
    ImageView imagehotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        tvprice = findViewById(R.id.hargakamar);
        pesankamar = findViewById(R.id.button_choose_room);
        namaruangan = findViewById(R.id.text_view_room_plan_name);
        namahotel = findViewById(R.id.text_view_hotel_name);
        imagehotel = findViewById(R.id.img_kamar);

    }
}
