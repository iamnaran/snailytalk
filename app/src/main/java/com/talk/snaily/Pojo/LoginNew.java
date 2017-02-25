package com.talk.snaily.Pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 1/09/2016.
 */
public class LoginNew implements Serializable {

    String lid,lphone,lemail,laddress;

    public LoginNew(String lid, String lphone, String lemail, String laddress) {
        this.lid = lid;
        this.lphone = lphone;
        this.lemail = lemail;
        this.laddress = laddress;
    }

    public String getLid() {
        return lid;
    }

    public String getLphone() {
        return lphone;
    }

    public String getLemail() {
        return lemail;
    }

    public String getLaddress() {
        return laddress;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public void setLphone(String lphone) {
        this.lphone = lphone;
    }

    public void setLemail(String lemail) {
        this.lemail = lemail;
    }

    public void setLaddress(String laddress) {
        this.laddress = laddress;
    }
}
