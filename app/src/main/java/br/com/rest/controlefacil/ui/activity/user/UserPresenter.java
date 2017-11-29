package br.com.rest.controlefacil.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.di.component.DaggerUIComponent;
import br.com.rest.controlefacil.di.module.ui.UIModule;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import br.com.rest.controlefacil.domain.model.User;
import br.com.rest.controlefacil.ui.activity.login.LoginActivity;
import br.com.rest.controlefacil.ui.activity.main.MainActivity;
import br.com.rest.controlefacil.util.Preferences;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public class UserPresenter implements UserContract.Presenter {

    @Inject
    Preferences preferences;
    private UserContract.View view;
    private Context context;
    private UserDAO userDAO;

    public UserPresenter(UserContract.View view) {
        this.view = view;
        this.context = (Activity) view;
        DaggerUIComponent
                .builder()
                .appComponent(getAppComponent())
                .uIModule(new UIModule())
                .build()
                .inject(this);
    }

    @Override
    public void callLoginScreen() {
        context.startActivity(new Intent(context, LoginActivity.class));
        finish();
    }

    @Override
    public void register(User user) {
        if (!userDAO.verifyIfExistsUserByEmail(user.getEmail())) {
            userDAO.save(user);
            preferences.save(user.getEmail());
            context.startActivity(new Intent(context, MainActivity.class));
            finish();
        } else {
            view.showToast(context.getString(R.string.toast_exists_email));
        }
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private void finish() {
        ((Activity) view).finish();
    }
}
