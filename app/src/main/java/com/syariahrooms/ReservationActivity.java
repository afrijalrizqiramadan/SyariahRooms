package com.syariahrooms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ReservationActivity extends AppCompatActivity {
    TextView title;
    CheckBox cb;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView lvhape;
    FrameLayout frameLayout;
    String snamahotel, skamar, scheckin, scheckout, stamu, sgambarhotel, stotalmalam, skeyhotel, slokasihotel, alamathotel, string;
    ImageView imagereservation;
    TextView namahotelcheckout, tanggalcheckinc, jumlahkamarc, jumlahmalamc, lokasihotelc, labelhargaakhir, emailuser, nameuser;
    ArrayList<HashMap<String, String>> list_data;
    String alamaturl;
    EditText ettamu, etkodepromo, inputnomertelepon;
    Button bdialogpromo;
    CheckBox pesanuntuk;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    private ImageView presedback;
    Button konfirmasipesanan;
    long hargaakhir = 0;
    long jumlahkamar = 0;
    ShimmerFrameLayout confirmation;
    SharedPreferences prf;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        title = findViewById(R.id.text_view_toolbar_title);
        cb = findViewById(R.id.cbpesanuntuk);
        ettamu = findViewById(R.id.ettamu);
        prf = Objects.requireNonNull(getApplicationContext()).getSharedPreferences("user_details", MODE_PRIVATE);
        emailuser = findViewById(R.id.emailuser);
        nameuser = findViewById(R.id.nameuser);
        inputnomertelepon = findViewById(R.id.inputnomertelepon);
        pesanuntuk = findViewById(R.id.cbpesanuntuk);
        nameuser.setText(prf.getString("username", null));
        emailuser.setText(prf.getString("email", null));
        imagereservation = findViewById(R.id.imagereservation);
        konfirmasipesanan = findViewById(R.id.konfirmasipesanan);
        namahotelcheckout = findViewById(R.id.namahotelcheckout);
        tanggalcheckinc = findViewById(R.id.tanggalcheckinc);
        presedback = findViewById(R.id.backpresedhotellist);

        jumlahmalamc = findViewById(R.id.jumlahmalamc);
        jumlahkamarc = findViewById(R.id.jumlahkamarc);
        lokasihotelc = findViewById(R.id.lokasihotelc);
        labelhargaakhir = findViewById(R.id.labelhargaakhir);
        bdialogpromo = findViewById(R.id.bdialogpromo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhotellist);
        setSupportActionBar(toolbar);
        if (title != null && toolbar != null) {
            setSupportActionBar(toolbar);
            presedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        }
        konfirmasipesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClickWhatsApp();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        bdialogpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm();
            }
        });
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            snamahotel = bundle.getString("namahotel");
            scheckin = bundle.getString("checkin");
            scheckout = bundle.getString("checkout");
            slokasihotel = bundle.getString("slokasihotel");
            stamu = bundle.getString("tamu");
            stotalmalam = bundle.getString("stotalmalam");
            skamar = bundle.getString("kamar");
            sgambarhotel = bundle.getString("gambarhotel");
            string = bundle.getString("string");
            alamaturl = bundle.getString("alamat");
            skeyhotel = bundle.getString("skeyhotel");
            alamathotel = bundle.getString("alamathotel");

        }
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb.isChecked()) {
                    ettamu.setVisibility(View.VISIBLE);
                }
                // do something
                else {
                    ettamu.setVisibility(View.GONE);

                }
            }
        });
        Glide.with(getApplicationContext())
                .load(sgambarhotel)
                .into(imagereservation);

        namahotelcheckout.setText(snamahotel);
        jumlahmalamc.setText(stotalmalam + " Malam");
        tanggalcheckinc.setText(scheckin);
        lokasihotelc.setText(alamathotel);
        title.setText("Lengkapi Data");
        lvhape = (RecyclerView) findViewById(R.id.listcheckout);

        confirmation = findViewById(R.id.boboboxReservationConfirmationShimmer);
