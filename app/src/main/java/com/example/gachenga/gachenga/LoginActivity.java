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

public class LoginActivity extends AppCompatActivity {

    private TextView mLink;
    private EditText mEmail;
    private EditText mPass;
    private Button mLogin;
    private AlertDialog.Builder mAlert;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAlert = new AlertDialog.Builder(this);
        mProgress = new ProgressDialog(this);

        mEmail = (EditText)findViewById(R.id.txt_log_email);
        mPass = (EditText)findViewById(R.id.txt_log_pass);

        mLink = (TextView)findViewById(R.id.txt_log_reg);
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        mLogin = (Button)findViewById(R.id.btn_log);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
    }

    private void startLogin() {
        String email = mEmail.getText().toString().trim();
        String pass = mPass.getText().toString().trim();

        mProgress.setMessage("Working...");

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            mProgress.show();
            TempDatabase database = new TempDatabase();
            if (email.equals(database.getEmail()) && pass.equals(database.getPassword())){
                Toast.makeText(getApplicationContext(),R.string.log_success,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            } else {
                mProgress.dismiss();
                mAlert.setTitle(R.string.title_error);
                mAlert.setMessage(R.string.mess_wrong_cred);
                mAlert.show();
            }
        } else {
            mAlert.setTitle(R.string.title_error);
            mAlert.setMessage(R.string.mess_empty_fields);
            mAlert.show();
        }
    }
}
