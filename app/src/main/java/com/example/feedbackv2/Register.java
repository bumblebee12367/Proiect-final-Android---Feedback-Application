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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {
EditText mName, mEmail, mPassword, mConfirmPass;
User user;
DatabaseReference mDataBase;
Button mRegisterBtn;
TextView mLoginText;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.edittext_name);
        mEmail = findViewById(R.id.edittext_email);
        mPassword = findViewById(R.id.edittext_password);
        mConfirmPass = findViewById(R.id.edittext_confirmpassword);
        mRegisterBtn = findViewById(R.id.registerbtn);
        mLoginText=(TextView)findViewById(R.id.logintext);
        mDataBase= FirebaseDatabase.getInstance("https://feedback-9a043-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("users");
        user=new User();
        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
        fAuth = FirebaseAuth.getInstance();

        /*if(fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();}*/


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString().trim();
                String email= mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String confirmPass=mConfirmPass.getText().toString().trim();

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

                if(!password.equals(confirmPass))
                {
                    mConfirmPass.setError("Password doesn't match!");
                }

                user.setName(name);
                user.setPassword(password);
                user.setSubject("");
                user.setEmail(email);
                user.setAge(-1);

                String key = mDataBase.push().getKey();

                //verifici ca key != null

                mDataBase.child(key).setValue(user);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User created succesfully!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}