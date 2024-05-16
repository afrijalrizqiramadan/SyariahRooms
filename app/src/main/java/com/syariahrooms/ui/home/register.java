package com.syariahrooms.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.syariahrooms.login.Login;
import com.syariahrooms.R;
import com.syariahrooms.TelpFragment;
import com.syariahrooms.login.SharedManPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    Button bregister;
    TextView btologin, bregisterchange, tanggallahir;
    TextView erroremail, errortelepon, errornama, errorgender, errorpassword, errortanggallahir;
    Boolean state = true;
    EditText email, telepon, nama, password;
    DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
    int callerId = -1;
    private Context ctx = this;
    SharedManPreferences sharedPrefManager;
    RelativeLayout loadingpane;
    RadioGroup gender;
    TextView tvToDate;
    //  public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATE_FORMAT = "EEE, MMM d, yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPrefManager = new SharedManPreferences(this);
        loadingpane = (RelativeLayout) findViewById(R.id.loadingpaneregister);
        gender = (RadioGroup) findViewById(R.id.rdgender);
        email = (EditText) findViewById(R.id.boboboxRegisterEmailET);
        password = (EditText) findViewById(R.id.RegisterPasswordET);
        nama = (EditText) findViewById(R.id.boboboxRegisterNamaET);
        tanggallahir = (TextView) findViewById(R.id.boboboxRegisterDateET);
        telepon = (EditText) findViewById(R.id.boboboxRegisterTeleponET);

        erroremail = (TextView) findViewById(R.id.boboboxRegisterEmailErrorTV);
        errorgender = (TextView) findViewById(R.id.boboboxGenderErrorTV);
        errorpassword = (TextView) findViewById(R.id.RegisterPasswordErrorTV);
        errornama = (TextView) findViewById(R.id.boboboxRegisterNamaErrorTV);
        errortanggallahir = (TextView) findViewById(R.id.boboboxRegisterDateErrorTV);
        errortelepon = (TextView) findViewById(R.id.boboboxRegisterTeleponErrorTV);

        bregister = (Button) findViewById(R.id.RegistrationButton);
        btologin = (TextView) findViewById(R.id.RegistrationLoginTV);
        tvToDate=findViewById(R.id.boboboxRegisterDateET);
        btologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, Login.class);
                startActivity(intent);
            }
        });

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi();
            }
        });

        tvToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view.getId(), tvToDate.getText().toString().trim());
            }
        });
    }
    public void showDatePickerDialog(int callerId, String dateText) {
        this.callerId = callerId;
        Date date = null;

        try {
            if (dateText.equals(""))
                date = new Date();
            else
                date = dateFormatter.parse(dateText);
        } catch (Exception exp) {
            // In case of expense initializa date with new Date
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // calendar month 0-11
        int day = calendar.get(Calendar.DATE);
        // date picker initialization
        DatePickerDialog datePicker = new DatePickerDialog(ctx, R.style.DialogThemeDate, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                handleOnDateSet(year, month, day);
            }
        }, year, month, day);
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", datePicker);
        datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Cancel button clicked
            }
        });
        datePicker.show();
    }

    public void handleOnDateSet(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        String formatedDate = dateFormatter.format(date);
                tvToDate.setText(formatedDate);
    }

    @SuppressLint("ResourceType")
    public void validasi() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.getText().toString().isEmpty()) {
            email.requestFocus();
            erroremail.setText("Email Belum Dimasukkan");
        } else {
            if (!email.getText().toString().matches(emailPattern)) {
                email.requestFocus();
                erroremail.setText("Masukkan Email yang Valid");
            } else {
                erroremail.setText("");
                if (nama.getText().toString().isEmpty()) {
                    nama.requestFocus();
                    errornama.setText("Nama Belum Dimasukkan");
                } else {
                    errornama.setText("");
                    if (gender.getCheckedRadioButtonId() <= 0) {
                        errorgender.setText("Gender Belum Dipilih");
                    } else {
                        errorgender.setText("");
                        if (tanggallahir.getText().toString().isEmpty()) {
                            errortanggallahir.setText("Tanggal Lahir Belum Dimasukkan");
                        } else {
                            errortanggallahir.setText("");
                            if (telepon.getText().toString().isEmpty()) {
                                errortelepon.setText("No Telepon Belum Dimasukkan");
                            } else {
                                errortelepon.setText("");
                                if (password.getText().toString().isEmpty()) {
                                    password.requestFocus();
                                    errorpassword.setText("Password Belum Dimasukkan");
                                } else {
                                    password.requestFocus();
                                    loadingpane.setVisibility(View.VISIBLE);

                                    sendRegister();
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private void sendRegister() {
        // Setting POST request ke server
        StringRequest loginRequest = new StringRequest(Request.Method.POST, "https://betabackoffice.syariahrooms.com/api/register_cust",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            // Mengambil variable status pada response
                            String status = json.getString("status_code");
                            if (status.equals("200")) {
                                // Jika Login Sukses Maka pindah ke activity lain
                                Intent intent = new Intent(register.this, Login.class);
                                startActivity(intent);

                                finish();
                            } else if (status.equals("500")) {
                                // Jika Login Sukses Maka pindah ke activity lain
                                Toast.makeText(getApplicationContext(), "Email sudah digunakan", Toast.LENGTH_LONG).show();
                                loadingpane.setVisibility(View.INVISIBLE);
                            } else {
                                // Jika Login Gagal Maka mengeluarkan Toast dengan message.
                                Toast.makeText(getApplicationContext(), "Isi data dengan lengkap dan benar", Toast.LENGTH_LONG).show();
                                loadingpane.setVisibility(View.INVISIBLE);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Gagal Mendaftar" + e, Toast.LENGTH_LONG).show();
                            loadingpane.setVisibility(View.INVISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle response dari server ketika gagal
                        Toast.makeText(getApplicationContext(), "Gagal Mendaftar", Toast.LENGTH_LONG).show();
                        loadingpane.setVisibility(View.INVISIBLE);

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("client_key", "qJRCK04wWxcNNNNN");
                params.put("email_cu", email.getText().toString());
                params.put("name_cu", nama.getText().toString());
                params.put("hp_cu", telepon.getText().toString());
                params.put("pass_cu", password.getText().toString());
                params.put("gender_cu", ((RadioButton) findViewById(gender.getCheckedRadioButtonId()))
                        .getText().toString());
                params.put("bdate_cu", tanggallahir.getText().toString());
                return params;
            }
        };
        // Buat antrian request pada cache android
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Tambahkan Request pada antrian request
        requestQueue.add(loginRequest);
    }

}
