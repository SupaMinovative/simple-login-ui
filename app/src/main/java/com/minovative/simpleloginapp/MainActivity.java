package com.minovative.simpleloginapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passText;
    private EditText reEmailText;
    private EditText rePassText;
    private TextView loginTextView;
    private TextView errorText;
    private TextView passErrorText;
    private TextView regCon;
    private TextView signupBtn;
    private TextView usernameShow;
    private Button loginBtn;

    private Button regBtn;
    private LinearLayout regOpt;
    private LinearLayout loginForm;
    private LinearLayout welcomePage;
    private String userName;
    private String reUserName;
    private String password;
    private String rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loginTextView = findViewById(R.id.logInTextView);
        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passText);
        reEmailText = findViewById(R.id.reEmail);
        rePassText = findViewById(R.id.rePassword);
        errorText = findViewById(R.id.errorText);
        passErrorText = findViewById(R.id.passErrorText);
        regCon = findViewById(R.id.regCon);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signUpBtn);
        regBtn = findViewById(R.id.regisButton);
        regOpt = findViewById(R.id.regOpt);
        loginForm = findViewById(R.id.loginForm);
        welcomePage = findViewById(R.id.welcomePage);
        usernameShow = findViewById(R.id.usernameShow);

        signupBtn.setOnClickListener(v -> {
            loginTextView.setText("REGISTER");
            reEmailText.setVisibility(View.VISIBLE);
            rePassText.setVisibility(View.VISIBLE);
            regBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
            regOpt.setVisibility(View.GONE);

        });


        regBtn.setOnClickListener(v -> {

            if(emailText.getText().toString().trim()
                    .equals(reEmailText.getText().toString().trim())) {
                userName = String.valueOf(emailText.getText());
                reUserName = String.valueOf(reEmailText.getText());
            } else {
                errorText.setVisibility(View.VISIBLE);
                errorText.setText("Email doesn't match. Please try again.");

                new android.os.Handler().postDelayed(() -> {
                    errorText.setText("");
                }, 2500);
                reEmailText.setText("");
            }

                if (passText.getText().toString().trim().equals(rePassText.getText().toString().trim())) {
                    password = String.valueOf(passText.getText());
                    rePassword = String.valueOf(passText.getText());
                }   else {
                    passErrorText.setVisibility(View.VISIBLE);
                    passErrorText.setText("Password doesn't match. Please try again.");

                    new android.os.Handler().postDelayed(( ) -> {
                        passErrorText.setText("");
                    },2500);
                    rePassText.setText("");
                };
                new android.os.Handler().postDelayed(() -> {
                    errorText.setText("");
                }, 2500);

                if(userName.isEmpty() && reUserName.isEmpty()
                && password.isEmpty() && rePassword.isEmpty()) {
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Field is required");

                    new android.os.Handler().postDelayed(( ) -> {
                        errorText.setText("");
                    },2500);
                } else if (userName.equals(reUserName) && Objects.equals(password,rePassword)) {
                    Log.d("DEBUG", "username: " + userName + "password: " + password);
                        loginTextView.setText("WELCOME");
                        emailText.setVisibility(View.GONE);
                        reEmailText.setVisibility(View.GONE);
                        passText.setVisibility(View.GONE);
                        rePassText.setVisibility(View.GONE);
                        regCon.setVisibility(View.VISIBLE);
                        regCon.setText("You're already registered. Please log in to your account.");
                        regBtn.setVisibility(View.GONE);
                        loginBtn.setVisibility(View.VISIBLE);

                    }
        });

        loginBtn.setOnClickListener(v -> {

            loginTextView.setText("LOGIN");
            emailText.setVisibility(View.VISIBLE);
            passText.setVisibility(View.VISIBLE);
            passErrorText.setText("");
            passErrorText.setVisibility(View.GONE);
            userName = String.valueOf(emailText.getText());
            password = String.valueOf(passText.getText());
            loginForm.setVisibility(View.GONE);
            welcomePage.setVisibility(View.VISIBLE);
            usernameShow.setText(userName);

        });


    }
}