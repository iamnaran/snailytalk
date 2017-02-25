package com.talk.snaily.Pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 11/09/2016.
 */
public class StudentShowList implements Serializable{

    String d_name, d_id , d_duration,d_description,d_syllabus,d_image;

    public StudentShowList(String d_name, String d_id, String d_duration, String d_description, String d_syllabus,String d_image) {
        this.d_name = d_name;
        this.d_id = d_id;
        this.d_duration = d_duration;
        this.d_description = d_description;
        this.d_syllabus = d_syllabus;
        this.d_image=d_image;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_duration() {
        return d_duration;
    }

    public void setD_duration(String d_duration) {
        this.d_duration = d_duration;
    }

    public String getD_description() {
        return d_description;
    }

    public void setD_description(String d_description) {
        this.d_description = d_description;
    }

    public String getD_syllabus() {
        return d_syllabus;
    }

    public String getD_image() {
        return d_image;
    }

    public void setD_image(String d_image) {
        this.d_image = d_image;
    }

    public void setD_syllabus(String d_syllabus) {
        this.d_syllabus = d_syllabus;
    }
}
