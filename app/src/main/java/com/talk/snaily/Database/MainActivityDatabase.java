package com.talk.snaily.Database;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.talk.snaily.R;

/**
 * Created by WHITEWALKERS on 9/19/2016.
 */
public class MainActivityDatabase extends AppCompatActivity {
    EditText editName , editSurname,editMarks ,editID;

    TextView textID;

    Button btnAddData ;
    Button btnviewAll;
    Button btnUpdate;
    Button btnDelete;
    Button btnCount;

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.eName);
        editSurname = (EditText) findViewById(R.id.eSurname);
        editMarks = (EditText) findViewById(R.id.eMarks);

        textID = (TextView) findViewById(R.id.tID);
        editID = (EditText) findViewById(R.id.eID);

        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_view);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        btnCount = (Button) findViewById(R.id.button_count);

        DeleteData();
        UpdateData();
        AddData();
        viewAll();
        countQuery();

    }


    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDatabase.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure" + "\n" + "You want to delete from Database?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (editID.length() == 0) {

                            editID.setBackgroundColor(Color.RED);
                            dialog.dismiss();
                        }
                        else {

                            Integer deletedRows = myDb.deleteData(editID.getText().toString());
                            if(deletedRows > 0){
                                Toast.makeText(MainActivityDatabase.this, "Data Deleted", Toast.LENGTH_LONG).show();
                                editID.setText("");
                                editName.setText("");
                                editSurname.setText("");
                                editMarks.setText("");
                                editID.setBackgroundColor(Color.WHITE);

                            } else
                                Toast.makeText(MainActivityDatabase.this, "Error in Data Deleting", Toast.LENGTH_LONG).show();


                        }

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }
    public void UpdateData(){

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDatabase.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure" + "\n" + "You want to update Database?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (editID.length() == 0) {

                            editID.setBackgroundColor(Color.RED);
                            dialog.dismiss();
                        } else {


                            boolean isUpdated = myDb.updateData(editID.getText().toString(), editName.getText().toString(),
                                    editSurname.getText().toString(), editMarks.getText().toString());




                            if (isUpdated == true) {
                                Toast.makeText(MainActivityDatabase.this, "Data Updated", Toast.LENGTH_LONG).show();
                                editID.setText("");
                                editName.setText("");
                                editSurname.setText("");
                                editMarks.setText("");
                                editID.setBackgroundColor(Color.WHITE);
                            } else
                                Toast.makeText(MainActivityDatabase.this, "Data not Updated", Toast.LENGTH_LONG).show();

                        }

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


                }
        });
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(MainActivityDatabase.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivityDatabase.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll(){
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res = myDb.getAllData();
                if(res.getCount()==0){
                    //Show Message
                    showMessage("Error !!","No Data Found");
                    return;
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("ID:"+res.getString(0)+"\n");
                        buffer.append("Name:"+res.getString(1)+"\n");
                        buffer.append("Surname:"+res.getString(2)+"\n");
                        buffer.append("Marks:"+res.getString(3)+"\n\n");
                    }
                    //Show All Data
                    showMessage("Data",buffer.toString());

                }
            }
        });
    }

    public void countQuery(){

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = myDb.getMyInquiryCount();

                Toast.makeText(MainActivityDatabase.this, "No of Queries"+ count, Toast.LENGTH_SHORT).show();

            }
        });


    }



    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
