package br.com.rest.controlefacil.ui.activity.user;

import br.com.rest.controlefacil.domain.daos.UserDAO;
import br.com.rest.controlefacil.domain.models.User;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public interface UserContract {

    interface View{
        boolean validadeForm();
        void showToast(String message);
    }
    interface Presenter{
        void register(User user);
        void setUserDAO(UserDAO userDAO);
    }
}
