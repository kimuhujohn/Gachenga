package com.example.gachenga.gachenga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextView mLink;
    private EditText mName;
    private EditText mEmail;
    private EditText mPass;
    private EditText mPass2;
    private Button mRegister;
    private AlertDialog.Builder mAlert;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mProgress = new ProgressDialog(this);
        mAlert = new AlertDialog.Builder(this);

        mName = (EditText)findViewById(R.id.txt_reg_name);
        mEmail = (EditText)findViewById(R.id.txt_reg_email);
        mPass = (EditText)findViewById(R.id.txt_reg_pass);
        mPass2 = (EditText)findViewById(R.id.txt_reg_pass2);

        mLink = (TextView)findViewById(R.id.txt_reg_log);
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        mRegister = (Button)findViewById(R.id.btn_reg);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPass.getText().toString().trim().equals(mPass2.getText().toString().trim())){
                    startRegistration();
                }
                else {
                    mAlert.setTitle(R.string.title_error);
                    mAlert.setMessage(R.string.mess_error);
                    mAlert.show();
                }
            }
        });
    }

    private void startRegistration() {
        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String pass = mPass.getText().toString().trim();

        mProgress.setMessage("Working...");

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            mProgress.show();
            TempDatabase database = new TempDatabase(name,email,pass);
            database.setEmail(email);
            database.setName(name);
            database.setPassword(pass);
            mProgress.dismiss();
            Toast.makeText(getApplicationContext(),R.string.reg_success,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        } else {
            mAlert.setTitle(R.string.title_error);
            mAlert.setMessage(R.string.mess_empty_fields);
            mAlert.show();
        }
    }
}
