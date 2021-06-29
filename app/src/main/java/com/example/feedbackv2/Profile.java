package com.example.feedbackv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

public class Profile extends AppCompatActivity {
TextView mName, mEmail,mAge,mSubject;
FirebaseAuth fAuth;
Button mLogout;
DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile);
        fAuth=FirebaseAuth.getInstance();
        mAge=findViewById(R.id.profile_age);
        mSubject=findViewById(R.id.profile_subject);
        mName=findViewById(R.id.profile_name);
        mEmail=findViewById(R.id.profile_email);
        mLogout=findViewById(R.id.logout);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser fUser= fAuth.getCurrentUser();
        mEmail.setText(fUser.getEmail());
        mName.setText(fUser.getDisplayName());


        //FirebaseUser fUser=FirebaseAuth.getInstance(FirebaseApp.getInstance("https://feedback-9a043-default-rtdb.europe-west1.firebasedatabase.app/")).getCurrentUser();
        //String key = mDataBase.push().getKey();

        //mDataBase = FirebaseDatabase.getInstance("https://feedback-9a043-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child(fUser.getUid());
       // mDataBase.addValueEventListener(new ValueEventListener() {
         //   @Override
          //  public void onDataChange(@NonNull DataSnapshot snapshot) {
           //     String name = snapshot.child("name").getValue().toString();
          //      String email = snapshot.child("email").getValue().toString();
          //      String age = snapshot.child("age").getValue().toString();
         //       String subject = snapshot.child("subject").getValue().toString();

         //       mName.setText(name);
           //     mEmail.setText(email);
           //     mAge.setText(age);
           //     mSubject.setText(subject);
           // }

         //   @Override
         //   public void onCancelled(@NonNull DatabaseError error) {

          //  }
      //  });
    }


    }