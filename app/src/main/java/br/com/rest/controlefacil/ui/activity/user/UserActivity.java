package br.com.rest.controlefacil.ui.activity.user;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import br.com.rest.controlefacil.domain.model.User;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class UserActivity extends BaseActivity implements UserContract.View {

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.edt_email)
    AppCompatEditText edtEmail;
    @BindView(R.id.edt_password)
    AppCompatEditText edtPassword;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    UserContract.Presenter presenter;
    @Inject
    User user;
    @Inject
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bindViews();
        component(this).inject(this);
        presenter.setUserDAO(userDAO);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.user_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btn_register)
    public void register() {
        if (validadeForm()) {
            user.setEmail(edtEmail.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            presenter.register(user);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.callLoginScreen();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean validadeForm() {
        boolean valid = true;
        if (edtEmail.getText().toString().isEmpty()) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError(getString(R.string.error_empty_email));
            valid = false;
        } else {
            tilEmail.setErrorEnabled(false);
        }
        if (edtPassword.getText().toString().isEmpty()) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError(getString(R.string.error_empty_password));
            valid = false;
        } else {
            tilPassword.setErrorEnabled(false);
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

    @Override
    public void onBackPressed() {
        presenter.callLoginScreen();
    }
}
