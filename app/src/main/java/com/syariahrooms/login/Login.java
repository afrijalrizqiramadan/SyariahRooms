package com.syariahrooms.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syariahrooms.EmailFragment;
import com.syariahrooms.HotelDetailActivity;
import com.syariahrooms.R;
import com.syariahrooms.RVArticelAdapter;
import com.syariahrooms.TelpFragment;
import com.syariahrooms.ui.home.LupaPasswordActivity;
import com.syariahrooms.ui.home.MainActivity;
import com.syariahrooms.ui.home.register;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button blogin;
    EditText etPassword;
    Boolean state = true;
    Boolean statepassword = true;
    TextView btoregister, bloginchange, etpassword, tverror, etemail;
    ImageView bpasshow;
    RelativeLayout loadingpane;
    String akun = "";
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    SharedPreferences pref;
    String url = "https://betabackoffice.syariahrooms.com/api/get_cust?client_key=qJRCK04wWxcNNNNN&email_cu=";

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btoregister = (TextView) findViewById(R.id.LoginRegisterTV);
        blogin = (Button) findViewById(R.id.LoginButton);
        bloginchange = (TextView) findViewById(R.id.LoginContactChangeTV);
        tverror = (TextView) findViewById(R.id.LoginPasswordErrorTV);
//        bforgotpassword = (TextView) findViewById(R.id.LoginForgotPasswordTV);
        etPassword = (EditText) findViewById(R.id.LoginPasswordET);
        bpasshow = (ImageView) findViewById(R.id.LoginShowPasswordIV);
        etemail = (EditText) findViewById(R.id.boboboxLoginEmailET);
        requestQueue = Volley.newRequestQueue(Login.this);

        loadingpane = (RelativeLayout) findViewById(R.id.loadingpane);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        ProgressBar mProgressBarScore;
        // onCreate


//        if (sharedPrefManager.getSPSudahLogin()){
//            startActivity(new Intent(Login.this, MainActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }
//        bforgotpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, LupaPasswordActivity.class);
//                startActivity(intent);
//            }
//        });

        bpasshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!statepassword) {
                    bpasshow.setImageResource(R.drawable.design_ic_visibility);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    statepassword = true;

                } else {
                    //Jika tidak, maka password akan di sembuyikan
                    bpasshow.setImageResource(R.drawable.design_ic_visibility_off);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    statepassword = false;

                }
            }
        });

        btoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, register.class);
                startActivity(intent);
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty()) {
                    tverror.setText("Isi Password");
                } else {
                    loadingpane.setVisibility(View.VISIBLE);
                    sendLogin();
                }
            }
        });
    }

    private void loadFragment(Fragment fragment) {

// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();

// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.LoginFragmentContainerLL, fragment);
        fragmentTransaction.commit(); // save the changes

    }

    private void sendLogin() {
        // Setting POST request ke server
        StringRequest loginRequest = new StringRequest(Request.Method.POST, "https://betabackoffice.syariahrooms.com/api/login_cust",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response ", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            // Mengambil variable status pada response
                            JSONObject jsonArray = json.getJSONObject("data");
                            String status = jsonArray.getString("status");
                            Log.d("response ", status);

                            if (status.equals("1")) {
                                String alamaturl = url + etemail.getText().toString();

                                stringRequest = new StringRequest(Request.Method.GET, alamaturl, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("response ", response);
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            JSONObject jsonArray = jsonObject.getJSONObject("data");
                                            JSONObject bio_cu = jsonArray.getJSONObject("bio_cu");
                                            JSONObject data = bio_cu.getJSONObject("data");
                                            SharedPreferences.Editor editor = pref.edit();
                                            editor.putString("username", data.getString("nama_cu"));
                                            editor.putString("email", data.getString("email_cu"));
                                            editor.putString("telepon", data.getString("hp_cu"));
                                            editor.apply();
                                            Log.d("email_cu", data.getString("email_cu"));

                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();

                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                                            loadingpane.setVisibility(View.INVISIBLE);

                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                                        loadingpane.setVisibility(View.INVISIBLE);

                                    }
                                });
                                requestQueue.add(stringRequest);


                            } else {
                                // Jika Login Gagal Maka mengeluarkan Toast dengan message.
                                Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_LONG).show();
                                loadingpane.setVisibility(View.INVISIBLE);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                            loadingpane.setVisibility(View.INVISIBLE);


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle response dari server ketika gagal
                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                        loadingpane.setVisibility(View.INVISIBLE);

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("client_key", "qJRCK04wWxcNNNNN");
                params.put("email_cu", etemail.getText().toString());
                params.put("pass_cu", etPassword.getText().toString());
                return params;
            }
        };
        // Buat antrian request pada cache android
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Tambahkan Request pada antrian request
        requestQueue.add(loginRequest);
    }

    public void setMyData(String myString) {
        this.akun = myString;
    }

    public String getMyData() {
        return akun;
    }
}
