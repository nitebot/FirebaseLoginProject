package com.example.firebaseloginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
EditText usernm, pwd;
TextView forgtpwd , signup;
Button login;
String unm , pass;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernm = (EditText) findViewById(R.id.usernm);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);
        forgtpwd = (TextView) findViewById(R.id.forgetpwd);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser(); //in order to check if the user has already logged into the data base or not
        //if the user has loggend in already we need to send him to the other activity

        if(user != null)
        {
            finish();
            startActivity(new Intent(MainActivity.this , RegistrationActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unm = usernm.getText().toString();
                pass = pwd.getText().toString();

                loginClicked(unm,pass);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Signup.class));
            }
        });
        forgtpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Forgetpassword.class));
            }
        });

    }
     public void loginClicked(String mail , String pwd)
     {
         //call instance of firebaseauth
         firebaseAuth.signInWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful())
                 {
                     Toast.makeText(MainActivity.this,"Login Successful" , Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(MainActivity.this , SecondActivity.class));

                 }
                 else
                 {
                     Toast.makeText(MainActivity.this,"Login Unsuccessful" , Toast.LENGTH_SHORT).show();

                 }
             }
         });
     }
}