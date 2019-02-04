package com.tosto.federico.mangiaebive.ui.activities;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;


public class Utility extends  AppCompatActivity {

    public static void showToast(Context context, String message){
        Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
    }
    public static boolean verifyEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean verifyPassword(String password, int minpasswordlenght){
        return password.length() >= minpasswordlenght;
    }

    public static boolean verifyPhoneNumber(String phonenumber){
        return PhoneNumberUtils.isGlobalPhoneNumber(phonenumber);
    }
}
