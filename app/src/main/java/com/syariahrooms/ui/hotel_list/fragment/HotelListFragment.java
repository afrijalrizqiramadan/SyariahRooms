package com.syariahrooms.ui.hotel_list.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syariahrooms.RoomListActivity;
import com.syariahrooms.ui.hotel_list.activity.HotelListActivity;
import com.syariahrooms.R;
import com.syariahrooms.RVHotelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelListFragment extends Fragment {

    String url = "https://betabackoffice.syariahrooms.com/api/ct_search?";
    ArrayList<HashMap<String, String>> list_data;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView lvhape;
    private Button bfilter, bsort;
    private FrameLayout framesimmer, framerecyce;
    public HotelListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_list, container, false);
        lvhape = view.findViewById(R.id.recycler_view_hotel_list);
        framerecyce = view.findViewById(R.id.framerecycle);
        framesimmer = view.findViewById(R.id.frameshimmer);
        HotelListActivity activity = (HotelListActivity) getActivity();
        String myDataFromActivity = activity.getDatalokasi();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvhape.setLayoutManager(llm);
        String coba=myDataFromActivity.replace(" ", "%20");
        String clientkey = "client_key=qJRCK04wWxcoINai";
        String date = "&date=" + activity.getCheckin();
        String night = "&night=" + activity.getJumlahmalam();
        String type_property = "&type_property=";
        String location = "&location=" + coba;
        String alamat = url + clientkey + date + night + type_property + location + "&param['kab']&param['kec']&param['type']&param['fas_prop']&param['fas_room']&param['type_property']";

        requestQueue = Volley.newRequestQueue(getContext());

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, alamat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("av_data");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("nama", json.getString("namaHS"));
                        map.put("gambar", json.getString("image"));
                        map.put("keyhotel", json.getString("keyHS"));
                        DecimalFormat formatter = new DecimalFormat("#,###,###");
                        map.put("hargaasli", formatter.format(json.getLong("prc_total")));
                        map.put("checkin", activity.getCheckin());
                        map.put("checkout", activity.getChekout());
                        map.put("kamar", activity.getKamar());
                        map.put("tamu", activity.getTamu());
                        map.put("totalmalam", activity.getJumlahmalam());
                        Log.d("Tes", json.getString("image"));
                        list_data.add(map);
                        framesimmer.setVisibility(View.GONE);
                        framerecyce.setVisibility(View.VISIBLE);
                        RVHotelAdapter adapter = new RVHotelAdapter(getContext(), list_data);
                        lvhape.setAdapter(adapter);

                        JSONArray jsonfasilitasArray = json.getJSONArray("fas");
                        for (int b = 0; b < jsonfasilitasArray.length(); b++) {
                            JSONObject jsonf = jsonfasilitasArray.getJSONObject(b);
                        }
                    }
                } catch (JSONException e) {

                    Toast.makeText(getContext(), "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
        return view;

    }


}
