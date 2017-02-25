package com.talk.snaily.ShowStudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.talk.snaily.Pojo.StudentShowList;
import com.talk.snaily.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/09/2016.
 */
public class ShowStudentAdapter extends ArrayAdapter<StudentShowList> {

    StudentShowList studentShowList;
    Context context;

    ImageView imageView;

    TextView mName, mid;

    public ShowStudentAdapter(Context context, ArrayList<StudentShowList> students) {

        super(context, 0, students);
        this.context = context;
        this.studentShowList = studentShowList;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public StudentShowList getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(StudentShowList item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        studentShowList = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.show_one_adapter, parent, false);
        }
            mName = (TextView) convertView.findViewById(R.id.adp_name);
            mid = (TextView) convertView.findViewById(R.id.adp_id);
            imageView= (ImageView) convertView.findViewById(R.id.adp_image);


            mName.setText(studentShowList.getD_name());
            mid.setText(studentShowList.getD_id());

        Glide.with(context).load(studentShowList.getD_image()).into(imageView);

            return convertView;


        }
    }


