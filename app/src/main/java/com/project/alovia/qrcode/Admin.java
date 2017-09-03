package com.project.alovia.qrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Admin extends AppCompatActivity {

    EditText search1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        search1 = (EditText) findViewById(R.id.et_search1);

    }

    public void onsearch1(View view){
        String search2 = search1.getText().toString();
        String type = "search22";
        Backgroundcode1 backgroundcode1 = new Backgroundcode1(this);
        backgroundcode1.execute(type, search2);
    }


}
