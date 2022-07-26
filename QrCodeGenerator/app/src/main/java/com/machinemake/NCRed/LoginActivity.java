package com.machinemake.NCRed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        signIn = findViewById(R.id.signIn);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeToLogin();
            }
        });
    }

    private void ChangeToLogin() {
        Intent intent = new Intent(this, RegisterationActivity.class);
        startActivity(intent);
    }
}