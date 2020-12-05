package com.example.progress_report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterCourse extends AppCompatActivity {

    String userEmail = "";

    EditText studId;
    EditText course;
    Button submit;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference stuRef = database.getReference("student");
    DatabaseReference stuRef1= database.getReference("Allcourses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_registration);
/*
        //Google sign
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            Toast.makeText(this, "Welcome back " + signInAccount.getDisplayName(), Toast.LENGTH_SHORT).show();
            userEmail = signInAccount.getEmail();
        }*/

        studId = findViewById(R.id.studentID);
        course = findViewById(R.id.courseName);
        submit = findViewById(R.id.submitBtn);
        TextView cdata=findViewById(R.id.textView_cdata);
        Intent i=getIntent();
        String id=i.getStringExtra("Id1");
        studId.setText(id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = studId.getText().toString();
                final String cname = course.getText().toString();

                stuRef = stuRef.child(id + "/course");

                stuRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long c = snapshot.getChildrenCount()+1;
                            Toast.makeText(RegisterCourse.this, c+""+"Registered", Toast.LENGTH_SHORT).show();

                        stuRef.child(c+"").setValue(cname);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        stuRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
     String courses=""+snapshot.getValue() ;
            cdata.setText(courses);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}