package com.example.feedbackv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
TextView mYourName, mFeedback, mTeacherEmail;
RatingBar mBar;
Button mSubmit;
Feedback overallFeedback;
DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        mYourName=findViewById(R.id.edittext_name);
        mTeacherEmail=findViewById(R.id.edittext_email);
        mBar=findViewById(R.id.ratingbar);
        mFeedback=findViewById(R.id.edittext_Feedback);
        mSubmit=findViewById(R.id.submit);
        overallFeedback=new Feedback();
        mDataBase= FirebaseDatabase.getInstance("https://feedback-9a043-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("feedbacks");

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = mTeacherEmail.getText().toString().trim();
                String from=mYourName.getText().toString().trim();
                String feedback=mFeedback.getText().toString().trim();
                String stars=String.valueOf(mBar.getRating());

                overallFeedback.setFeedback(feedback);
                overallFeedback.setFrom(from);
                overallFeedback.setTo(to);
                overallFeedback.setRating(Float.parseFloat(stars));

                String key = mDataBase.push().getKey();
                mDataBase.child(key).setValue(overallFeedback);

                Toast.makeText(MainActivity.this,"Feedback sent succesfully!",Toast.LENGTH_SHORT).show();
                mFeedback.setText("");
                mTeacherEmail.setText("");
                mYourName.setText("");

            }
        });
    }
}