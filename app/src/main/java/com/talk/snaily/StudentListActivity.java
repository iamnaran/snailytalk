package com.talk.snaily;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.talk.snaily.Pojo.StudentList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentListActivity extends AppCompatActivity {
    ListView listView;
    ProgressDialog progressDialog;
    JsonParser jsonParser =new JsonParser();
    int status=0;

    ArrayList<StudentList> arrayList =new ArrayList<>();
    StudentList studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_list_activity);

        listView = (ListView) findViewById(R.id.list_student);

        new ListStudents().execute();

    }

    class ListStudents extends AsyncTask<String, String , String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new ProgressDialog(StudentListActivity.this);

            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            HashMap<String ,String> hashmap= new HashMap<>();

            hashmap.put("id","1");

            String url="http://kinbech.6te.net/ittraining_online/api/showstudentlist";

            JSONObject jsonObject = jsonParser.performPostCI(url,hashmap);

           if(jsonObject== null) {
               status = 1;
           }
               else{


                   try {
                       if(jsonObject.getString("status").equals("success")){

                           status=2;


                           JSONArray jsonArray = jsonObject.getJSONArray("data");

                           for(int i=0;i<jsonArray.length();i++){

                               JSONObject studentObject = jsonArray.getJSONObject(i);


                               String name= studentObject.getString("name");
                               String id= studentObject.getString("id");
                               String address= studentObject.getString("address");
                               String email= studentObject.getString("email");
                               String phone= studentObject.getString("phone");

                               studentList = new StudentList(name,id,address,email,phone);

                               arrayList.add(studentList);

                           }
                       }
                       else{
                           status=3;

                       }

                   } catch (JSONException e) {
                       e.printStackTrace();

                   }
               }

       return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if(status==1){

                Toast.makeText(StudentListActivity.this, " No internet connection", Toast.LENGTH_SHORT).show();

            }
            else if(status==2){

                Toast.makeText(StudentListActivity.this, "List successfully updated", Toast.LENGTH_SHORT).show();

                StudentAdapter studentAdapter = new StudentAdapter(StudentListActivity.this, arrayList);

                listView.setAdapter(studentAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        StudentList studentList= (StudentList) parent.getItemAtPosition(position);


                        Intent intent = new Intent(StudentListActivity.this,StudentDetailActivity.class);
                        intent.putExtra("student_data",studentList);
                        startActivity(intent);

                    }
                });




            }
            else if(status==3){
                Toast.makeText(StudentListActivity.this, "Error in updating", Toast.LENGTH_SHORT).show();

            }


        }
    }

}
