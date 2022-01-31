package com.example.myloginfirebase.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myloginfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email,pass;
    private Button button;
    private TextView signin;
    private ActionBar actionBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        // calling the action bar
        actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        Window window = SignupActivity.this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(SignupActivity.this, R.color.reds));
        ColorDrawable colorDrawable
                = new ColorDrawable(ContextCompat.getColor(SignupActivity.this, R.color.reds));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        email = findViewById(R.id.IdRegEmail);
        pass = findViewById(R.id.IdRegPass);
        button = findViewById(R.id.IdRegButton);
        signin = findViewById(R.id.IdRegSignin);

        signin.setOnClickListener(this);
        button.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.IdRegSignin){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.IdRegButton){
            if(email.getText().toString().equals("")){
                email.setError("Email field is required!");
                email.requestFocus();
//                Toast.makeText(MainActivity.this,"Email and Password required",Toast.LENGTH_LONG).show();
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                email.setError("Invalid email address");
                email.requestFocus();
            }else if(pass.getText().toString().equals("")){
                pass.setError("Password field is required!");
                pass.requestFocus();
            }
            else {
                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(SignupActivity.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(SignupActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }

        }
    }
}