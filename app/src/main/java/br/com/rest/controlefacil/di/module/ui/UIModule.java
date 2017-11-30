package br.com.rest.controlefacil.di.module.ui;

import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.activity.category.CategoryContract;
import br.com.rest.controlefacil.ui.activity.category.CategoryPresenter;
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

    private static CategoryContract.View categoryView;
    private static ForgotPasswordContract.View forgotPasswordView;
    private static LoginContract.View loginView;
    private static UserContract.View userView;

    public UIModule() {
    }

    public UIModule(BaseActivity baseActivity) {
        if (baseActivity instanceof LoginContract.View)
            this.loginView = (LoginContract.View) baseActivity;
        else if (baseActivity instanceof UserContract.View)
            this.userView = (UserContract.View) baseActivity;
        else if (baseActivity instanceof ForgotPasswordContract.View)
            this.forgotPasswordView = (ForgotPasswordContract.View) baseActivity;
        else if (baseActivity instanceof CategoryContract.View)
            this.categoryView = (CategoryContract.View) baseActivity;
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

    @Provides
    CategoryContract.Presenter providesCategoryPresenter(){
        return new CategoryPresenter( categoryView );
    }
}
