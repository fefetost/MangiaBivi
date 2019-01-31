package com.tosto.federico.mangiaebive.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tosto.federico.mangiaebive.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    final static int minpasswordlenght=7;
    Button Registerbutton;
    EditText Emailedit;
    EditText Passwordedit;
    EditText Phonedit;
    private boolean verifyEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void enableregister(){

        String email = Emailedit.getText().toString();
        String password = Passwordedit.getText().toString();
        String phonenumber = Phonedit.getText().toString();

        if (!verifyEmail(email)){
            return;
        }
        if (password.length()<minpasswordlenght){
            return;
        }
        if (!PhoneNumberUtils.isGlobalPhoneNumber(phonenumber)){
            return;
        }
        Registerbutton.setEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.register_activity);

        Registerbutton = findViewById(R.id.reg_btn);
        Emailedit = findViewById(R.id.reg_mail);
        Passwordedit = findViewById(R.id.reg_pass);
        Phonedit = findViewById(R.id.phone_number);

        Registerbutton.setVisibility(View.VISIBLE);
        Registerbutton.setOnClickListener(this);
        Emailedit.addTextChangedListener(this);
        Passwordedit.addTextChangedListener(this);
        Phonedit.addTextChangedListener(this);
        Registerbutton.setEnabled(false);

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        enableregister();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        enableregister();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        enableregister();
    }
}
