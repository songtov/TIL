package com.chihosong.my_web_app.model;


public class MyRequestObject {
    private String email;
    private String password;
    private String booleanValue;

    // Constructors
    public MyRequestObject() {}

    public MyRequestObject(String email, String password, String booleanValue) {
        this.email = email;
        this.password = password;
        this.booleanValue = booleanValue;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
}