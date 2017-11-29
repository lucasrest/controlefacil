package br.com.rest.controlefacil;

import android.app.Application;

import br.com.rest.controlefacil.di.component.AppComponent;
import br.com.rest.controlefacil.di.component.DaggerAppComponent;
import br.com.rest.controlefacil.di.module.AppModule;
import br.com.rest.controlefacil.di.module.domain.DAOModule;
import br.com.rest.controlefacil.di.module.domain.ModelModule;
import br.com.rest.controlefacil.di.module.util.UtilModule;
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
