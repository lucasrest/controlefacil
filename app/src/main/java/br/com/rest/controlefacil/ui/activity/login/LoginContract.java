package br.com.rest.controlefacil.ui.activity.login;

import br.com.rest.controlefacil.domain.daos.UserDAO;
import br.com.rest.controlefacil.domain.models.User;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public interface LoginContract {

    interface View {
        boolean validadeForm();
        void showToast(String message);
    }

    interface Presenter {
        void callForgotPassword();
        void callPrivacyPolicies();
        void callMainScreen();
        void callNewAccount();
        void login(User user);
        void setUserDAO(UserDAO userDAO);
        void verifyIfUserIsLogged();
    }

}
