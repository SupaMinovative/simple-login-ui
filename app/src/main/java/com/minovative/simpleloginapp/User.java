package com.minovative.simpleloginapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"email"})
public class User {

    @NonNull
    @ColumnInfo(name ="email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;

    public User(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail( ) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword( ) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
