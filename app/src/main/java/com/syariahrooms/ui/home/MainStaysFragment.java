package com.syariahrooms.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.syariahrooms.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainStaysFragment extends Fragment {


    public MainStaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_stays, container, false);
    }

}
