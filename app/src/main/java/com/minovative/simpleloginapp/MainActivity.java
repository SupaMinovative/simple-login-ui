package com.minovative.simpleloginapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
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
    private AppDatabase db;
    private UserDao userDao;

    List<User> userOnDb;

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

        db = AppDatabase.getInstance(this);
        userDao = db.userDao();

        signupBtn.setOnClickListener(v -> {

            loginTextView.setText("REGISTER");
            reEmailText.setVisibility(View.VISIBLE);
            rePassText.setVisibility(View.VISIBLE);
            regBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
            regOpt.setVisibility(View.GONE);
        });

        regBtn.setOnClickListener(v -> {

            userName = emailText.getText().toString().trim();
            reUserName = reEmailText.getText().toString().trim();
            password = passText.getText().toString().trim();
            rePassword = rePassText.getText().toString().trim();

            if (!userName.equals(reUserName)) {

                setErrorText(errorText,"Email doesn't match. Please try again.");
                setEmptyText(reEmailText);
                return;
            }

            if (!password.equals(rePassword)) {
                setErrorText(passErrorText,"Password doesn't match. Please try again.");
                setEmptyText(rePassText);
                return;

            } else if (password.isEmpty() && rePassword.isEmpty() || userName.isEmpty() && reUserName.isEmpty()) {
                setErrorText(errorText,"Field is required");
                return;
            }

            new Thread(( ) -> {
                userOnDb = userDao.getAllUser(userName,password);
                boolean exists = false;

                for (User user : userOnDb){
                    if (userName.equals(user.getEmail())) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {

                    runOnUiThread(( ) -> {
                        removeView();
                        setEmptyText(loginTextView);
                        setEmptyText(emailText);
                        setEmptyText(passText);
                        emailText.setText("");
                        passText.setText("");
                        regCon.setText("You already have an account. Please login.");
                        regBtn.setVisibility(View.GONE);
                        loginBtn.setVisibility(View.VISIBLE);
                    });
                }

                if (!exists) {
                    User newUser = new User(userName,password);
                    userDao.insertUser(newUser);
                }
            }).start();

                new android.os.Handler().postDelayed(( ) -> {

                    setEmptyText(errorText);

                },2500);

             if (userName.equals(reUserName) && Objects.equals(password,rePassword)) {
                removeView();
                emailText.setText("");
                passText.setText("");
                loginTextView.setText("WELCOME");
                regCon.setVisibility(View.VISIBLE);
                regCon.setText("Registered successfully! You can now log in to your account.");
                regBtn.setVisibility(View.GONE);
                loginBtn.setVisibility(View.VISIBLE);
            }
        });

        loginBtn.setOnClickListener(v -> {
            loginTextView.setText("LOGIN");
            emailText.setVisibility(View.VISIBLE);
            passText.setVisibility(View.VISIBLE);
            regCon.setVisibility(View.GONE);
            regBtn.setVisibility(View.GONE);

            String loginEmail = emailText.getText().toString().trim();
            String loginPass = passText.getText().toString().trim();


                if (loginEmail.isEmpty() && loginPass.isEmpty()) {
                    return;
                }

            new Thread(( ) -> {

                userOnDb = userDao.getAllUser(loginEmail,loginPass);

                runOnUiThread(( ) -> {
                    boolean isCorrect = false;

                    for (User user : userOnDb){

                        if (loginEmail.equals(user.getEmail()) && loginPass.equals(user.getPassword())) {
                            isCorrect = true;
                            break;
                        }
                    }
                    if (isCorrect) {

                        userName = String.valueOf(emailText.getText());
                        password = String.valueOf(passText.getText());
                        welcomePage.setVisibility(View.VISIBLE);
                        emailText.setVisibility(View.VISIBLE);
                        passText.setVisibility(View.VISIBLE);
                        setEmptyText(passErrorText);
                        passErrorText.setVisibility(View.GONE);
                        loginForm.setVisibility(View.GONE);
                        usernameShow.setText(userName);

                    }
                    else {
                        setErrorText(errorText,"Email or password is incorrect. Please try again.");
                    }
                });
            }).start();
        });
    }
    public void removeView() {
        emailText.setVisibility(View.GONE);
        reEmailText.setVisibility(View.GONE);
        passText.setVisibility(View.GONE);
        rePassText.setVisibility(View.GONE);
    }

    public void setErrorText(TextView t, String str) {

            t.setVisibility(View.VISIBLE);
            t.setText(str);

            new android.os.Handler().postDelayed(( ) -> {

                setEmptyText(t);

            },2000);
        }

    public void setEmptyText(TextView t) {
        t.setText("");
    }
}