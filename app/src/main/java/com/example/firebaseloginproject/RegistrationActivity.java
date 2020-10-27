package com.example.firebaseloginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    EditText fname, lname, add, dist, state, phno, dt;
    Button submit;

    String fnm, lnm, address, dis, st, pno;

    RadioGroup radioGroup; //for radio button
    CheckBox check; //for checkbox

    DatePickerDialog datePickerDialog;  //datepicker
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        add = (EditText) findViewById(R.id.address);
        dist = (EditText) findViewById(R.id.district);
        state = (EditText) findViewById(R.id.state);
        phno = (EditText) findViewById(R.id.phoneno);
        submit = (Button) findViewById(R.id.submitbutton);
        dt = (EditText) findViewById(R.id.date);
        radioGroup = (RadioGroup) findViewById(R.id.radiogrup);
        check = (CheckBox) findViewById(R.id.checkbx);
        //to upload image
        firebaseAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclick();
            }
        });
        //code for date picker
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance(); // to access the calender and its methods
                final int year = calendar.get(Calendar.YEAR); // to get year
                final int month = calendar.get(Calendar.MONTH); // to get month
                final int day = calendar.get(Calendar.DAY_OF_MONTH); //to get day


                datePickerDialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        //to set the date in the edit text
                        dt.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                //to show the calander dialoge
                datePickerDialog.show();
            }
        });

    }

    public void btnclick() {
        fnm = fname.getText().toString();
        lnm = lname.getText().toString();
        address = add.getText().toString();
        st = state.getText().toString();
        dis = dist.getText().toString();
        pno = phno.getText().toString();
        //Radio button
        int isselected = radioGroup.getCheckedRadioButtonId();

        if (fnm.equals("")) {
            Toast.makeText(RegistrationActivity.this, "First Name Is Empty.. Please Enter Some Text",
                    Toast.LENGTH_SHORT).show();
        } else if (lnm.equals("")) {
            {
                Toast.makeText(RegistrationActivity.this, "Last Name Is Empty.. Please Enter Some Text",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (add.equals("")) {
            {
                Toast.makeText(RegistrationActivity.this, "Address Field Is Empty.. Please Enter Your address",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (dis.equals("")) {
            {
                Toast.makeText(RegistrationActivity.this, "District field is empty ",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (st.equals("")) {
            {
                Toast.makeText(RegistrationActivity.this, "Please enter your State",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (pno.equals("")) {
            {
                Toast.makeText(RegistrationActivity.this, "Phone No Is Empty.. Please Enter your Phone Number",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (dt.equals("")) {
            Toast.makeText(RegistrationActivity.this, "Mention The date", Toast.LENGTH_SHORT).show();
        } else if (isselected == -1) {
            Toast.makeText(RegistrationActivity.this, "select your gender", Toast.LENGTH_SHORT).show();

        } else if (!check.isChecked()) {
            Toast.makeText(RegistrationActivity.this, "Please click on the check box", Toast.LENGTH_SHORT).show();
        }
//        else if(imageView.equals(null)) doent work for image view
//        {
//            Toast.makeText(RegistrationActivity.this,"Please upload a photo",Toast.LENGTH_SHORT).show();
//
//        }
        else {
            //code for db connection

            sendUserData();
            Toast.makeText(RegistrationActivity.this, "Resitration Successful",
                    Toast.LENGTH_SHORT).show();

        }
    }

    public void sendUserData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(fnm,lnm,address,st,dis,pno);
        myRef.setValue(userProfile);
    }
}