package com.syariahrooms;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HotelShimmerFragment extends Fragment {
    public HotelShimmerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View view = inflater.inflate(R.layout.fragment_hotel_list, container, false);
        return view;

    }
}
