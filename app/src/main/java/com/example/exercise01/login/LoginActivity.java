package com.example.exercise01.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exercise01.R;
import com.example.exercise01.base.BaseActivity;
import com.example.exercise01.data.Login;
import com.example.exercise01.data.LoginDataSource;
import com.example.exercise01.data.LoginRepository;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private LoginPresenter mLoginPresenter;
    private LoginRepository mLoginDataSource;

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;

    ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginDataSource = new LoginRepository();

        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                mLoginPresenter.callLogin(new Login(username, password));
            }
        });
    }

    public int getLayout() {
        return R.layout.activity_login;
    }

    public void init() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        mLoginPresenter = new LoginPresenter(this);
    }


    public void showLoading() {
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
    }


    public void hideLoading() {
        pd.hide();
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
    }


    public void loginSuccess(String msg) {

    }


    public void loginFailure(String errMsg) {

    }


    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
