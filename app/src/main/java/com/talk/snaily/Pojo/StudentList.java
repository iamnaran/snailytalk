package com.talk.snaily.Pojo;

import java.io.Serializable;


public class StudentList implements Serializable {

    String id, phone, email, address, name;

    public StudentList(String id, String phone, String email, String address, String name) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
