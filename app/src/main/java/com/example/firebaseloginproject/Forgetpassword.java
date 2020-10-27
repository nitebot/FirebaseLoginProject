package com.example.firebaseloginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpassword extends AppCompatActivity {

    EditText emailid;
    Button reset;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        emailid = (EditText) findViewById(R.id.mailid);
        reset = (Button) findViewById(R.id.resetButton);
        firebaseAuth  = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = emailid.getText().toString();
                if(id.isEmpty())
                {
                    Toast.makeText(Forgetpassword.this , "Please Enter Your mail ID" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(emailid.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful())
                           {
                               Toast.makeText(Forgetpassword.this, "Password Reset Email Send" , Toast.LENGTH_SHORT)
                                       .show();
                               finish();
                               startActivity(new Intent(Forgetpassword.this , MainActivity.class));
                           }
                           else
                               {
                                   Toast.makeText(Forgetpassword.this, "Email Provided is Not Registered" , Toast.LENGTH_SHORT)
                                           .show();
                               }
                        }
                    });
                }
            }
        });
    }
}