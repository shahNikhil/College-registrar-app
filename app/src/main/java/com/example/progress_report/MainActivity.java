package com.example.progress_report;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //Declaring the FirebaseAuth for google sign in
    private FirebaseAuth firebaseAuth;
    //login data handlers
    Button loginButton = findViewById(R.id.loginButton);
    EditText userMail = findViewById(R.id.userMail);
    EditText userPassword = findViewById(R.id.userPassword);
    //loading bar for signing in
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton.setOnClickListener((v)->{checkCredentials();});
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(MainActivity.this);
    }

    private void checkCredentials() {
    }
}
