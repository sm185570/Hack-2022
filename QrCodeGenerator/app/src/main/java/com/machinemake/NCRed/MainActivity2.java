package com.machinemake.NCRed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView pToPayment;
    TextView signOut;
    TextView editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pToPayment = findViewById(R.id.ppayment);
        signOut = findViewById(R.id.signOut2);
        editProfile = findViewById(R.id.editProfile);
        if(getIntent().hasExtra("dp")) {
            ImageView imv= findViewById(R.id.dp);
            Bitmap bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("dp"), 0, getIntent().getByteArrayExtra("dp").length);
            imv.setImageBitmap(bitmap);
        }
        pToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeToPayment();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeToProfile();
            }
        });
    }

    private void ChangeToProfile()
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void ChangeToPayment()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}