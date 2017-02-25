package com.talk.snaily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.talk.snaily.Pojo.LoginNew;

public class DisplayActivity extends AppCompatActivity {
    TextView vEmail, vPassword , vphone,vid,vaddress;
    LoginNew loginNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_login);

        vEmail= (TextView) findViewById(R.id.act_email_v);
        vPassword= (TextView) findViewById(R.id.act_pass_v);
        vphone= (TextView) findViewById(R.id.act_phone_v);
        vid= (TextView) findViewById(R.id.act_id_v);
        vaddress= (TextView) findViewById(R.id.act_address_v);


        loginNew=(LoginNew) getIntent().getSerializableExtra("login_data");

//        String d_email= getIntent().getStringExtra("snail_email");
//        String d_password= getIntent().getStringExtra("snail_password");
//
//
//        String d_phone= getIntent().getStringExtra("snail_phone");
//        String d_address= getIntent().getStringExtra("snail_address");
//        String d_id= getIntent().getStringExtra("snail_id");
//
//        vEmail.setText(d_email);
//        vPassword.setText(d_password);
//
//
//        vphone.setText(d_phone);
//        vaddress.setText(d_address);
//        vid.setText(d_id);


        vid.setText(loginNew.getLid());
        vaddress.setText(loginNew.getLaddress());
        vphone.setText(loginNew.getLphone());
        vEmail.setText(loginNew.getLemail());

    }
}
