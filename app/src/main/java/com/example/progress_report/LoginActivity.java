package com.example.progress_report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    //Declaring the FirebaseAuth for google sign in
    private FirebaseAuth firebaseAuth;
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient googleSignInClient;
    private SignInButton signInButton;

    //loading bar for signing in
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //login data handlers
        signInButton = findViewById(R.id.signin);
        Button loginButton = findViewById(R.id.loginButton);
        final EditText userID = findViewById(R.id.studentId);
        final EditText userPassword = findViewById(R.id.studentPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(handleGoogleLogin);*/


        //on click of a local login here
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                final String user_ID = userID.getText() + "";
                final String pass = userPassword.getText() + "";



                //Code for checking student authentication
               final  DatabaseReference stuRef = database.getReference("student").
                        child(user_ID);
             //  final  DatabaseReference courRef = database.getReference("student").child(user_ID).child("course");



                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                        }else{
                            String verifyPass =  ""+dataSnapshot.child("pass").getValue();
                           String name=""+dataSnapshot.child("name").getValue();
                           String email=""+dataSnapshot.child("email").getValue();
                           String courses=""+dataSnapshot.child("course").getValue();
                           if(pass.equals(verifyPass)){
                               Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), StudentActivity.class);

                               intent.putExtra("Id",user_ID);
                               intent.putExtra("name",name);
                               intent.putExtra("email",email);
                               intent.putExtra("course",courses);

                               startActivity(intent);
                           }else {
                           }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", databaseError.getMessage()); //Don't ignore errors!
                    }
                };
                stuRef.addListenerForSingleValueEvent(eventListener);




                //Code for checking Instructor authentication
                final DatabaseReference insRef = database.getReference("instructor").child(user_ID);
                ValueEventListener instructorEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                        }else{
                            String verifyPass =  ""+dataSnapshot.child("pass").getValue();
                            String email=""+dataSnapshot.child("email").getValue();
                            String courses=""+dataSnapshot.child("course").getValue();

                            if(pass.equals(verifyPass)){
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                                intent.putExtra("email",email);
                                intent.putExtra("course",courses);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "Password invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", databaseError.getMessage()); //Don't ignore errors!
                    }
                };
                insRef.addListenerForSingleValueEvent(instructorEventListener);



                //Code for checking Developer authentication
                DatabaseReference devRef = database.getReference("developer");
                ValueEventListener devEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                        }else{
                            String verifyPass =  ""+dataSnapshot.child("pass").getValue();
                            if(pass.equals(verifyPass)){
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DeveloperActivity.class);
                                startActivity(intent);
                            }else {
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", databaseError.getMessage()); //Don't ignore errors!
                    }
                };
                devRef.addListenerForSingleValueEvent(devEventListener);


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), LoginHandler.class);
            startActivity(intent);
        }
    }


    /**
     * This method decides whether the user is authenticated and if he exist in the system or not
     */
    private void signIn() {
        Intent singInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(singInIntent, RC_SIGN_IN);
    }

    /**
     * This listner will use sign in method
     */
    private View.OnClickListener handleGoogleLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signIn();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), LoginHandler.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Sorry authentication failed ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void checkCredentials() {
    }
}
