package com.nooralhealth.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nooralhealth.R;
import com.nooralhealth.activity.login.Login;

public class GmailProfile extends AppCompatActivity {

    ImageView imageView;
    TextView textName, textEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_profile);

        mAuth = FirebaseAuth.getInstance();

        imageView = findViewById(R.id.profile);
        textName = findViewById(R.id.name);
        textEmail = findViewById(R.id.email);


        FirebaseUser user = mAuth.getCurrentUser();

        Glide.with(this)
                .load(user.getPhotoUrl())
                .into(imageView);

        textName.setText(user.getDisplayName());
        textEmail.setText(user.getEmail());
    }
    @Override
    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, Login.class));
        }
    }
    }
