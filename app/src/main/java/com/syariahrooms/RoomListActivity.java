package com.syariahrooms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.syariahrooms.R;
import com.syariahrooms.login.Login;
import com.syariahrooms.ui.home.MainMyAccountFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RoomListActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> list_data;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView lvhape;
    String alamaturl;
    private ImageView presedback;
    public static HashMap<String, Integer> evenNumbers = new HashMap<>();

    Button btncheckout;
    private TextView titlehotel, tvhari, tvtamu, tvcheckin, tvcheckout, tvkamar;
    FrameLayout frameLayout;
    String snamahotel, skamar, scheckin, scheckout, stamu, sgambarhotel, stotalmalam, slokasihotel, skeyhotel, alamathotel, jumlahkamar;
    private List<arraycheckout> arraycheckouts;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        tvcheckin = findViewById(R.id.text_view_check_in);
        tvcheckout = findViewById(R.id.text_view_check_out);
        tvtamu = findViewById(R.id.text_view_guest);
        btncheckout = findViewById(R.id.btncheckout);
        presedback = findViewById(R.id.backpresedhotellist);

//        tvkamar=findViewById(R.id.text_view_room);
        lvhape = (RecyclerView) findViewById(R.id.recycler_view_room);
        titlehotel = (TextView) findViewById(R.id.text_view_toolbar_titlehotel);
        frameLayout = findViewById(R.id.frameshimmer);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvhape.setLayoutManager(llm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (titlehotel != null && toolbar != null) {
            setSupportActionBar(toolbar);
            presedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        }
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            snamahotel = bundle.getString("namahotel");
            scheckin = bundle.getString("checkin");
            scheckout = bundle.getString("checkout");
            stamu = bundle.getString("tamu");
            skamar = bundle.getString("kamar");
            skeyhotel = bundle.getString("skeyhotel");
            slokasihotel = bundle.getString("slokasihotel");
            stotalmalam = bundle.getString("stotalmalam");
            alamathotel = bundle.getString("alamathotel");
            sgambarhotel = bundle.getString("gambarhotel");
            alamaturl = bundle.getString("alamat");
        }

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref = getSharedPreferences("user_details", MODE_PRIVATE);
                Intent intsent = new Intent(getApplicationContext(), Login.class);
                if (!pref.contains("username")) {
                    startActivity(intsent);
                    finish();
                } else {


                    Iterator<Integer> iterate3 = evenNumbers.values().iterator();
                    int var = 0;
                    while (iterate3.hasNext()) {
                        var += iterate3.next();
                    }
                    Log.d("variabel", String.valueOf(var));
                    String string = "[";
                    int count = 0;
                    for (Map.Entry<String, Integer> entry : evenNumbers.entrySet()) {
                        if (count != 0) {
                            string += ",";
                        }
                        if (entry.getValue() != 0) {
                            string += "[";
                            string += entry.getKey();
                            string += ",";
                            string += String.valueOf(entry.getValue());
                            string += "]";
                            count++;
                        }
                    }
                    string += "]";

                    Log.d("variabel", String.valueOf(string));
                    if (var == 0) {
                        Toast.makeText(getApplicationContext(), "Pilih Kamar terlebih Dahulu", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("namahotel", snamahotel);
                        bundle.putString("checkin", scheckin);
                        bundle.putString("checkout", scheckout);
                        bundle.putString("slokasihotel", slokasihotel);
                        bundle.putString("tamu", stamu);
                        bundle.putString("skeyhotel", skeyhotel);
                        bundle.putString("alamathotel", alamathotel);
                        bundle.putString("stotalmalam", stotalmalam);
                        bundle.putString("kamar", skamar);
                        bundle.putString("string", string);
                        bundle.putString("alamat", alamaturl);
                        bundle.putString("gambarhotel", sgambarhotel);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
            }
        });
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        Date date2 = null;
        try {
            date = format.parse(scheckin);
            date2 = format.parse(scheckout);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.ENGLISH);
        String sdateawal = sdf.format(date);
        String sdateakhir = sdf.format(date2);

        titlehotel.setText(snamahotel);
        tvcheckin.setText(sdateawal);
        tvcheckout.setText(sdateakhir);
        tvtamu.setText(stamu);
//        tvkamar.setText(skamar);

        requestQueue = Volley.newRequestQueue(RoomListActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        List<Item> ItemList = new ArrayList<>();

        stringRequest = new StringRequest(Request.Method.GET, alamaturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("room_detail");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        DecimalFormat formatter = new DecimalFormat("#,###,###");

                        evenNumbers.put(json.getString("idTypeRoom"), 0);
                        JSONArray jsonfasilitasArray = json.getJSONArray("fas");
                        List<SubItem> subItemList = new ArrayList<>();
                        for (int b = 0; b < jsonfasilitasArray.length(); b++) {
                            JSONObject jsonf = jsonfasilitasArray.getJSONObject(b);
                            Log.d("Tes", jsonf.getString("namaFasilitas"));

                            SubItem subItem = new SubItem("" + jsonf.getString("namaFasilitas"), "" + jsonf.getString("urlIcon"));
                            subItemList.add(subItem);
                        }
                        Item item = new Item(json.getString("typeName"),json.getString("idTypeRoom"), json.getString("image"), json.getString("kapasitas"), json.getString("min"), formatter.format(json.getLong("ttl_promo")), subItemList);
                        ItemList.add(item);
                        frameLayout.setVisibility(View.GONE);
                        lvhape.setVisibility(View.VISIBLE);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        RVRoomAdapter itemAdapter = new RVRoomAdapter(getApplicationContext(), ItemList);
                        lvhape.setAdapter(itemAdapter);
                        lvhape.setLayoutManager(layoutManager);
                    }


                } catch (JSONException e) {
                    Toast.makeText(RoomListActivity.this, "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RoomListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }
}