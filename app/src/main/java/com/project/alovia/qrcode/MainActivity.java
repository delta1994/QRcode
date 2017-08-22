package com.project.alovia.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    Button scanner;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        scanner = (Button) findViewById(R.id.btn_scanner);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    public void OnLogin(View view){
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "Login";
        Background background = new Background(this);
        background.execute(type, username, password);
    }

    public void OpenReg(View view){

        startActivity(new Intent(this, Register.class));

    }




}
