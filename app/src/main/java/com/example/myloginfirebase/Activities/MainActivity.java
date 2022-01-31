package com.example.myloginfirebase.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email,pass;
    private TextView signup;
    private Button button;
    private ActionBar actionBar;
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        Window window = MainActivity.this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.reds));

        email = findViewById(R.id.IdLogEmail);
        pass = findViewById(R.id.IdLogPass);
        button = findViewById(R.id.IdLogSubmit);
        signup = findViewById(R.id.IdLogSignup);

        signup.setOnClickListener(this);
        button.setOnClickListener(this);

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            currentUser.reload();
//        }
//    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.IdLogSignup){
            Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
           startActivity(intent);
        }else if(view.getId() == R.id.IdLogSubmit){
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
                mAuth.signInWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(),DeshboardActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this,"Email or password did not match !",Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }

        }
    }
}