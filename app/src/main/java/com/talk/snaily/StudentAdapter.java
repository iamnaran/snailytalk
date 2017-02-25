package com.talk.snaily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.talk.snaily.Pojo.StudentList;

import java.util.ArrayList;


public class StudentAdapter extends ArrayAdapter<StudentList> {

    StudentList studentList;
    Context context;
    TextView mName,mAddress;

    public StudentAdapter(Context context, ArrayList<StudentList> students) {
        super(context, 0, students);
        this.context = context;
        this.studentList= studentList;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public StudentList getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(StudentList item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StudentList studentList = getItem(position);

        if(convertView== null){

            convertView= LayoutInflater.from(getContext()).inflate(R.layout.student_adapter,parent,false);

        }
        mName = (TextView) convertView.findViewById(R.id.adp_name);
        mAddress = (TextView) convertView.findViewById(R.id.adp_address);

        mName.setText(studentList.getName());
        mAddress.setText(studentList.getAddress());

        return convertView;
    }

}

