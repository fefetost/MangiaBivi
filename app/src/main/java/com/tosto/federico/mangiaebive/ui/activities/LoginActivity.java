package com.tosto.federico.mangiaebive.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tosto.federico.mangiaebive.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final static int minpasswordlenght=7;

    Button Loginbutton;
    Button Registerbutton;
    EditText Emailedit;
    EditText Passwordedit;
    LinearLayout Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_login);

        Loginbutton = findViewById(R.id.bottone_login);
        Registerbutton =findViewById(R.id.bottone_registrati);
        Emailedit =findViewById(R.id.email_Hint);
        Passwordedit =findViewById(R.id.password_hint);
        Layout = findViewById(R.id.layout);

        Loginbutton.setOnClickListener(this);
        Registerbutton.setOnClickListener(this);

        Log.i("LoginActivity","Main Activity Created");

    }

    private boolean doLogin(){

        String email = Emailedit.getText().toString();
        String password = Passwordedit.getText().toString();

        if (!Utility.verifyEmail(email)){
            Utility.showToast(this,getString(R.string.email_Errata));
            return false;
        }
        if (!(Utility.verifyPassword(password,minpasswordlenght))){
            Utility.showToast(this,getString(R.string.Password_Errata));
            return false;
        }
        Utility.showToast(this,getString(R.string.login_ok));
        return true;

    }

    @Override
    public void onClick(View view){

        if (view.getId() == R.id.bottone_login){
            if (doLogin()){
                Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        }

        else if (view.getId()== R.id.bottone_registrati){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if(item.getItemId()==R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }
        else if(item.getItemId()==R.id.shop_menu){
            startActivity(new Intent(this, ShopActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
