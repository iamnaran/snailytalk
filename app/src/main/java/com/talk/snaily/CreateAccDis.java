package com.talk.snaily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class CreateAccDis extends AppCompatActivity {

    TextView c_Fname,c_Lname, c_Email,c_Phone ,c_Password ,c_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_acc_display);

        c_Fname= (TextView) findViewById(R.id.e_FName);
        c_Lname= (TextView) findViewById(R.id.e_LName);
        c_Email= (TextView) findViewById(R.id.e_email);
        c_Password= (TextView) findViewById(R.id.e_pass);
        c_user= (TextView) findViewById(R.id.e_username);
        c_Phone= (TextView) findViewById(R.id.e_phone);


        String Fname= getIntent().getStringExtra("r_FName");
        String Lname= getIntent().getStringExtra("r_LName");
        String Email= getIntent().getStringExtra("r_Email");
        String Password= getIntent().getStringExtra("r_Pass");
        String Phone= getIntent().getStringExtra("r_Phone");
        String User= getIntent().getStringExtra("r_user");

        c_Fname.setText(Fname);
        c_Lname.setText(Lname);
        c_Email.setText(Email);
        c_Password.setText(Password);
        c_Phone.setText(Phone);
        c_user.setText(User);

    }
}
