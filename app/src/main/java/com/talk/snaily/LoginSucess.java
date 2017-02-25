package com.talk.snaily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2/09/2016.
 */
public class LoginSucess extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_successful);

        Button mButton = (Button) findViewById(R.id.login_success);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginSucess.this, MainActivity.class);
                startActivity(intent);


            }
        });



    }
}
