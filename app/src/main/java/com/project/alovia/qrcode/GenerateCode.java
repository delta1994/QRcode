package com.project.alovia.qrcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class GenerateCode extends AppCompatActivity {
    EditText Addgencode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        Addgencode = findViewById(R.id.btn_Add);
    }

    public void onAdd(View view){
        String str_addcodegen = Addgencode.getText().toString();
        String type = "addnum";
        Background background = new Background(this);
        background.execute(type, str_addcodegen);

    }





}
