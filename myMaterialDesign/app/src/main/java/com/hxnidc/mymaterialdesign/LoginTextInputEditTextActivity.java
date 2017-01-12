package com.hxnidc.mymaterialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by on 2017/1/12 15:08
 * Author：yrg
 * Describe:
 */


public class LoginTextInputEditTextActivity extends AppCompatActivity {

    Button button;
    TextInputLayout til_username, til_password;
    TextInputEditText tie_login_username, tie_login_password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinput_edit);
        init();
    }


    private void init() {
        button = (Button) findViewById(R.id.button);
        til_username = (TextInputLayout) findViewById(R.id.til_username);
        til_password = (TextInputLayout) findViewById(R.id.til_password);
        tie_login_username = (TextInputEditText) findViewById(R.id.tie_login_username);
        tie_login_password = (TextInputEditText) findViewById(R.id.tie_login_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLoginInfo();


            }
        });

    }

    private void getLoginInfo() {

        String username = tie_login_username.getText().toString().trim();
        String password = tie_login_password.getText().toString().toString();

        tie_login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_username.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tie_login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_password.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (TextUtils.isEmpty(username)) {
            til_username.setError("用户名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            til_password.setError("密码不能为空!");
            return;
        }
        if (password.length() > 5 && username.length() > 5) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else if (password.length() <= 5) {
            til_password.setError("密码错误");
        } else if (username.length() <= 5) {
            til_username.setError("用户名错误");
        }


    }


}
