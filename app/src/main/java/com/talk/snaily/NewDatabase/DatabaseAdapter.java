package com.talk.snaily.NewDatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.talk.snaily.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 22/09/2016.
 */
public class DatabaseAdapter extends ArrayAdapter<Inquiry> {

    Context context;
    int layoutResourceId;
    List<Inquiry> inquiryList = new ArrayList<>();
    int i = 0;

    public DatabaseAdapter(Context context, int layoutResourceId, List<Inquiry> inquiryList) {
        super(context,layoutResourceId, inquiryList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.inquiryList = inquiryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        InquiryHolder holder = null;

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new InquiryHolder();
            holder.mTitle = (TextView) row.findViewById(R.id.inquiry_name);
            holder.mDate = (TextView) row.findViewById(R.id.inquiry_date_time);

            row.setTag(holder);

        }else{
            holder = (InquiryHolder)row.getTag();
        }

        Inquiry inquiry = inquiryList.get(position);
        holder.mTitle.setText("Name: " + inquiry.getmCourse());
        holder.mDate.setText("Date: " + inquiry.getmDate() + " , Time: " + inquiry.getmTime());

        return row;
    }

    static class InquiryHolder{
        TextView mTitle,mDate;
    }
}
