package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class fbtest extends AppCompatActivity {

    Button sign_out_button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbtest);

        sign_out_button = findViewById(R.id.mySignout);
        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(fbtest.this, MainActivity.class));
            }
        });
    }
}