package com.tosto.federico.mangiaebive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final static int minpasswordlenght=7;
    final static String KEY_MAIL = "email";

    Button Loginbutton;
    Button Registerbutton;
    EditText Emailedit;
    EditText Passwordedit;
    LinearLayout Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_main);

        Loginbutton = findViewById(R.id.bottone_login);
        Registerbutton =findViewById(R.id.bottone_registrati);
        Emailedit =findViewById(R.id.email_Hint);
        Passwordedit =findViewById(R.id.password_hint);
        Layout = findViewById(R.id.layout);

        Loginbutton.setOnClickListener(this);
        Registerbutton.setOnClickListener(this);

        Log.i("MainActivity","Main Activity Created");

    }

    private boolean doLogin(){

        String email = Emailedit.getText().toString();
        String password = Passwordedit.getText().toString();

        if (!verifyEmail(email)){
            showToast(R.string.email_Errata);
            return false;
        }
        if (!verifyPassword(password)){
            showToast(R.string.Password_Errata);
            return false;
        }
        showToast(R.string.login_ok);
        return true;

    }
    private void showToast(int id){
        Toast.makeText(this, getString(id),Toast.LENGTH_SHORT).show();
    }
    private boolean verifyEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean verifyPassword(String password){
        if (password.length()<minpasswordlenght){
            return false;
        }else return true;
    }

    @Override
    public void onClick(View view){

        if (view.getId() == R.id.bottone_login){
            if (doLogin()){
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        }

        else if (view.getId()== R.id.bottone_registrati){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);

        }

    }


}
