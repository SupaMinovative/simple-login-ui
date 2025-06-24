package com.minovative.simpleloginapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);
    @Query("SELECT * FROM user WHERE email =:email AND password =:password")
    List<User> getAllUser(String email,String password);
}
