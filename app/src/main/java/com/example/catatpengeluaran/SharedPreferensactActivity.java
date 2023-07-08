package com.example.catatpengeluaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SharedPreferensactActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private SharePrevManager sharePrevManager;
    private EditText etUsername, etPassword;
    private Button btnlogin;
    private ProgressBar pbLogin;
    private ImageView ivLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferensact);

        sharePrevManager = new SharePrevManager(this);

        if (sharePrevManager.getIsLogin()){
            //Kondisi Jika User Sudah Login
            startHomeUI();
        } else {
            //Kondisi Jika User Belum Log in
            etUsername = findViewById(R.id.et_nama);
            etPassword = findViewById(R.id.et_password);
            btnlogin = findViewById(R.id.btnLogin);
            pbLogin = findViewById(R.id.pblogin);
            ivLogin = findViewById(R.id.login);
        }
    }

    public void startHomeUI(){
        Intent i = new Intent(SharedPreferensactActivity.this,LoginActivity.class);

        startActivity(i);
        finishAffinity();
    }

    public void Login(){
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ambil value dari edittext

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                pbLogin.setVisibility(View.VISIBLE);
                ivLogin.setVisibility(View.GONE);

                if (username.isEmpty() || password.isEmpty()){
                    pbLogin.setVisibility(View.GONE);
                    ivLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(SharedPreferensactActivity.this, "Username dan passwoard tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String spUsername = sharePrevManager.getUsername();
                            String spPassword = sharePrevManager.getPassword();

                            Log.d("username","user"+username);
                            Log.d("password","password"+password);

                            if (username.equals(spUsername) && password.equals(spPassword)){
                                Intent i = new Intent(SharedPreferensactActivity.this,LoginActivity.class);
                                startActivity(i);
                            }else {
                                pbLogin.setVisibility(View.GONE);
                                ivLogin.setVisibility(View.VISIBLE);
                                Toast.makeText(SharedPreferensactActivity.this, "Username dan password salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 3000);
                }
            }
        });
    }
}