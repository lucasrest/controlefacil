package br.com.rest.controlefacil.di.component;

import br.com.rest.controlefacil.di.module.ui.FragmentModule;
import br.com.rest.controlefacil.di.module.ui.UIModule;
import br.com.rest.controlefacil.di.scope.UIScope;
import br.com.rest.controlefacil.ui.activity.category.CategoryActivity;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordActivity;
import br.com.rest.controlefacil.ui.activity.login.LoginActivity;
import br.com.rest.controlefacil.ui.activity.login.LoginPresenter;
import br.com.rest.controlefacil.ui.activity.release.ReleaseActivity;
import br.com.rest.controlefacil.ui.activity.user.UserActivity;
import br.com.rest.controlefacil.ui.activity.user.UserPresenter;
import br.com.rest.controlefacil.ui.fragment.category.CategoryFragment;
import br.com.rest.controlefacil.ui.fragment.category.TabFragment;
import dagger.Component;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@UIScope
@Component(dependencies = AppComponent.class,
        modules = {
                UIModule.class,
                FragmentModule.class
        }
)
public interface UIComponent {

    void inject(CategoryActivity activity);

    void inject(CategoryFragment categoryFragment);

    void inject(ForgotPasswordActivity activity);

    void inject(LoginActivity activity);

    void inject(ReleaseActivity activity);

    void inject(UserActivity activity);

    void inject(LoginPresenter presenter);

    void inject(UserPresenter presenter);

    void inject(TabFragment tabFragment);

}
