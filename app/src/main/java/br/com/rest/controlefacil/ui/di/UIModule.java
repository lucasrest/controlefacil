package br.com.rest.controlefacil.ui.di;

import br.com.rest.controlefacil.domain.daos.UserDAO;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordContract;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordPresenter;
import br.com.rest.controlefacil.ui.activity.login.LoginContract;
import br.com.rest.controlefacil.ui.activity.login.LoginPresenter;
import br.com.rest.controlefacil.ui.activity.user.UserContract;
import br.com.rest.controlefacil.ui.activity.user.UserPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@Module
public class UIModule {

    private static ForgotPasswordContract.View forgotPasswordView;
    private static LoginContract.View loginView;
    private static UserContract.View userView;

    public UIModule(ForgotPasswordContract.View view) {
        this.forgotPasswordView = view;
    }

    public UIModule(LoginContract.View view) {
        this.loginView = view;
    }

    public UIModule(UserContract.View view) {
        this.userView = view;
    }

    @Provides
    ForgotPasswordContract.Presenter providesForgotPasswordPresenter() {
        return new ForgotPasswordPresenter(forgotPasswordView);
    }

    @Provides
    LoginContract.Presenter providesLoginPresenter() {
        return new LoginPresenter(loginView);
    }

    @Provides
    UserContract.Presenter providesUserPresenter() {
        return new UserPresenter(userView);
    }
}
