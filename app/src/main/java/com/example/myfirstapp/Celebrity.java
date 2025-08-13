package com.example.myfirstapp;

public class Celebrity {
    private String password;
    private String age;
    private String phone;
    private String name;

    public Celebrity(String name,String password, String age, String phone) {
        this.password = password;
        this.age= age;
        this.phone = phone;
        this.name=name;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }
    public String getName() {
        return name;
    }
}
