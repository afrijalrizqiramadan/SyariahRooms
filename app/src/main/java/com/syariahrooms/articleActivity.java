package com.syariahrooms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class articleActivity extends AppCompatActivity {
    String url = "https://betabackoffice.syariahrooms.com/api/view_artc?client_key=qJRCK04wWxcNNNNN&ttl=";
    ArrayList<HashMap<String, String>> list_data;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ShimmerFrameLayout framearticleLayout;
    private RecyclerView lvhape;
    private TextView titlearticle, descriptionarticle, datearticle, viewarticle, toolbar_text;
    private ImageView gambararticle, backpresed;
    private String judul;
    private FrameLayout framesimmer;
    private LinearLayout frameartikel;

    private WebView web;
    TextView title;
    private ImageView presedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        lvhape = (RecyclerView) findViewById(R.id.HomeArticleRV);
        datearticle = (TextView) findViewById(R.id.TanggalArticle);
        framesimmer = findViewById(R.id.frameshimmer);
        titlearticle = (TextView) findViewById(R.id.TitleArticle);
        gambararticle = (ImageView) findViewById(R.id.GambarArticle);
        presedback = findViewById(R.id.backpresedhotellist);
        title = findViewById(R.id.text_view_toolbar_title);
        viewarticle = (TextView) findViewById(R.id.ViewArticle);
        framearticleLayout = findViewById(R.id.ArticleShimmer);
        web = (WebView) findViewById(R.id.webView);
        frameartikel = findViewById(R.id.frameartikel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhotellist);
        setSupportActionBar(toolbar);
        title.setText("Artikel");
        if (title != null && toolbar != null) {
            setSupportActionBar(toolbar);
            presedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        }


        if(getIntent().getExtras()!=null){

            Bundle bundle = getIntent().getExtras();
            judul=bundle.getString("judul");
        }else{
            judul=getIntent().getStringExtra("judul");
        }

        requestQueue = Volley.newRequestQueue(articleActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        String coba=judul.replace(" ", "%20");
        String alamat=url+coba;
        Log.d("Alamat", alamat);

        stringRequest = new StringRequest(Request.Method.GET, alamat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonArray = jsonObject.getJSONObject("data");
                        Log.d("Tes", jsonObject.getString("status_code"));
                    JSONObject jsonfasilitasArray = jsonArray.getJSONObject("art");
                            framearticleLayout.setVisibility(View.GONE);

                            HashMap<String, String> map = new HashMap<String, String>();
                            titlearticle.setText(jsonfasilitasArray.getString("judul"));
                            datearticle.setText(jsonfasilitasArray.getString("registered"));
                    viewarticle.setText(jsonfasilitasArray.getString("view"));
                    Glide.with(getApplicationContext())
                            .load(jsonfasilitasArray.getString("imageArticle"))
                            .into(gambararticle);
                    gambararticle.setVisibility(View.VISIBLE);
                    String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
                    String html = jsonfasilitasArray.getString("content");
                    web.getSettings().getJavaScriptEnabled();
                    web.loadData(String.format(htmlText, html), "text/html", "utf-8");
                    Log.d("Tes", jsonfasilitasArray.getString("judul"));
                    framesimmer.setVisibility(View.INVISIBLE);
                    frameartikel.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(articleActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

}
