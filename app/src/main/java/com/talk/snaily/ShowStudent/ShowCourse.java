package com.talk.snaily.ShowStudent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.talk.snaily.JsonParser;
import com.talk.snaily.Pojo.StudentList;
import com.talk.snaily.Pojo.StudentShowList;
import com.talk.snaily.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 11/09/2016.
 */
public class ShowCourse extends AppCompatActivity{


    ListView listView;
    ProgressDialog progressDialog;

    int status=0;

    ArrayList<StudentShowList> arrayList =new ArrayList<>();
    StudentShowList studentShowList;


    JsonParser jsonParser = new JsonParser();


//    TextView sname, sid , sduration,sdescription,ssyllabus;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_list_all);

        listView = (ListView) findViewById(R.id.list_student);

        new ShowStudentCourse().execute();



    }

    class ShowStudentCourse extends AsyncTask<String ,String ,String>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(ShowCourse.this);

            progressDialog.setMessage("Loading Students data");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            HashMap <String , String> hashMap =new HashMap<>();

            hashMap.put("id","1");

            String url="http://kinbech.6te.net/ittraining_online/api/showpostlist";
            JSONObject jsonObject= jsonParser.performPostCI(url,hashMap);


            if(jsonObject== null){

                status=1;

            }
            else{
               try{
                   if(jsonObject.getString("status").equals("success")){

                       status=2;

                       JSONArray jsonArray=jsonObject.getJSONArray("data");


                       for(int i=0;i<jsonArray.length();i++){

                           JSONObject studentObject = jsonArray.getJSONObject(i);

                           String name= studentObject.getString("name");
                           String id= studentObject.getString("id");
                           String duration= studentObject.getString("duration");
                           String description= studentObject.getString("description");
                           String syllabus= studentObject.getString("syllabus");
                           String image_path= studentObject.getString("image_path");

                           studentShowList= new StudentShowList(name,id,duration,description,syllabus,image_path);

                           arrayList.add(studentShowList);

                       }


                   }
                   else{

                       status=3;
                   }

               }catch(JSONException e){

                  e.printStackTrace();

               }



            }



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (status==1){

                Toast.makeText(ShowCourse.this, " No internet connection", Toast.LENGTH_SHORT).show();


            }
            else if(status==2){
                Toast.makeText(ShowCourse.this, "Student List successfully updated", Toast.LENGTH_SHORT).show();
                ShowStudentAdapter showStudentAdapter= new ShowStudentAdapter(ShowCourse.this,arrayList);

                listView.setAdapter(showStudentAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        StudentShowList studentShowList=(StudentShowList) parent.getItemAtPosition(position);

                        Intent intent = new Intent(ShowCourse.this,ShowAllActivity.class);

                        intent.putExtra("student_data1",studentShowList);
                        startActivity(intent);

                    }
                });





            }
            else if(status==3){
                Toast.makeText(ShowCourse.this, "Error in updating", Toast.LENGTH_SHORT).show();





            }



        }
    }

}
