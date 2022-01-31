package com.example.myloginfirebase.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myloginfirebase.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Email,pass;
    private TextView signup;
    private Button button;
    private ActionBar actionBar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Window window = MainActivity.this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.reds));

        Email = findViewById(R.id.IdLogEmail);
        pass = findViewById(R.id.IdLogPass);
        button = findViewById(R.id.IdLogSubmit);
        signup = findViewById(R.id.IdLogSignup);

        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.IdLogSignup){
            Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
           startActivity(intent);
        }else {

        }
    }
}