package com.example.progress_report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progress_report.model.Ques_data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TakeTest extends AppCompatActivity {
    Button b1, b2, b3, b4;
    TextView t1_ques, timertxt;
    int total = 0;
    int correct = 0;
    int wrong = 0;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);


        b1=findViewById(R.id.op1);
        b2=findViewById(R.id.op2);
        b3=findViewById(R.id.op3);
        b4=findViewById(R.id.op4);
        t1_ques=findViewById(R.id.ques_txt);
        timertxt=findViewById(R.id.timer);


        updateques() ;
        timer(300,timertxt);

    }

    private void updateques(){
        total++;
        if(total>100 ){

//open result
        }




        else{


            Intent i = getIntent();
            String cnm = i.getStringExtra("course").toUpperCase();

          /*  FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference stuRef = database.getReference("courses");
            stuRef.child(cnm+"/questions");
            stuRef.addValueEventListener(new ValueEventListener()*/

           reference= FirebaseDatabase.getInstance().getReference("courses").child(cnm+"/questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener()

               {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Ques_data ques = snapshot.getValue(Ques_data.class);

                    if(ques.getQuestion().equals("the end")){
                        int extot=correct+wrong;
                        Toast.makeText(TakeTest.this,"Test Finished",Toast.LENGTH_LONG).show();
                        Intent myintent=new Intent(TakeTest.this,TakeTest_result.class);
                        myintent.putExtra("total",String.valueOf(extot));
                        myintent.putExtra("correct",String.valueOf(correct));
                        myintent.putExtra("incorrect",String.valueOf(wrong));
                        startActivity(myintent);
                    }
                    else {
                        t1_ques.setText(ques.getQuestion());
                        b1.setText(ques.getOp1());

                        b2.setText(ques.getOp2());
                        b3.setText(ques.getOp3());
                        b4.setText(ques.getOp4());


                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (b1.getText().toString().equals(ques.getAns())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct++;
                                            b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                            updateques();

                                        }
                                    }, 1500);

                                }
                                //wrong ans
                                else {
                                    wrong++;
                                    b1.setBackgroundColor(Color.RED);
                                    if (b2.getText().toString().equals(ques.getAns())) {
                                        b2.setBackgroundColor(Color.GREEN);


                                    } else if (b3.getText().toString().equals(ques.getAns())) {
                                        b3.setBackgroundColor(Color.GREEN);


                                    } else if (b4.getText().toString().equals(ques.getAns())) {
                                        b4.setBackgroundColor(Color.GREEN);


                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(() -> {
                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateques();
                                    }, 1500);


                                }

                            }

                        });

                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (b2.getText().toString().equals(ques.getAns())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct++;
                                            b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                            updateques();

                                        }
                                    }, 1500);
                                }
                                //wrong ans
                                else {
                                    wrong++;
                                    b2.setBackgroundColor(Color.RED);
                                    if (b1.getText().toString().equals(ques.getAns())) {
                                        b1.setBackgroundColor(Color.GREEN);


                                    } else if (b3.getText().toString().equals(ques.getAns())) {
                                        b3.setBackgroundColor(Color.GREEN);


                                    } else if (b4.getText().toString().equals(ques.getAns())) {
                                        b4.setBackgroundColor(Color.GREEN);


                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(() -> {
                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateques();
                                    }, 1500);


                                }
                            }

                        });
                        b3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (b3.getText().toString().equals(ques.getAns())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct++;
                                            b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                            updateques();

                                        }
                                    }, 1500);
                                }
                                //wrong ans
                                else {
                                    wrong++;
                                    b3.setBackgroundColor(Color.RED);
                                    if (b1.getText().toString().equals(ques.getAns())) {
                                        b1.setBackgroundColor(Color.GREEN);


                                    } else if (b2.getText().toString().equals(ques.getAns())) {
                                        b2.setBackgroundColor(Color.GREEN);


                                    } else if (b4.getText().toString().equals(ques.getAns())) {
                                        b4.setBackgroundColor(Color.GREEN);


                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(() -> {
                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateques();
                                    }, 1500);


                                }
                            }

                        });
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (b4.getText().toString().equals(ques.getAns())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct++;
                                            b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                            updateques();

                                        }
                                    }, 1500);
                                }
                                //wrong ans
                                else {
                                    wrong++;
                                    b4.setBackgroundColor(Color.RED);
                                    if (b1.getText().toString().equals(ques.getAns())) {
                                        b1.setBackgroundColor(Color.GREEN);


                                    } else if (b3.getText().toString().equals(ques.getAns())) {
                                        b3.setBackgroundColor(Color.GREEN);


                                    } else if (b2.getText().toString().equals(ques.getAns())) {
                                        b2.setBackgroundColor(Color.GREEN);


                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(() -> {
                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateques();
                                    }, 1500);


                                }
                            }

                        });

                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }
    public void timer(int seconds,final TextView timertxt){

        new CountDownTimer(seconds*1000+1000,1000){
            public void onTick(long milunfinish){
                int seconds =(int)milunfinish/1000;
                int minutes=seconds/60;
                seconds=seconds%60;
                timertxt.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds));

            }
            public void onFinish(){
                int extot=correct+wrong;
                timertxt.setText("Completed");
                Intent myintent=new Intent(TakeTest.this,TakeTest_result.class);
                myintent.putExtra("total",String.valueOf(extot));
                myintent.putExtra("correct",String.valueOf(correct));
                myintent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myintent);


            }
        }.start();
    }

}

