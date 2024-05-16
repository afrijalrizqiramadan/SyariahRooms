package com.syariahrooms.ui.home;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.syariahrooms.ui.hotel_list.activity.HotelListActivity;
import com.syariahrooms.LocationListActivity;
import com.syariahrooms.R;
import com.syariahrooms.RVArticelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    public final static int PAGES = 4;
    public static int FIRST_PAGE = 1;
    public static Integer[] images = {R.drawable.banner_bg, R.drawable.banner_bg, R.drawable.banner_bg};
    public CarouselPagerAdapter mAdapter;
    public ViewPager mViewPager;
    public Button bCari, bSImpan;
    LinearLayout bKamarTamu, bLocation;
    private TextView bcheckin, bcheckout, tvnight, tvKamar, tvTamu, searchlocation;
    int fyear, fmonth, fdayOfMonth, fdayOfWeek, lyear, lmonth, ldayOfMonth, ldayOfWeek;
    String stringfyear, stringfmonth, stringfdayOfMonth, stringfdayOfWeek, stringlyear, stringlmonth, stringldayOfMonth, stringldayOfWeek;
    String firstdate, lastdate;
    String sdateawal, sdateakhir;

    SimpleDateFormat numbermothformat = new SimpleDateFormat("MM");
    SimpleDateFormat yearformat = new SimpleDateFormat("yyyy");
    SimpleDateFormat dayf = new SimpleDateFormat("dd");
    String[] listkamar = new String[10];
    String[] listtamu = new String[30];
    int jTamu, jKamar;
    long diffDays = 1;
    Calendar calendar;
    View myView;
    boolean isUp;
    String url = "https://betabackoffice.syariahrooms.com/api/get_artc?client_key=qJRCK04wWxcNNNNN&limit=5";
    ArrayList<HashMap<String, String>> list_data;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ShimmerFrameLayout framearticleLayout;
    RelativeLayout changebackground;
    private RecyclerView lvhape;
    public MainHomeFragment() {
        // Required empty public constructor
    }
    String lokasi="";
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        mViewPager = view.findViewById(R.id.view_pager);
        bCari = view.findViewById(R.id.HomeSearchButton);
        bcheckin = view.findViewById(R.id.HomeCheckInDateTV);
        bcheckout = view.findViewById(R.id.HomeCheckOutDateTV);
        bKamarTamu = view.findViewById(R.id.HomeKamarTamuTV);
        bLocation=view.findViewById(R.id.HomeLocationHotelTV);
        bSImpan = view.findViewById(R.id.bsimpan);
        searchlocation = view.findViewById(R.id.text_view_search_location);
        changebackground = view.findViewById(R.id.changebackground);

        tvnight = view.findViewById(R.id.tvNight);
        tvKamar = view.findViewById(R.id.tvKamar);
        tvTamu = view.findViewById(R.id.tvTamu);
        isUp = false;
        myView = view.findViewById(R.id.my_view);
        MainActivity activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.getlokasi();

        if (!myDataFromActivity.isEmpty()){
            searchlocation.setText(myDataFromActivity);
        }
        for (int a = 0; a < listkamar.length; a++) {
            listkamar[a] = String.valueOf(a + 1);
        }
        for (int a = 0; a < listtamu.length; a++) {
            listtamu[a] = String.valueOf(a + 1);
        }

        jTamu = 1;
        jKamar = 1;

        ArrayAdapter<String> adapterkamar = new ArrayAdapter<String>(getContext(), R.layout.listview_kamartamu, listkamar);
        ListView listviewkamar = (ListView) view.findViewById(R.id.listKamar);
        listviewkamar.setAdapter(adapterkamar);
        ArrayAdapter<String> adaptertamu = new ArrayAdapter<String>(getContext(), R.layout.listview_kamartamu, listtamu);
        ListView listviewtamu = (ListView) view.findViewById(R.id.listTamu);
        listviewtamu.setAdapter(adaptertamu);
        listviewkamar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                jKamar = Integer.parseInt(listkamar[position]);
                tvKamar.setText(jKamar + " Kamar");
            }
        });
        listviewtamu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                jTamu = Integer.parseInt(listtamu[position]);
                tvTamu.setText(jTamu + " Tamu");
            }
        });
        bLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LocationListActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });


        Date date = new Date();
        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
        SimpleDateFormat sdd = new SimpleDateFormat("EEE");
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

        sdateawal = dt1.format(date);
        sdateakhir = dt1.format(tomorrow);

        firstdate = sdd.format(date) + ", " + sdf.format(date);
        lastdate = sdd.format(tomorrow) + ", " + sdf.format(tomorrow);

        bcheckin.setText(firstdate);
        bcheckout.setText(lastdate);

        stringfdayOfMonth = dayf.format(date);
        stringfmonth = String.valueOf(Integer.parseInt(numbermothformat.format(date)) - 1);
        stringfyear = yearformat.format(date);

        bCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lokasi.isEmpty()){
                    Toast.makeText(getContext(), "Pilih Lokasi Terlebih Dahulu", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(view.getContext(), HotelListActivity.class);
                    Bundle a=new Bundle();
                    a.putString("checkin", sdateawal);
                    a.putString("checkout", sdateakhir);
                    a.putString("totalmalam", String.valueOf(diffDays));
                    a.putString("lokasi",lokasi);
                    a.putString("tamu",String.valueOf(jTamu));
                    a.putString("kamar",String.valueOf(jKamar));
                    intent.putExtras(a);
                    startActivity(intent);
                }
            }
        });

        bSImpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDown(myView);
                changebackground.setVisibility(View.INVISIBLE);
                isUp = !isUp;
            }
        });

        bcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fshowDateDialog();

            }
        });

        bcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lshowDateDialog();

            }
        });
        bKamarTamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUp) {
                    slideDown(myView);
                    changebackground.setVisibility(View.INVISIBLE);

                } else {
                    slideUp(myView);
                    changebackground.setVisibility(View.VISIBLE);
                }
                isUp = !isUp;
            }
        });

        mAdapter = new CarouselPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(false, mAdapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        mViewPager.setCurrentItem(FIRST_PAGE);

        // Necessary or the mViewPager will only have one extra page to show
        // make this at least however many pages you can see
        mViewPager.setOffscreenPageLimit(3);

        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        mViewPager.setPageMargin(-200);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (FIRST_PAGE == PAGES) {
                    FIRST_PAGE = 0;
                }
                mViewPager.setCurrentItem(FIRST_PAGE++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        lvhape = (RecyclerView) view.findViewById(R.id.HomeArticleRV);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        lvhape.setLayoutManager(llm);
        framearticleLayout=view.findViewById(R.id.HomePromoShimmer);
        requestQueue = Volley.newRequestQueue(getContext());

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("nama", json.getString("judul"));
                        map.put("gambar", json.getString("imageArticle"));
                        Log.d("Tes", json.getString("imageArticle"));
                        list_data.add(map);
                        RVArticelAdapter adapter = new RVArticelAdapter(getContext(), list_data);
                        framearticleLayout.setVisibility(View.GONE);
                        lvhape.setVisibility(View.VISIBLE);
                        lvhape.setAdapter(adapter);

//                        JSONArray jsonfasilitasArray = json.getJSONArray("fas");
//                        for (int b = 0; b < jsonfasilitasArray.length(); b++) {
//                            JSONObject jsonf = jsonfasilitasArray.getJSONObject(b);
//                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

        return view;
    }



    private void fshowDateDialog() {
        datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogThemeDate,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Date fdate1 = new Date(year, month, day);
                        Date fdate2 = new Date(year, month, day - 1);

                        Date ldate1 = new Date(fdate1.getTime() + (1000 * 60 * 60 * 24));
                        Date ldate2 = new Date(fdate2.getTime() + (1000 * 60 * 60 * 24));

                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
                        SimpleDateFormat sdd = new SimpleDateFormat("EEE");
                        SimpleDateFormat dt1 = new SimpleDateFormat("YY-MM-dd");

                        sdateawal = "20" + dt1.format(fdate1);
                        sdateakhir = "20" + dt1.format(ldate1);
                        firstdate = sdd.format(fdate2) + ", " + sdf.format(fdate1);
                        lastdate = sdd.format(ldate2) + ", " + sdf.format(ldate1);

                        bcheckout.setText(lastdate);
                        bcheckin.setText(firstdate);

                        stringfdayOfMonth = String.valueOf(day);
                        stringfmonth = String.valueOf(month);
                        stringfyear = String.valueOf(year);

                    }
                }, fyear, fmonth, fdayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void lshowDateDialog() {

        datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogThemeDate,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Date date1 = new Date(year, month, day);
                        Date date2 = new Date(year, month, day - 1);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
                        SimpleDateFormat sdd = new SimpleDateFormat("EEE");

                        lastdate = sdd.format(date2) + ", " + sdf.format(date1);

                        stringldayOfMonth = String.valueOf(day);
                        stringlmonth = String.valueOf(month);
                        stringlyear = String.valueOf(year);


                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.MONTH, Integer.parseInt(stringfmonth));
                        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(stringfdayOfMonth));
                        cal.set(Calendar.YEAR, Integer.parseInt(stringfyear));

                        Calendar calnew = Calendar.getInstance();
                        calnew.set(Calendar.MONTH, Integer.parseInt(stringlmonth));
                        calnew.set(Calendar.DAY_OF_MONTH, Integer.parseInt(stringldayOfMonth));
                        calnew.set(Calendar.YEAR, Integer.parseInt(stringlyear));

                        long diff = calnew.getTimeInMillis() - cal.getTimeInMillis();
                        diffDays = diff / (24 * 60 * 60 * 1000);
                        tvnight.setText(diffDays + " Malam");

                        bcheckout.setText(lastdate);
                    }
                }, lyear, lmonth, ldayOfMonth);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Integer.parseInt(stringfmonth));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(stringfdayOfMonth));
        cal.set(Calendar.YEAR, Integer.parseInt(stringfyear));
        cal.add(Calendar.DATE, 1);

        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog.show();
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                lokasi = data.getStringExtra("lokasi");
                searchlocation.setText(lokasi);
            }
        }

    }
}