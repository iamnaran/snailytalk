package com.talk.snaily.NewDatabase;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.talk.snaily.R;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Siddhant on 9/18/2016.
 */
public class FormSpinnerDatepickerExample extends AppCompatActivity {
    String[] coursedata = {"PHP","Android Studio","Java","javascript","HTML5/CSS3","Arduino"};
    Calendar calendar;
    EditText mDate,mTime,mQuestion;
    int mYear, mMonth, mDay, mHours, mMinutes;
    Button mSendButton , mShowButton;
    String vCourseName,vDate,vTime,vQuestion;
    Spinner coursespinner;
    DBHandler dbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dbHandler = new DBHandler(FormSpinnerDatepickerExample.this);
        coursespinner = (Spinner) findViewById(R.id.form_course_spinner);

        ArrayAdapter<String> spinnerAdapter = new  ArrayAdapter<String>(FormSpinnerDatepickerExample.this,android.R.layout.simple_spinner_item,coursedata);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursespinner.setAdapter(spinnerAdapter);

        calendar = Calendar.getInstance();

        mDate = (EditText) findViewById(R.id.form_course_date);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYear=calendar.get(Calendar.YEAR);
                mMonth=calendar.get(Calendar.MONTH);
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog mDatePicker = new DatePickerDialog(FormSpinnerDatepickerExample.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mDate.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                    }
                },mYear,mMonth,mDay);
                mDatePicker.show();
            }
        });

        mTime=(EditText) findViewById(R.id.form_course_time);
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHours=calendar.get(Calendar.HOUR_OF_DAY);
                mMinutes=calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(FormSpinnerDatepickerExample.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        mTime.setText(hourOfDay+":"+minute);
                    }
                },mHours,mMinutes,false);
                mTimePicker.show();
            }
        });

        mQuestion=(EditText) findViewById(R.id.form_course_question);

        mSendButton = (Button) findViewById(R.id.form_course_button);
        mShowButton = (Button) findViewById(R.id.form_course_button_show);
//        dbHandler.addMyInquiry(new Inquiry("Android","2016","11","sdfsadfasdf"));
//        dbHandler.addMyInquiry(new Inquiry("Java","2016","11","sdfsadfasdf"));
//        dbHandler.addMyInquiry(new Inquiry("PHP","2016","11","sdfsadfasdf"));
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vCourseName=coursespinner.getSelectedItem().toString();
                vDate=mDate.getText().toString();
                vTime=mTime.getText().toString();
                vQuestion=mQuestion.getText().toString();
                if(vCourseName.length()<1||
                        vDate.length()<1||
                        vTime.length()<1){
                    Toast.makeText(FormSpinnerDatepickerExample.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
//                    if(getIntent().getExtras()==null){
                        dbHandler.addMyInquiry(new Inquiry(vCourseName,vDate,vTime,vQuestion));

                        Toast.makeText(FormSpinnerDatepickerExample.this, "New Inquiry Added.", Toast.LENGTH_SHORT).show();

                        //startActivity(new Intent(FormSpinnerDatepickerExample.this,));
//                    }
//                    else{
////                        dbHandler.updateContact(new Inquiry(inqID,courseName,vDate,vTime,vQuestion));
////                        Toast.makeText(FormSpinnerDatepickerExample.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
////                        startActivity(new Intent(FormSpinnerDatepickerExample.this,InquiryListActivity.class));
//                    }
                }
                //finish();
                }
            });

        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormSpinnerDatepickerExample.this,InquiryListView.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });


        }//OnCreate()
    }

