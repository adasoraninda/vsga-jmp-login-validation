package com.adasoranina.loginvalidation.model;

public class User {
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String school;
    private String address;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String email, String fullName, String school, String address) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.school = school;
        this.address = address;
    }

    public String getLoginFileFormat() {
        return this.userName + ";" +
                this.password;
    }

    public String getRegisterFileFormat() {
        return this.userName + ";"
                + this.password + ";"
                + this.email + ";"
                + this.fullName + ";"
                + this.school + ";"
                + this.address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
