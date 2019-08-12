package com.example.employeeattendance;

/**
 * Created by THUNDER on 12/28/2016.
 */

import android.content.SharedPreferences;


import java.util.HashMap;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
// Shared Preferences reference
  SharedPreferences pref;

// Editor reference for Shared preferences
Editor editor;

          // Context
  Context _context;

// Shared pref mode
int PRIVATE_MODE = 0;

// Sharedpref file name
 private static final String PREFER_NAME = "AttendancePref";

// All Shared Preferences Keys
         private static final String IS_USER_LOGIN = "IsUserLoggedIn";

            // User name (make variable public to access from outside)
            public static final String KEY_PASSWORD = "password";

            // Email address (make variable public to access from outside)
            public static final String KEY_EMAIL = "email";

        // Constructor
        public SessionManager(Context context){
        this._context = context;
       pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
       editor = pref.edit();
       }
  public void createUserLoginSession(String email, String password){
        editor.putBoolean(IS_USER_LOGIN, true);
       editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_EMAIL, email);

        editor.commit();
    }
public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
        Intent i = new Intent(_context, LoginActivity.class);
         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

        return false;}
        return false;
        }
  public HashMap<String, String> getUserDetails(){
       HashMap<String, String> user = new HashMap<String, String>();
       user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
      return user;
       }
    public void logoutUser(){

        editor.clear();
        editor.commit();
Intent i = new Intent(_context, LoginActivity.class);
 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
        }


          public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
       }
    }