package com.machinemake.NCRed;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.machinemake.NCRed.data.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class RegisterationActivity extends AppCompatActivity {
    private static final int GET_FROM_GALLERY = 9090;
    Button join;
    TextView uploadPic;
    TextView login;
    TextView email;
    TextView password;
    ImageView pic;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);
        join = findViewById(R.id.join);
//        uploadPic = findViewById(R.id.txtUploadPic);
        login = findViewById(R.id.lnkLogin);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.secTxt);
        pic = findViewById(R.id.pic);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                        .addOnCompleteListener(RegisterationActivity.this, new OnCompleteListener<AuthResult>()
//                        {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    GoToHome(mAuth.getCurrentUser());
//                                }
//                                else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//
//                                }
//                            }
//                        });
                Intent intent = new Intent(RegisterationActivity.this, MainActivity2.class);

                startActivity(intent);

            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                pic.setRotation(-270);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeToLogin();
            }
        });
    }

    private void GoToHome( FirebaseUser currentUser) {
        User u = new User();
        u.Email = currentUser.getEmail();

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("KEY_NAME", (Serializable) u);
        startActivity(intent);
    }

    private void ChangeToMain()
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    private void ChangeToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                pic.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}