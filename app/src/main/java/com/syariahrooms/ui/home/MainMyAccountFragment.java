package com.syariahrooms.ui.home;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.syariahrooms.R;
import com.syariahrooms.login.Login;
import com.syariahrooms.login.SharedManPreferences;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMyAccountFragment extends Fragment {


    public MainMyAccountFragment() {
        // Required empty public constructor
    }

    private TextView blogout, boboboxMyAccountUsernameTV, boboboxMyAccountEmailTV, boboboxMyAccountPhoneTV;
    SharedPreferences prf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_my_account, container, false);
        blogout = view.findViewById(R.id.boboboxMyAccountLogoutTV);
        boboboxMyAccountUsernameTV = view.findViewById(R.id.boboboxMyAccountUsernameTV);
        boboboxMyAccountEmailTV = view.findViewById(R.id.boboboxMyAccountEmailTV);
        boboboxMyAccountPhoneTV = view.findViewById(R.id.boboboxMyAccountPhoneTV);
        prf = Objects.requireNonNull(getContext()).getSharedPreferences("user_details", MODE_PRIVATE);

        boboboxMyAccountUsernameTV.setText(prf.getString("username", null));
        boboboxMyAccountEmailTV.setText(prf.getString("email", null));
        boboboxMyAccountPhoneTV.setText(prf.getString("telepon", null));
        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(),
                        Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();

            }
        });
        return view;
    }

}
