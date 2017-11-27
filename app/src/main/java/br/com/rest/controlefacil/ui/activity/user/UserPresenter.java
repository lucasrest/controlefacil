package br.com.rest.controlefacil.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.daos.UserDAO;
import br.com.rest.controlefacil.domain.models.User;
import br.com.rest.controlefacil.ui.activity.main.MainActivity;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View view;
    private Context context;
    private UserDAO userDAO;

    public UserPresenter(UserContract.View view){
        this.view = view;
        this.context = (Activity) view;
    }

    @Override
    public void register(User user) {
        if (!userDAO.verifyIfExistsUserByEmail( user.getEmail() )){
            userDAO.save(user);
            context.startActivity( new Intent( context, MainActivity.class ));
        }else {
            view.showToast(context.getString(R.string.toast_exists_email));
        }
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
