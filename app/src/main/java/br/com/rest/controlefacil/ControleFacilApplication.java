package br.com.rest.controlefacil;

import android.app.Application;

import br.com.rest.controlefacil.domain.di.DAOModule;
import br.com.rest.controlefacil.domain.di.DaggerDomainComponent;
import br.com.rest.controlefacil.domain.di.DomainComponent;
import br.com.rest.controlefacil.domain.di.ModelModule;
import br.com.rest.controlefacil.util.di.DaggerUtilComponent;
import br.com.rest.controlefacil.util.di.UtilComponent;
import br.com.rest.controlefacil.util.di.UtilModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by LUCAS RODRIGUES on 24/11/2017.
 */

public class ControleFacilApplication extends Application {

    private static AppComponent appComponent;
    private static UtilComponent utilComponent;
    private static DomainComponent domainComponent;

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

        domainComponent = DaggerDomainComponent
                .builder()
                .modelModule(new ModelModule())
                .dAOModule(new DAOModule())
                .build();

        utilComponent = DaggerUtilComponent
                .builder()
                .utilModule(new UtilModule())
                .build();

        appComponent = DaggerAppComponent
                .builder()
                .domainComponent(domainComponent)
                .utilComponent(utilComponent)
                .appModule(new AppModule(this))
                .build();
    }


}
