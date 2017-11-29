package br.com.rest.controlefacil.ui.activity.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import br.com.rest.controlefacil.domain.model.User;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.edt_email)
    AppCompatEditText edtEmail;
    @BindView(R.id.edt_password)
    AppCompatEditText edtPassword;
    @Inject
    LoginContract.Presenter presenter;
    @Inject
    UserDAO userDAO;
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        component(this).inject(this);
        presenter.setUserDAO(userDAO);
        presenter.verifyIfUserIsLogged();
    }

    @OnClick(R.id.tv_forgot_password)
    public void callForgotPassword() {
        presenter.callForgotPassword();
    }

    @OnClick(R.id.tv_privacy_policies)
    public void callPrivacyPolicies() {
        presenter.callPrivacyPolicies();
    }

    @OnClick(R.id.tv_new_account)
    public void callNewAccount() {
        presenter.callNewAccount();

    }

    @OnClick(R.id.btn_login)
    public void login() {
        if (validadeForm()) {
            user.setEmail(edtEmail.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            presenter.login(user);
        } else {
            showToast(getString(R.string.toast_fields_not_filled_login));
        }
    }

    @Override
    public boolean validadeForm() {
        boolean valid = true;
        if (edtEmail.getText().toString().isEmpty()) {
            valid = false;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            valid = false;
        }
        return valid;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}
