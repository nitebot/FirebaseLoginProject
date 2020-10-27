package com.example.firebaseloginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivy extends AppCompatActivity {

    TextView pfname , plname , padrs , pDist , pstate , pPhNo;
    FirebaseDatabase  firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activy);

        pfname = (TextView) findViewById(R.id.pfstname);
        plname = (TextView) findViewById(R.id.plstname);
        padrs = (TextView) findViewById(R.id.paddrs);
        pDist = (TextView) findViewById(R.id.pdist);
        pstate = (TextView) findViewById(R.id.pst);
        pPhNo = (TextView) findViewById(R.id.pPno);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //for database Reference
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        //To access the data base and retrive the data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                pfname.setText(userProfile.getFirstname());
                plname.setText(userProfile.getLastname());
                padrs.setText(userProfile.getAddress());
                pDist.setText(userProfile.getDistrict());
                pstate.setText(userProfile.getState());
                pPhNo.setText(userProfile.getPhoneno());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivy.this , error.getCode() , Toast.LENGTH_SHORT).show();

            }
        });

    }
}