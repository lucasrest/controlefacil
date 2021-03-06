package br.com.rest.controlefacil.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import javax.inject.Named;

import br.com.rest.controlefacil.di.module.AppModule;
import br.com.rest.controlefacil.di.module.domain.DAOModule;
import br.com.rest.controlefacil.di.module.domain.EventModule;
import br.com.rest.controlefacil.di.module.domain.ModelModule;
import br.com.rest.controlefacil.di.module.util.UtilModule;
import br.com.rest.controlefacil.di.scope.AppScope;
import br.com.rest.controlefacil.domain.dao.BaseDAO;
import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.dao.PeriodDAO;
import br.com.rest.controlefacil.domain.dao.ReleaseDAO;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import br.com.rest.controlefacil.domain.event.CategorySelectedEvent;
import br.com.rest.controlefacil.domain.event.SelectedIconEvent;
import br.com.rest.controlefacil.domain.model.Category;
import br.com.rest.controlefacil.domain.model.Period;
import br.com.rest.controlefacil.domain.model.Release;
import br.com.rest.controlefacil.domain.model.User;
import br.com.rest.controlefacil.ui.adapter.CategoryAdapter;
import br.com.rest.controlefacil.ui.adapter.CategoryIconsAdapter;
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

    Category category();

    List<Category> categories();

    Period period();

    Release release();

    User user();

    CategoryDAO categoryDAO();

    PeriodDAO periodDAO();

    ReleaseDAO releaseDAO();

    UserDAO userDAO();

    HashMap<String, String> hashMap();

    Preferences preferences();

    Realm realm();

    @Named("ExpensesCategoryChangeEvent")
    CategoryChangeEvent expensesCategoryChangeEvent();

    @Named("RecipesCategoryChangeEvent")
    CategoryChangeEvent recipesCategoryChangeEvent();

    SelectedIconEvent selectedIconEvent();

    CategorySelectedEvent categorySelectedEvent();

    EventBus providesEventBus();

    void inject(BaseDAO baseDAO);

    void inject(CategoryDAO categoryDAO);

    void inject(CategoryAdapter categoryAdapter);

    void inject(CategoryIconsAdapter categoryIconsAdapter);

    void inject(Preferences preferences);

    void inject(UserDAO userDAO);
}
