package br.com.rest.controlefacil;

import android.app.Application;
import android.content.SharedPreferences;

import br.com.rest.controlefacil.domain.di.DAOModule;
import br.com.rest.controlefacil.domain.daos.UserDAO;
import br.com.rest.controlefacil.domain.di.DomainComponent;
import br.com.rest.controlefacil.domain.di.ModelModule;
import br.com.rest.controlefacil.domain.models.User;
import br.com.rest.controlefacil.ui.activity.login.LoginPresenter;
import br.com.rest.controlefacil.util.Preferences;
import br.com.rest.controlefacil.util.di.scope.AppScope;
import br.com.rest.controlefacil.util.di.UtilComponent;
import dagger.Component;

/**
 * Created by LUCAS RODRIGUES on 24/11/2017.
 */

@AppScope
@Component(
        dependencies = {
                DomainComponent.class,
                UtilComponent.class
        },
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    Application application();

    SharedPreferences sharedPreferences();

    SharedPreferences.Editor sharePreferencesEditor();

    void inject(Preferences preferences);
    void inject(LoginPresenter presenter);
}
