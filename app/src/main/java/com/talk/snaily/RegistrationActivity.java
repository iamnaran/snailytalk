package com.talk.snaily;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;


public class RegistrationActivity extends AppCompatActivity {

    EditText mFname, mEmail,mPass,mPhone,mrePass,mAddress;

    EditText mDateDisplay;
    int syear;
    int smonth;
    int sday;
    static final int DATE_DIALOG_ID = 999;

    ImageView mDate_image;
    String  vFname,vEmail,vPass,vPhone,vAddress,vDate;
    JsonParser jsonParser= new JsonParser();
    int status=0;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registration);

        Button mCreate_Acc_Button = (Button) findViewById(R.id.reg_create_acc_button);

        mFname= (EditText)findViewById(R.id.reg_fname);
        mrePass= (EditText) findViewById(R.id.reg_re_pass_word);
        mEmail= (EditText) findViewById(R.id.reg_email);
        mPass= (EditText) findViewById(R.id.reg_pass_word);
        mPhone= (EditText) findViewById(R.id.reg_phone_number);
        mPhone= (EditText) findViewById(R.id.reg_phone_number);
        mAddress= (EditText) findViewById(R.id.reg_address);
        mDateDisplay =  (EditText) findViewById(R.id.reg_date);
        mDate_image = (ImageView) findViewById(R.id.image_date);



        mDate_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                syear = c.get(Calendar.YEAR);
                smonth = c.get(Calendar.MONTH);
                sday = c.get(Calendar.DAY_OF_MONTH);

                showDialog(DATE_DIALOG_ID);


            }

        }
        );

//        vDateDisplay = DateFormat.getDateInstance().format(new Date());
//        mDate.setText(vDateDisplay);


//        mUser= (EditText) findViewById(R.id.reg_user_name);

//        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.country_arrays, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);


        mCreate_Acc_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vFname = mFname.getText().toString();
//                vLname=mLname.getText().toString();
                vEmail = mEmail.getText().toString();
                vPass = mPass.getText().toString();
                vPhone = mPhone.getText().toString();
                vAddress = mAddress.getText().toString();


//                vUser= mUser.getText().toString();


                if (vFname.length() == 0 && vEmail.length() == 0 && vPass.length() == 0 && vAddress.length() == 0
                        && vPhone.length() == 0) {

                    Toast.makeText(RegistrationActivity.this, "Please! Fill up all fields", Toast.LENGTH_SHORT).show();


                }else if(!mPass.getText().toString().equals(mrePass.getText().toString())) {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();
                    alertDialog.setTitle("Error!");
                    alertDialog.setMessage("Password didnt matches");
                    alertDialog.setButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    alertDialog.show();
                }  else {

//                    Intent intent = new Intent(RegistrationActivity.this,CreateAccDis.class);


//
//                    intent.putExtra("r_FName", vFname);
//                    intent.putExtra("r_LName", vLname);
//                    intent.putExtra("r_Email", vEmail);
//                    intent.putExtra("r_Pass", vPass);
//                    intent.putExtra("r_Phone", vPhone);
//                    intent.putExtra("r_User", vUser);
//
//
//                    startActivity(intent);

                    new CheckRegistration().execute();


                }


            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        syear, smonth,sday);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            syear= year;
            smonth=monthOfYear;
            sday=dayOfMonth;

            mDateDisplay.setText(new StringBuilder().append(smonth +1).append("-")
                    .append(sday).append("-").append(syear)
                    .append(" "));

        }
    };





    class CheckRegistration extends AsyncTask {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RegistrationActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading.. Please Wait");
            progressDialog.show();



        }

        @Override
        protected Object doInBackground(Object[] params) {

            HashMap<String ,String> hashMap = new HashMap<>();

            hashMap.put("email",vEmail);
            hashMap.put("password",vPass);
            hashMap.put("phone",vPhone);
            hashMap.put("address",vAddress);
            hashMap.put("name", vFname);

            String url="http://kinbech.6te.net/ittraining_online/api/register";

            JSONObject jsonObject = jsonParser.performPostCI(url,hashMap);

            if(jsonObject == null){

                status=1;

            }
            else{
                try {
                    if(jsonObject.getString("status").equals("success")){

                        status=2;

                    }
                    else{

                        status=3;

                    }

                }catch( JSONException e){

                    e.printStackTrace();
                }


            }





            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            progressDialog.dismiss();

            if(status==1){

                Toast.makeText(RegistrationActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();

            }
            else if(status==2){
                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(RegistrationActivity.this,LoginSucess.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
            else if (status==3){

                Toast.makeText(RegistrationActivity.this, "Invalid Username & password ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this,LoginSucess.class);
            }


        }
    }





}
