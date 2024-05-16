package com.syariahrooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.syariahrooms.login.Login;

public class EmailFragment extends Fragment {
    View view;
    TextView bloginemail, tverror;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_email, container, false);
        bloginemail=view.findViewById(R.id.boboboxLoginEmailET);
        tverror=view.findViewById(R.id.boboboxLoginEmailErrorTV);

        String email=bloginemail.getText().toString();
        Login activity = (Login) getActivity();
        activity.setMyData(email);

        return view;
    }

    public void setText(){
        tverror.setText("Isi Email Terlebih Dahulu");
    }
}