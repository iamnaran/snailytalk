
package com.talk.snaily.NewDatabase;

import java.io.Serializable;

public class Inquiry implements Serializable{
    String mID,mCourse,mDate,mTime,mQuestion;

    public Inquiry() {

    }

    public Inquiry(String mID, String mCourse, String mDate, String mTime, String mQuestion) {
        this.mID = mID;
        this.mCourse = mCourse;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mQuestion = mQuestion;
    }

    public Inquiry(String mCourse, String mDate, String mTime, String mQuestion) {
        this.mCourse = mCourse;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mQuestion = mQuestion;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmCourse() {
        return mCourse;
    }

    public void setmCourse(String mCourse) {
        this.mCourse = mCourse;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }
}
