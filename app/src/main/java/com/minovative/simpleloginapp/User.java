package com.minovative.simpleloginapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"email"})
public class User {

    @ColumnInfo(name ="email")
    private String email;
    @ColumnInfo(name = "password")
    private int password;

    public User(String email,int password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail( ) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword( ) {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
