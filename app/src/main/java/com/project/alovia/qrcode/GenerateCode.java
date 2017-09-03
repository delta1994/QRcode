package com.project.alovia.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateCode extends AppCompatActivity {
    EditText getnum1, etgen12;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);
        getnum1 = (EditText) findViewById(R.id.et_getnum);

        final Context context = this;
        etgen12 = (EditText) this.findViewById(R.id.et_getnum);
        button = (Button) this.findViewById(R.id.btn_Gen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text20r = etgen12.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text20r, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, code.class);
                    intent.putExtra("pic",bitmap);
                    context.startActivity(intent);
                }catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void onAdd(View view){

        String getnum2 = getnum1.getText().toString();
        String type = "addgenCode";
        Backgroundgencode backgroundgencode = new Backgroundgencode(this);
        backgroundgencode.execute(type, getnum2);
    }

    }