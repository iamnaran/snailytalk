package com.talk.snaily;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.talk.snaily.Pojo.StudentList;

/**
 * Created by Administrator on 9/09/2016.
 */
public class StudentDetailActivity extends AppCompatActivity {
    TextView vEmail, vPassword , vname,vid,vaddress;
    StudentList studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details);

        new ShowDetail().execute();


    }

    class ShowDetail extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            vEmail =(TextView) findViewById(R.id.adp_email);
            vPassword =(TextView) findViewById(R.id.adp_password);
            vid =(TextView) findViewById(R.id.adp_id);
            vname =(TextView) findViewById(R.id.adp_name);
            vaddress =(TextView) findViewById(R.id.adp_address);

            studentList= (StudentList) getIntent().getSerializableExtra("student_data");

           vEmail.setText(studentList.getName());
           vPassword.setText(studentList.getPhone());
           vid.setText(studentList.getId());
           vname.setText(studentList.getName());
           vaddress.setText(studentList.getAddress());


        }
    }
}
