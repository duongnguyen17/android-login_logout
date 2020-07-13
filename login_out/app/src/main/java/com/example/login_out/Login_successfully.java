package com.example.login_out;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class Login_successfully extends Activity {
    private Button btlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successfully);
        btlogout = (Button) findViewById(R.id.btLogout);
        btlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Login_successfully.this, MainActivity.class);
                startActivity(login);
            }
        });

    }
}