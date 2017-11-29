package br.com.rest.controlefacil.ui.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.di.component.DaggerUIComponent;
import br.com.rest.controlefacil.di.module.ui.UIModule;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import br.com.rest.controlefacil.domain.model.User;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordActivity;
import br.com.rest.controlefacil.ui.activity.main.MainActivity;
import br.com.rest.controlefacil.ui.activity.privacy_policies.PrivacyPoliciesActivity;
import br.com.rest.controlefacil.ui.activity.user.UserActivity;
import br.com.rest.controlefacil.util.Preferences;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @Inject
    Preferences preferences;
    @Inject
    HashMap<String, String> data;
    private LoginContract.View view;
    private Context context;
    private UserDAO userDAO;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        context = (Activity) view;
        DaggerUIComponent
                .builder()
                .appComponent(getAppComponent())
                .uIModule(new UIModule())
                .build()
                .inject(this);
    }

    @Override
    public void callForgotPassword() {
        context.startActivity(new Intent(context, ForgotPasswordActivity.class));
    }

    @Override
    public void callPrivacyPolicies() {
        context.startActivity(new Intent(context, PrivacyPoliciesActivity.class));
    }

    @Override
    public void callMainScreen() {
        context.startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void callNewAccount() {
        context.startActivity(new Intent(context, UserActivity.class));
        finish();
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void verifyIfUserIsLogged() {
        data = preferences.getData();
        if (data.containsKey(Preferences.KEY_EMAIL) &&
                data.get(Preferences.KEY_EMAIL) != null) {
            callMainScreen();
        }
    }

    @Override
    public void login(User user) {
        if (userDAO.login(user)) {
            preferences.save(user.getEmail());
            callMainScreen();
        } else {
            view.showToast(context.getString(R.string.toast_invalid_user_or_password));
        }
    }

    private void finish(){
        ((Activity) view).finish();
    }
}
