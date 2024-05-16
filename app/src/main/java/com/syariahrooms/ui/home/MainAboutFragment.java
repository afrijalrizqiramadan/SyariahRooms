package com.syariahrooms.ui.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.syariahrooms.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainAboutFragment extends Fragment {
    TextView aboutus, privacy, term, telp, email, frenchise;

    public MainAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_about, container, false);
        aboutus = view.findViewById(R.id.boboboxAboutAboutUsTV);
        privacy = view.findViewById(R.id.boboboxAboutPrivacyPolicyTV);
        term = view.findViewById(R.id.boboboxAboutTermsandConditionsTV);
        telp = view.findViewById(R.id.boboboxAboutCustomerServiceTV);
        email = view.findViewById(R.id.boboboxAboutInfoTV);
        frenchise = view.findViewById(R.id.boboboxAboutFranchiseTV);

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.syariahrooms.com/booking/contact";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.syariahrooms.com/booking/contact";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.syariahrooms.com/booking/contact";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        frenchise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "franchise@syariahrooms.co.id");
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "info@syariahrooms.co.id");
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String posted_by = "6282119007791";

                String uri = "tel:" + posted_by.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        return view;
    }

}