//        frameLayout=findViewById(R.id.frameshimmer);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvhape.setLayoutManager(llm);
        String url = "https://betabackoffice.syariahrooms.com/index.php/api/ct_checkout?";
        alamaturl = "";
        String clientkey = "client_key=qJRCK04wWxcoINai";
        String date = "&date=" + scheckin;
        String night = "&night=" + stotalmalam;
        String key_prop = "&key_prop=" + skeyhotel;
        String booking = "&booking=" + string;
        alamaturl = url + clientkey + date + night + key_prop + booking;
        requestQueue = Volley.newRequestQueue(ReservationActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        Log.d("responseurl ", alamaturl);

        stringRequest = new StringRequest(Request.Method.GET, alamaturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("detail");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("nama", json.getString("typeName"));
                        map.put("night", json.getString("night"));
                        map.put("jumlahkamar", json.getString("kmr"));
                        map.put("hargaasli", formatter.format(json.getLong("price")));
                        map.put("hargatotal", formatter.format(json.getLong("total")));
                        hargaakhir += json.getLong("total");
                        jumlahkamar += json.getLong("kmr");

                        list_data.add(map);
//                        framesimmer.setVisibility(View.GONE);
//                        framerecyce.setVisibility(View.VISIBLE);
                        RVCheckoutAdapter adapter = new RVCheckoutAdapter(ReservationActivity.this, list_data);
                        lvhape.setAdapter(adapter);

                    }
                    labelhargaakhir.setText("Rp. " + formatter.format(hargaakhir));
                    jumlahkamarc.setText(String.valueOf(jumlahkamar));
                } catch (JSONException e) {

                    Toast.makeText(ReservationActivity.this, "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReservationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(ReservationActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.promo_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
//        dialog.setIcon(R.mipmap.ic_launcher);
//        dialog.setTitle("Masukkan Kode Promo");

        etkodepromo = (EditText) dialogView.findViewById(R.id.etkodepromo);

        kosong();

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String kodepromo = etkodepromo.getText().toString();
                Toast.makeText(ReservationActivity.this, etkodepromo.getText(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = dialog.create();

        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.mainTheme));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.mainTheme));
    }

    // untuk mengosongi edittext
    private void kosong() {
        etkodepromo.setText(null);
    }

    public void onClickWhatsApp() throws UnsupportedEncodingException {
        String nama = prf.getString("username", null);
        if (inputnomertelepon.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Isi Telepon Terlebih Dahulu", Toast.LENGTH_LONG).show();
        } else {
            if (pesanuntuk.isChecked()) {
                if (ettamu.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Isi Nama Tamu", Toast.LENGTH_LONG).show();
                } else {
                    nama = String.valueOf(ettamu.getText());
                    String contact = "628113031777"; // use country code with your phone number
                    String text = "Nama%20%3A%20" + nama + "%0ANo%20Telepon%20%3A%20" + inputnomertelepon.getText() + "%0ANama%20Homestay%20%3A%20" + snamahotel + "%0AJumlah%20Kamar%20yang%20dipesan%20%3A%20" + jumlahkamar + "%0AMalam%20%3A%20" + stotalmalam + "%0ATotal%20%3A%20" + hargaakhir;
                    String url = "https://api.whatsapp.com/send?phone=" + contact + "&text=" + text;
                    try {
                        PackageManager pm = getApplication().getPackageManager();
                        pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    } catch (PackageManager.NameNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    }
                }

            } else {
                nama = prf.getString("username", null);
                String contact = "628113031777"; // use country code with your phone number
                String text = "Nama%20%3A%20" + nama + "%0ANo%20Telepon%20%3A%20" + inputnomertelepon.getText() + "%0ANama%20Homestay%20%3A%20" + snamahotel + "%0AJumlah%20Kamar%20yang%20dipesan%20%3A%20" + jumlahkamar + "%0AMalam%20%3A%20" + stotalmalam + "%0ATotal%20%3A%20" + hargaakhir;
                String url = "https://api.whatsapp.com/send?phone=" + contact + "&text=" + text;
                try {
                    PackageManager pm = getApplication().getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }
        }

    }

}
