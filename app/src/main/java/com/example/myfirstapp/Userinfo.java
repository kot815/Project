package com.example.myfirstapp;

public class Userinfo {

    private String username;
    private String password;
    private String user_id;
    private String nickname;
public static Userinfo sUserInfo;

    public static Userinfo getsUserInfo() {
        return sUserInfo;
    }

    public static void setsUserInfo(Userinfo sUserInfo) {
        Userinfo.sUserInfo = sUserInfo;
    }

    public Userinfo(String user_id, String username, String password, String nickname) {

        this.username = username;
        this.password=password;
        this.user_id=user_id;
        this.nickname=nickname;

    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


        }
