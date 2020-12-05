package com.example.progress_report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentActivity extends AppCompatActivity {

    Button logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        logout = findViewById(R.id.logoutButton);
        TextView name=findViewById(R.id.textView_name);
        TextView email=findViewById(R.id.textView_email);
        TextView identi=findViewById(R.id.Text_id);
        TextView txt_course=findViewById(R.id.textView_course);
        final EditText test=findViewById(R.id.editText_test);
        final Button taketest=findViewById(R.id.button_test);
        final Button reg=findViewById(R.id.button_register);
        firebaseAuth = FirebaseAuth.getInstance();
        Intent i=getIntent();
        String id=i.getStringExtra("Id");
        String nm=i.getStringExtra("name");
        String em=i.getStringExtra("email");
        String courses=i.getStringExtra("course");
        name.setText(nm);
        email.setText(em);
        identi.setText(id);
        txt_course.setText(courses);
        logout.setOnClickListener(new View.OnClickListener() {
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
        taketest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(test.getText().toString().isEmpty()){
                    Toast.makeText(StudentActivity.this,"Please Enter valid course name",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i;
                    i=new Intent(StudentActivity.this,TakeTest.class);
                    String courseName=test.getText().toString();
                    i.putExtra("course",courseName);
                    startActivity(i);

                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(StudentActivity.this,RegisterCourse.class);
                n.putExtra("Id1",id);
                startActivity(n);
            }
        });

    }
}