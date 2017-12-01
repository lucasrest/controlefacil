package br.com.rest.controlefacil.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import javax.inject.Named;

import br.com.rest.controlefacil.di.module.AppModule;
import br.com.rest.controlefacil.di.module.domain.DAOModule;
import br.com.rest.controlefacil.di.module.domain.EventModule;
import br.com.rest.controlefacil.di.module.domain.ModelModule;
import br.com.rest.controlefacil.di.module.util.UtilModule;
import br.com.rest.controlefacil.di.scope.AppScope;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import br.com.rest.controlefacil.domain.model.User;
import br.com.rest.controlefacil.util.Preferences;
import dagger.Component;
import io.realm.Realm;

/**
 * Created by LUCAS RODRIGUES on 24/11/2017.
 */

@AppScope
@Component(
        modules = {
                AppModule.class,
                DAOModule.class,
                EventModule.class,
                ModelModule.class,
                UtilModule.class
        }
)
public interface AppComponent {

    Application application();

    Context context();

    SharedPreferences sharedPreferences();

    SharedPreferences.Editor sharePreferencesEditor();

    User user();

    UserDAO userDAO();

    HashMap<String, String> hashMap();

    Preferences preferences();

    Realm realm();

    @Named("ExpensesCategoryChangeEvent")
    CategoryChangeEvent expensesCategoryChangeEvent();

    @Named("RecipesCategoryChangeEvent")
    CategoryChangeEvent recipesCategoryChangeEvent();

    EventBus providesEventBus();

    void inject(Preferences preferences);

    void inject(UserDAO userDAO);
}
