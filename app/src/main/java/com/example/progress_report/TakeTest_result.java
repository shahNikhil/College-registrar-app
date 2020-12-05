package com.example.progress_report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TakeTest_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test_result);
        TextView correct=findViewById(R.id.txt_correct);
        TextView incorrect=findViewById(R.id.txt_incorrect);
        TextView total=findViewById(R.id.txt_total);
        Intent i=getIntent();
        String tot=i.getStringExtra("total");
        String cor=i.getStringExtra("correct");
        String incorr=i.getStringExtra("incorrect");
        correct.setText(cor);
        incorrect.setText(incorr);
        total.setText(tot);
        Button home=findViewById(R.id.button_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(TakeTest_result.this,LoginActivity.class);
                Toast.makeText(TakeTest_result.this,"Session expired",Toast.LENGTH_LONG).show();
               startActivity(myintent);
            }
        });
    }
}