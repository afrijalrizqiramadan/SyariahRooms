package com.syariahrooms;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.syariahrooms.RoomListActivity.evenNumbers;

public class HotelDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MARKER_SOURCE = "markers-source";
    private static final String MARKER_STYLE_LAYER = "markers-style-layer";
    private static final String MARKER_IMAGE = "custom-marker";
    TextView namahotel, namahotelbawah, alamathotelbawah, tvprice, namalokasi, keterangankhusus, titletoolbar, text_view_toolbar_title;
    Button pilihkamar;
    RecyclerView recyclerView;
    String url = "https://betabackoffice.syariahrooms.com/api/ct_prop?";
    ArrayList<HashMap<String, String>> list_data;
    ArrayList<HashMap<String, String>> list_image;
    ImageView imagehotel, imgpending;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView lvhape, imagerecycler;
    LinearLayout pagehoteldetail;
    String snamahotel, skamar, scheckin, scheckout, stamu, stotalmalam, skeyhotel, hargamulai, gambarhotel, alamathotel;
    private ShimmerFrameLayout concentShimmer;
    private ImageView iv1, iv2, iv3, iv4, iv5;
    String alamaturl;
    private ImageView[] IMGS = {iv1, iv3, iv3, iv4, iv5};
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hotel_detail);
        concentShimmer = findViewById(R.id.hoteldetailpending);
        namahotelbawah = findViewById(R.id.text_view_hotel_name_access);
        alamathotelbawah = findViewById(R.id.text_view_hotel_address);
        tvprice = findViewById(R.id.text_view_price);
        tvprice = findViewById(R.id.text_view_price);
        pilihkamar = findViewById(R.id.button_room_detail);
        pagehoteldetail = findViewById(R.id.pagehoteldetail);
        namalokasi = findViewById(R.id.text_view_hotel_city);
        keterangankhusus = findViewById(R.id.keterangankhusus);
        text_view_toolbar_title = findViewById(R.id.text_view_toolbar_title);
        evenNumbers.clear();

        namahotel = findViewById(R.id.text_view_hotel_name);
        imgpending = findViewById(R.id.img_pending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhotellist);
        setSupportActionBar(toolbar);
        titletoolbar = findViewById(R.id.text_view_toolbar_title);
        titletoolbar.setText("Description");
        iv1 = findViewById(R.id.img_hotel);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            scheckin = bundle.getString("checkin");
            scheckout = bundle.getString("checkout");
            stamu = bundle.getString("tamu");
            skeyhotel = bundle.getString("keyhotel");
            stotalmalam = bundle.getString("totalmalam");
            skamar = bundle.getString("kamar");
            hargamulai = bundle.getString("hargamulai");

        }
        String clientkey = "client_key=qJRCK04wWxcNNNNN";
        String date = "&date=" + scheckin;
        String night = "&night=" + stotalmalam;
        String key_prop = "&key_prop=" + skeyhotel;
        alamaturl = url + clientkey + date + night + key_prop;

        lvhape = (RecyclerView) findViewById(R.id.rv_fasilitas);
        imagerecycler = (RecyclerView) findViewById(R.id.recycleimagehoteldetail);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager lln = new LinearLayoutManager(this);
        lln.setOrientation(LinearLayoutManager.HORIZONTAL);
        lvhape.setLayoutManager(llm);
        imagerecycler.setLayoutManager(lln);

        requestQueue = Volley.newRequestQueue(HotelDetailActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();
        list_image = new ArrayList<HashMap<String, String>>();

        pilihkamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RoomListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("namahotel", snamahotel);
                bundle.putString("checkin", scheckin);
                bundle.putString("checkout", scheckout);
                bundle.putString("tamu", stamu);
                bundle.putString("kamar", skamar);
                bundle.putString("alamat", alamaturl);
                bundle.putString("stotalmalam", stotalmalam);
                bundle.putString("skeyhotel", skeyhotel);
                bundle.putString("alamathotel", alamathotel);
                bundle.putString("gambarhotel", gambarhotel);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        stringRequest = new StringRequest(Request.Method.GET, alamaturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonArray = jsonObject.getJSONObject("prop");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        namahotelbawah.setText(jsonArray.getString("namaHomestay"));
                        snamahotel = jsonArray.getString("namaHomestay");
                        text_view_toolbar_title.setText(jsonArray.getString("namaHomestay"));
                        namahotel.setText(jsonArray.getString("namaHomestay"));
                        String tempat = jsonArray.getString("namaKecamatan") + ", " + jsonArray.getString("namaKabupaten");
                        namalokasi.setText(tempat);
                        alamathotelbawah.setText(jsonArray.getString("lokasi"));
                        keterangankhusus.setText(jsonArray.getString("catatanKhusus"));
                        alamathotel = jsonArray.getString("lokasi");
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        tvprice.setText(hargamulai);
                    }

                    JSONArray jsonimage = jsonObject.getJSONArray("image");
                    for (int a = 0; a < jsonimage.length(); a++) {
                        JSONObject json = jsonimage.getJSONObject(a);
                        if (a == 0) {
                            Glide.with(getApplicationContext())
                                    .load(json.getString("urlImage"))
                                    .into(iv1);
                            gambarhotel = json.getString("urlImage");
                        }
                        HashMap<String, String> image = new HashMap<String, String>();
                        image.put("gambar", json.getString("urlImage"));
                        Log.d("image ", json.getString("urlImage"));

                        imgpending.setVisibility(View.GONE);
                        iv1.setVisibility(View.VISIBLE);
                        list_image.add(image);
                        ImageViewAdapter adapter = new ImageViewAdapter(HotelDetailActivity.this, list_image);
                        imagerecycler.setAdapter(adapter);

                    }

                    JSONArray jsonfas = jsonObject.getJSONArray("fas");
                    for (int b = 0; b < jsonfas.length(); b++) {
                        JSONObject jsonf = jsonfas.getJSONObject(b);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("nama", jsonf.getString("namaFasilitasHS"));
                        map.put("gambar", jsonf.getString("urlIcon"));
                        concentShimmer.setVisibility(View.GONE);
                        pagehoteldetail.setVisibility(View.VISIBLE);

                        list_data.add(map);
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(HotelDetailActivity.this, list_data);
                        lvhape.setAdapter(adapter);
                    }

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HotelDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    Double latitude;
    Double longitude;
    String titlemap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        float zoomLevel = 16.0f; //This goes up to 21

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(7.912365, 112.584782);
        mMap.addMarker(new MarkerOptions().position(sydney).title(titlemap));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

    }
}
