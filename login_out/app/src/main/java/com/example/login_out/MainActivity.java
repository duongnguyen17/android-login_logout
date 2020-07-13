package com.example.login_out;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etemail, etpassword;
    private Button btsigin, btlogin;
    private CheckBox cbrememberme;
    private SharedPreferences loginPreferences;
    private Boolean remember;

    final String email="admin@gmail.com";
    final String password="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        loginPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);

        loadData();
    }



    private void connectView() {
        etemail=(EditText) findViewById(R.id.etEmail);
        etpassword=(EditText) findViewById(R.id.etPassword);
        btlogin=(Button) findViewById(R.id.btLogin);
        btsigin=(Button) findViewById(R.id.btSignin);
        cbrememberme=(CheckBox) findViewById(R.id.cbRemember);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        //loginPrefsEditor = loginPreferences.edit();
        remember=loginPreferences.getBoolean("remember", false);
        if(remember==true){
            etemail.setText(loginPreferences.getString("email",""));
            etpassword.setText(loginPreferences.getString("password", ""));
            cbrememberme.setChecked(true);
        }
        btlogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(cbrememberme.isChecked()){
                    saveData(etemail.getText().toString(), etpassword.getText().toString());
                }
                else
                    clearData();

                if(etemail.getText().toString().equals(email)&& etpassword.getText().toString().equals(password)){
                    Intent loginsuccessfully= new Intent(MainActivity.this, Login_successfully.class);
                    startActivity(loginsuccessfully);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login false", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void loadData() {
        if(loginPreferences.getBoolean(String.valueOf(remember),false)) {
            etemail.setText(loginPreferences.getString(email, ""));
            etpassword.setText(loginPreferences.getString(password, ""));
            cbrememberme.setChecked(true);
        }
        else
            cbrememberme.setChecked(false);
    }
    private void saveData(String email, String password) {
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString(this.email,email);
        editor.putString(this.password,password);
        editor.putBoolean(String.valueOf(this.remember),cbrememberme.isChecked());
        editor.commit();
    }

    private void clearData() {
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.clear();
        editor.commit();
    }

}