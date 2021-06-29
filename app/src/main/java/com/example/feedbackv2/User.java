package com.example.feedbackv2;

public class User {
    private String name;
    private String email;
    private int age;
    private String subject;
    private String password;


    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        this.name = "";
        this.email = "";
        this.age = -1;
        this.subject = "";
        this.password = "";
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.age = -1;
        this.subject = "";
        this.password = password;
    }
}
