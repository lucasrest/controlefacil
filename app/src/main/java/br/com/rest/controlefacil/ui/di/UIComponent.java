package br.com.rest.controlefacil.ui.di;

import br.com.rest.controlefacil.AppComponent;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordActivity;
import br.com.rest.controlefacil.ui.activity.login.LoginActivity;
import br.com.rest.controlefacil.ui.activity.user.UserActivity;
import br.com.rest.controlefacil.util.di.scope.UtilScope;
import dagger.Component;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@UtilScope
@Component(dependencies = AppComponent.class, modules = UIModule.class)
public interface UIComponent {
    void inject(ForgotPasswordActivity forgotPasswordActivity);

    void inject(LoginActivity loginActivity);

    void inject(UserActivity userActivity);
}
