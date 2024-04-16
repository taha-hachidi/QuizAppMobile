package com.example.first_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import androidx.annotation.NonNull;


import com.google.firebase.auth.FirebaseAuth;



public class MainLogin extends AppCompatActivity {
    //1-declaration
    EditText etMail,etPassword;
    Button bLogin;
    TextView tvRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //2-Recuperation des id
        etMail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        bLogin=findViewById(R.id.bLogin);
        tvRegister=findViewById(R.id.tvRegister);
        mAuth = FirebaseAuth.getInstance();

        //3-Association de listners
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4-Traitement
//                if(etLogin.getText().toString().equals("toto")&& etPassword.getText().toString().equals("123")){
//                    startActivity(new Intent(MainActivity.this, Quiz1.class));
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Login or Password incorrect!",Toast.LENGTH_SHORT).show();
//                }
                signIn(etMail.getText().toString(), etPassword.getText().toString());


            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4-Traitement
                startActivity(new Intent(MainLogin.this,RegisterActivity.class));
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainLogin.this, "Sign in success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainLogin.this, QuizActivity.class));

                            // You can add code here to navigate to another activity
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainLogin.this, "Sign in failed. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}