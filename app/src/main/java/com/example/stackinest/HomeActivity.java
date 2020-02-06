package com.example.stackinest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    Button logout;
    FirebaseAuth mAuth;
    TextView username;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("HomePage");
        username=(TextView)findViewById(R.id.username);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //logout=(Button)findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                if(firebaseAuth.getCurrentUser()==null){

                    startActivity(new Intent(HomeActivity.this,SignIn.class));
                }
            }
        };


        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("LOGGED", "FirebaseUser: " + user);

        if (user != null) {
            username.setText("Welcome, " + user.getDisplayName());



           // MainActivity.LoggedIn_User_Email =user.getDisplayName();




        }

       /* logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

            }
        });*/

    }
}
