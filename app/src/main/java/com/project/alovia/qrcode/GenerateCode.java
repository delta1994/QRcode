package com.project.alovia.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.Map;

public class GenerateCode extends AppCompatActivity {

    public static final String URL = "http://jonhslim.pe.hu/tor/insert.php";
    private Button btnAdd, btnGen;
    private EditText etgetnum;
    private String numg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        final Context context = this;

        etgetnum = (EditText) this.findViewById(R.id.et_getnum);
        btnGen =  (Button) this.findViewById(R.id.btn_Gen);
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text20r = etgetnum.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text20r, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, code.class);
                    intent.putExtra("pic",bitmap);
                    context.startActivity(intent);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        onMyadd();
        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onEditText();
                onButtClick();
            }
        });

    }

    private void onMyadd() {
        etgetnum = (EditText) findViewById(R.id.et_getnum);
        btnAdd = (Button) findViewById(R.id.btn_Add);
    }

    private void onEditText() {
        numg1 = etgetnum.getText().toString();
    }

    private void onButtClick() {
        if (!numg1.isEmpty()){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("onResponse", response);
                    etgetnum.setText("");
                    Toast.makeText(GenerateCode.this, "เพิ่มข้อมูลแล้ว", Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError", error.toString());
                    Toast.makeText(GenerateCode.this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("sendnum",numg1);
                    return params;

                }
            };
            requestQueue.add(request);
        }

    }


}
