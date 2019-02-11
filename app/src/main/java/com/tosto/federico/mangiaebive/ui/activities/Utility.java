package com.tosto.federico.mangiaebive.ui.activities;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;


public class Utility extends  AppCompatActivity {

    static RecyclerView.LayoutManager layoutManager;

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
    public static RecyclerView.LayoutManager setLayoutManager(Context context,boolean layoutManagertipe ,int spancount) {
        if (layoutManagertipe){
            layoutManager = new LinearLayoutManager(context);
        }
        else{
            layoutManager = new GridLayoutManager(context, spancount);
        }
        return layoutManager;
    }
}
