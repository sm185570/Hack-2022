package com.machinemake.NCRed;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.machinemake.NCRed.data.model.User;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    TextView register;
    TextView email;
    TextView password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        signIn = findViewById(R.id.signIn);
        register = findViewById(R.id.register);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.secTxt);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            GoToHome(currentUser);
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeToLogin();
            }
        });
    }

    private void GoToHome(FirebaseUser currentUser) {
        User u = new User();
        u.Email = currentUser.getEmail();

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("KEY_NAME", (Serializable) u);
        startActivity(intent);
    }

    private void ChangeToLogin() {
        Intent intent = new Intent(this, RegisterationActivity.class);
        startActivity(intent);
    }
}