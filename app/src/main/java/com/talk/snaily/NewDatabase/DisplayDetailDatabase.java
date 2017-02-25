package com.talk.snaily.NewDatabase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.talk.snaily.R;

/**
 * Created by Administrator on 22/09/2016.
 */
public class DisplayDetailDatabase extends AppCompatActivity{

    EditText textCourse,textDate,textTime,textQuestion ,textID;
    Inquiry inquiry;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_database_list);

        dbHandler = new DBHandler(this);
        
        textID = (EditText) findViewById(R.id.database_id);
        textCourse = (EditText) findViewById(R.id.database_course);
        textDate = (EditText) findViewById(R.id.database_date);
        textTime = (EditText) findViewById(R.id.database_time);
        textQuestion = (EditText) findViewById(R.id.database_question);

        inquiry = (Inquiry) getIntent().getSerializableExtra("database_data");

        textID.setText(inquiry.getmID());
        textCourse.setText(inquiry.getmCourse());
        textDate.setText(inquiry.getmDate());
        textTime.setText(inquiry.getmTime());
        textQuestion.setText(inquiry.getmQuestion());


//            if(textID.requestFocus()) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }
//            if(textCourse.requestFocus()) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }
//            if(textDate.requestFocus()) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }
//            if(textTime.requestFocus()) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }
//            if(textQuestion.requestFocus()) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(DisplayDetailDatabase.this);
//
//            builder.setTitle("Confirm");
//            builder.setMessage("Are you sure" + "\n" + "You want to update Database?");
//
//            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//                public void onClick(DialogInterface dialog, int which) {
            int note_id_edit = Integer.valueOf(inquiry.getmID());

            boolean isUpdated = dbHandler.updateData(new Inquiry(textID.getText().toString(),
                    textCourse.getText().toString(),
                    textDate.getText().toString(),
                    textQuestion.getText().toString()),note_id_edit);

                if (isUpdated == true) {
                    Toast.makeText(DisplayDetailDatabase.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(DisplayDetailDatabase.this, "Data not Updated", Toast.LENGTH_LONG).show();

            return true;
        }
        if (id == R.id.action_delete) {

            int note_id = Integer.valueOf(inquiry.getmID());

            dbHandler.deleteInquiry(note_id);
            Toast.makeText(DisplayDetailDatabase.this,"Id "+note_id+" is deleted from Database", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DisplayDetailDatabase.this,InquiryListView.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}