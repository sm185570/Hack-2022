package com.machinemake.NCRed;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Button generateQrCode;

    ImageView qrCode;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
        makeText(MainActivity.this, currentUser.toString(), LENGTH_LONG).show();

        amount = findViewById(R.id.authorizedAmount);
        generateQrCode = findViewById(R.id.GenerateQrButton);

        qrCode = findViewById(R.id.QRCode);



        makeText(MainActivity.this, "Hai", LENGTH_LONG).show();
        generateQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeText(MainActivity.this, "creating Qr for Primary card", LENGTH_LONG).show();
                String amountText = amount.getText().toString();
                String content = String.format("Name: Pulkit, Card Number: XXXX XXXX XXXX XXXX, Date of Expiry: MM/YY, Amount Paid: %s Rs",amountText);
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 1024, 1024);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    qrCode.setImageBitmap(bmp);
                    Thread.sleep(2000);

                }

                catch (WriterException e)
                {
                    Log.e("QR Code", "Error Generating Code");
                    e.printStackTrace();
                }
                catch (Exception e)
                {

                }
            }
        });


    }




}