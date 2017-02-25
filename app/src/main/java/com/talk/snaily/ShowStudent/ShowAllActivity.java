package com.talk.snaily.ShowStudent;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.talk.snaily.Pojo.StudentShowList;
import com.talk.snaily.R;

/**
 * Created by Administrator on 11/09/2016.
 */
public class ShowAllActivity extends AppCompatActivity {

    TextView vName, vid , vduration,vdescription,vsyllabus;
    ImageView vimageView;

    StudentShowList studentShowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_list);

        new ShowStudentDetail().execute();


    }


    class ShowStudentDetail extends  AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {


            vName =(TextView) findViewById(R.id.show_name);
            vid=(TextView) findViewById(R.id.show_id);
            vduration =(TextView) findViewById(R.id.show_duration);
            vdescription =(TextView) findViewById(R.id.show_description);
            vsyllabus =(TextView) findViewById(R.id.show_syllabus);
            vimageView=(ImageView) findViewById(R.id.adp_image);

            studentShowList = (StudentShowList) getIntent().getSerializableExtra("student_data1");

            vName.setText(studentShowList.getD_name());
            vid.setText(studentShowList.getD_id());
            vduration.setText(studentShowList.getD_duration());
            vdescription.setText(studentShowList.getD_description());
            vsyllabus.setText(studentShowList.getD_syllabus());

            Glide.with(ShowAllActivity.this).load(studentShowList.getD_image()).into(vimageView);



            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);




        }
    }
}
