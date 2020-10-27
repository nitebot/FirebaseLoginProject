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

public class Signup extends AppCompatActivity {
    EditText fname , lname , email , password , phno;
    TextView alreadysignin ;
    Button signIn;
    String fnm,lnm,mail,pwd,pno;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.password);
        phno = (EditText) findViewById(R.id.phoneno);
        alreadysignin = (TextView) findViewById(R.id.tw1);
        signIn = (Button) findViewById(R.id.submitbutton);

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(buttonClick())
               {
                   //code for firebase
                   String user_email = email.getText().toString().trim();
                   String pd = password.getText().toString().trim();
                   firebaseAuth.createUserWithEmailAndPassword(user_email,pd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(Signup.this , "Successful" , Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Toast.makeText(Signup.this , "UnSuccessful" , Toast.LENGTH_SHORT).show();

                           }
                       }
                   }) ;


               }
            }
        });


        alreadysignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this , MainActivity.class));
            }
        });

    }

    private Boolean buttonClick() {
        Boolean result = false;
        fnm = fname.getText().toString();
        lnm = lname.getText().toString();
        mail = email.getText().toString();
        pwd = password.getText().toString();
        pno = phno.getText().toString();

        if(fnm.isEmpty())
        {
            Toast.makeText(Signup.this , "Enter Your Name", Toast.LENGTH_SHORT).show();
        }
        else if(lnm.isEmpty())
        {
                                 Toast.makeText(Signup.this , "Enter Last Name", Toast.LENGTH_SHORT).show();
        }
        else if(mail.isEmpty())
        {
                             Toast.makeText(Signup.this , "Enter Yout Mail ID", Toast.LENGTH_SHORT).show();
        }
        else if(pwd.isEmpty())
        {
                              Toast.makeText(Signup.this , "Enter Your Password", Toast.LENGTH_SHORT).show();
        }
        else if(pno.isEmpty())
        {
                              Toast.makeText(Signup.this , "Enter Your Phone No", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;


    }
}