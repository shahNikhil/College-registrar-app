package com.example.progress_report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class InstructorActivity extends AppCompatActivity {

    Button logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        logout = findViewById(R.id.logoutButton);
        TextView inEm=findViewById(R.id.textViewInemail);
        TextView inco=findViewById(R.id.textViewIncourses);
        firebaseAuth = FirebaseAuth.getInstance();
        Intent i=getIntent();
        String email=i.getStringExtra("email");
        String cours=i.getStringExtra("course");
        inEm.setText(email);
        inco.setText(cours);
        logout.setOnClickListener(new View.OnClickListener() {
            //String userEmail = firebaseAuth.getCurrentUser().getEmail();
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    firebaseAuth.signOut();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}