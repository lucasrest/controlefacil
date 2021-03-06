package br.com.rest.controlefacil;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import br.com.rest.controlefacil.di.component.AppComponent;
import br.com.rest.controlefacil.di.component.DaggerAppComponent;
import br.com.rest.controlefacil.di.module.AppModule;
import br.com.rest.controlefacil.di.module.domain.DAOModule;
import br.com.rest.controlefacil.di.module.domain.ModelModule;
import br.com.rest.controlefacil.di.module.util.UtilModule;
import br.com.rest.controlefacil.domain.model.Category;
import br.com.rest.controlefacil.domain.model.CategoryIcons;
import br.com.rest.controlefacil.domain.model.Period;
import br.com.rest.controlefacil.domain.model.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by LUCAS RODRIGUES on 24/11/2017.
 */

public class ControleFacilApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("controle-facil")
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        List<Category> categories = CategoryIcons.getDefaultCategories();
                        realm.copyToRealm(categories);
                        realm.copyToRealm(Arrays.asList(new Period(1L, 30, "Mensal"), new Period(2L, 7, "Semanal"), new Period(3L, 0, "Personalidado")));
                        realm.copyToRealm(new User(1L, "lucas@hotmail.com", "123", "Lucas", ""));
                    }
                })
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dAOModule(new DAOModule())
                .modelModule(new ModelModule())
                .utilModule(new UtilModule())
                .build();
    }


}
