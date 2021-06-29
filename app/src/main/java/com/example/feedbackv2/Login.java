package com.example.feedbackv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {
EditText mEmail, mPassword;
Button mLogin;
TextView mRegister, mGuest;
FirebaseAuth fAuth;
DatabaseReference mDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        fAuth=FirebaseAuth.getInstance();
        mEmail=findViewById(R.id.edittext_email);
        mPassword=findViewById(R.id.edittext_password);
        mRegister=(TextView)findViewById(R.id.registertext);
        mLogin=findViewById(R.id.login);
        mGuest=(TextView)findViewById(R.id.guest);

        mGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
                finish();
            }

        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required!");
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("Password must be at least 6 characters long!.");
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in Succesfully!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Profile.class));

                        }else{
                            Toast.makeText(Login.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    });


            }
        });
    }
}